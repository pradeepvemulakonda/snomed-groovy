package com.yengic.components.uploader

import com.yengic.common.DescElement
import com.yengic.common.SnomedRef
import com.yengic.components.builder.Neo4jCommandExecutor

import javax.inject.Inject

import static org.neo4j.driver.v1.Values.parameters

/**
 * Created by Harita on 14/5/17.
 */
class ConceptUploader implements Uploader {

    public static final String CREATE_CONCEPT = "CREATE (c:Concept:FSN {conceptId: {conceptId}, term: {term}, synonyms:{synonyms}, descriptions:{descriptions}})"
    Neo4jCommandExecutor neo4jCommandExecutor

    @Inject
    ConceptUploader(Neo4jCommandExecutor neo4jCommandExecutor) {
        this.neo4jCommandExecutor = neo4jCommandExecutor
    }

    @Override
    def upload(SnomedRef snomedRef) {
        try {
            snomedRef.mapOfDesc.eachWithIndex { Map.Entry<String, DescElement> entry, int i ->
                def run = neo4jCommandExecutor.connection.createObject(CREATE_CONCEPT,
                        parameters("conceptId", entry.key, "term", entry.value.fsn, "synonyms", entry.value.synonym, "descriptions", entry.value.description))
                neo4jCommandExecutor.execute(run)
            }
            neo4jCommandExecutor.executor.waitForThreadsToComplete()
        } finally {
            neo4jCommandExecutor.close()
        }
    }
}
