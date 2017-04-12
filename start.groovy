@Grab(group="org.neo4j", module="neo4j-rest-graphdb", version="1.9.M04")
import org.neo4j.rest.graphdb.RestGraphDatabase

def db = new RestGraphDatabase("http://localhost:7474/db/data")
def node = db.getNodeById(0)
println node
db.shutdown()
println "Hello World"
