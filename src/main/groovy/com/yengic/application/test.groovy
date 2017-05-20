package com.yengic.application

import com.yengic.components.builder.Neo4jCommandExecutor
import com.yengic.util.Neo4jConnection
import com.yengic.util.ThreadPoolExecutor
import org.neo4j.driver.v1.StatementResult

import static org.neo4j.driver.v1.Values.parameters

start = System.currentTimeMillis()

def neo4jDriver = new Neo4jConnection()
neo4jDriver.connect("bolt://localhost:7687", "neo4j", "ramani456%")
def pool = new ThreadPoolExecutor()
Neo4jCommandExecutor executor = new Neo4jCommandExecutor(neo4jDriver, pool)
(1..10).each {
    def run = executor.connection.createObject("CREATE (a:Person {name: {name}, title: {title}})",
            parameters("name", "Arthur", "title", "King"))
    executor.execute(run)
}

executor.executor.waitForThreadsToComplete()

StatementResult result = neo4jDriver.getObject("MATCH (a:Person) WHERE a.name = {name} " +
        "RETURN a.name AS name, a.title AS title",
        parameters("name", "Arthur"))
result.each { record ->
    System.out.println(record.get("title").asString() + " " + record.get("name").asString())
}
now = System.currentTimeMillis()
neo4jDriver.close()
pool.close()
println now - start
