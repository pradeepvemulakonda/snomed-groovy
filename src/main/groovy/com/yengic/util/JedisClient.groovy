package com.yengic.util
/**
 * Created by l080747 on 2/05/2017.
 */
import redis.clients.jedis.*
class JedisClient {

    Jedis jedis

    def connect() {
        jedis = new Jedis("localhost")
        this
    }

    def createDescription(concept, description) {
        jedis.hset("descMap", concept, description)
    }

    def getDescription(concept) {
        jedis.hget("descMap", concept)
    }

    def createSynonyms(concept, description) {
        def desc = jedis.hget(concept)
        jedis.hset("descMap", concept, description + "," +desc)
    }
}
