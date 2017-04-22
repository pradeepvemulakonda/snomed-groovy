@GrabResolver(name="neo4j", root="http://m2.neo4j.org/")
@GrabResolver(name="restlet", root="http://maven.restlet.org/")
@GrabConfig(systemClassLoader = true)
@Grab('org.neo4j:neo4j-jdbc:1.9')

import groovy.sql.*

def sql = Sql.newInstance('jdbc:neo4j://172.28.5.0:7474/')

println "simple cypher statement"
sql.eachRow("start n=node(*) return n") {
	println "row $it"
}

println "parameterized cypher statement"
sql.eachRow("start n=node({1}) return n", [0]) {
	println "row $it"
}
println "Hello World"
