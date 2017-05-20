package com.yengic.components.uploader

import com.yengic.common.BaseSnomedFileReader
import com.yengic.common.DescElement
import com.yengic.common.SnomedRef
import com.yengic.components.builder.Neo4jCommandExecutor

import javax.inject.Inject
import java.util.concurrent.CancellationException
import java.util.concurrent.ExecutionException
import java.util.concurrent.Future

import static org.neo4j.driver.v1.Values.parameters

/**
 * Created by Pradeep on 14/5/17.
 */
class RelationshipUploader {

    static String CREATE_RELATIONSHIP = "MATCH (source:Concept:FSN) WHERE source.conceptId = {sourceId} \n" +
                                                "MATCH (dest:Concept:FSN) WHERE dest.conceptId = {destId}\n" +
                                                "CREATE (source)-[r:{label} {relId:{typeId}, term: {term}}]->(dest)"
    Neo4jCommandExecutor neo4jCommandExecutor
    BaseSnomedFileReader baseFileReader

    @Inject
    RelationshipUploader(Neo4jCommandExecutor neo4jCommandExecutor, BaseSnomedFileReader baseFileReader) {
        this.neo4jCommandExecutor = neo4jCommandExecutor
        this.baseFileReader = baseFileReader
    }

    def upload(SnomedRef sr, filePath) {
        def localAfterExecute = neo4jCommandExecutor.executor.metaClass.&afterExecute
        neo4jCommandExecutor.executor.metaClass.afterExecute= {
            Runnable r, Throwable t ->
                localAfterExecute(r, t)
                if (t == null && r instanceof Future<?>) {
                    try {
                        Future<?> future = (Future<?>) r
                        if (future.isDone()) {
                            future.get()
                        }
                    } catch (CancellationException ce) {
                        t = ce
                    } catch (ExecutionException ee) {
                        t = ee.getCause()
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt()
                    }
                }
                if (t != null) {
                    // do not stop the processing.
                    System.out.println(t)
                }
        }

        try {
            baseFileReader.readAndProcess(filePath, {
                values ->
                    def isActive = values[2] == "1"
                    if (isActive) {
                        def refData = getLabel(values[7])
                        def query = CREATE_RELATIONSHIP.replaceAll(/\{label\}/, refData.label)
                        def run = neo4jCommandExecutor.connection.createObject(query,
                                parameters("sourceId", values[4], "destId", values[5], "label", refData.label, "typeId", values[7], "term", refData.fsn))
                        neo4jCommandExecutor.execute run
                    }
            })
            neo4jCommandExecutor.executor.waitForThreadsToComplete({
                it->
                    print "ignore"
            })
        } finally {
            neo4jCommandExecutor.close()
        }
    }

    def getLabel(String s) {
        def sr= SnomedRef.instance
        DescElement value = sr.mapOfDesc.get(s)
        new Expando(fsn: value.fsn, label: value.label)
    }
}
