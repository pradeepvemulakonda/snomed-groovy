package com.yengic.components.uploader

import com.yengic.common.SnomedRef
import com.yengic.components.builder.Neo4jCommandExecutor

import javax.inject.Inject
import java.util.concurrent.FutureTask

import static org.neo4j.driver.v1.Values.parameters

/**
 * Created by Harita on 14/5/17.
 */
class IndexCreator implements Uploader{


    static final String CREATE_INDEX_CONCEPT_ID = "CREATE INDEX ON :Concept(conceptId)"
    static final CREATE_INDEX_TERM = "CREATE INDEX ON :Concept(term)"
    static final CREATE_UNIQUE_CONSTRAINT = "CREATE CONSTRAINT ON (concept:Concept) ASSERT concept.conceptId IS UNIQUE"

    Neo4jCommandExecutor neo4jCommandExecutor

    @Inject
    IndexCreator(Neo4jCommandExecutor neo4jCommandExecutor) {
        this.neo4jCommandExecutor = neo4jCommandExecutor
    }

    @Override
    def upload(SnomedRef snomedRef) {
        try {

            def run3 = neo4jCommandExecutor.connection.createObject(CREATE_UNIQUE_CONSTRAINT,
                    parameters())
            neo4jCommandExecutor.execute(run3)
            neo4jCommandExecutor.executor.waitForThreadsToComplete()

            def run2 = neo4jCommandExecutor.connection.createObject(CREATE_INDEX_TERM,
                    parameters())
            neo4jCommandExecutor.execute(run2)
            neo4jCommandExecutor.executor.waitForThreadsToComplete()

        } finally {
            neo4jCommandExecutor.close()
        }
        return null
    }
}
