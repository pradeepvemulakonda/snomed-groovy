package com.yengic.redis

import redis.clients.jedis.*

/**
 * Created by Harita on 27/4/17.
 */
class TestRedis {

    def test() {
        Jedis jedis = new Jedis("0.0.0.0")
        jedis.sadd()

    }

    static void main(String[] args) {
        new TestRedis().test()
    }
}
