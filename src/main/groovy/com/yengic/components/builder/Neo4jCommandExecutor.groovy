package com.yengic.components.builder

import com.yengic.util.Neo4jConnection
import com.yengic.util.ThreadPoolExecutor

import javax.inject.Inject

import static com.yengic.common.ApplicationProperties.*

/**
 * Created by Pradeep on 14/5/17.
 */
class Neo4jCommandExecutor {

    Neo4jConnection connection

    ThreadPoolExecutor executor

    @Inject
    Neo4jCommandExecutor(Neo4jConnection connection, ThreadPoolExecutor executor) {
        this.connection = connection
        this.executor = executor
        connection.connect(BOLT_URL, USER_NAME, PASSWORD)
    }

    def execute(Closure closure) {
        executor.execute(closure)
    }

    def close() {
        connection.close()
        executor.close()
    }
}
