import org.neo4j.driver.v1.*

/**
 * Created by L080747 on 13/04/2017.
 */
class Neo4jConnection implements AutoCloseable {

    Driver driver

    def connect(url, userName = 'neo4j', password = 'neo4j') {
        driver = GraphDatabase.driver(url, AuthTokens.basic(userName, password))
    }

    @Override
    void close() throws Exception {
        driver.close()
    }

    def createObject(String query, Value param) {
        return {
            Session session = driver.session()
            session.run(query, param)
            session.close()
        }
    }

    def getObject(String query, Value param) {
        Session session = driver.session()
        StatementResult result = session.run(query, param)
        session.close()
        return result
    }
}
