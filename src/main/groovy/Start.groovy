import org.neo4j.driver.v1.Record
import org.neo4j.driver.v1.StatementResult

import static org.neo4j.driver.v1.Values.parameters

start = System.currentTimeMillis()

def neo4jDriver = new Neo4jConnection();
neo4jDriver.connect("bolt://localhost:7687", "neo4j", "ramani456%")
def pool = new CreateThreadPool()
(1..10).each {
    def run = neo4jDriver.createObject("CREATE (a:Person {name: {name}, title: {title}})",
            parameters("name", "Arthur", "title", "King"))
    pool.start(run)
}

pool.futureTasks.each { it.get() }

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
