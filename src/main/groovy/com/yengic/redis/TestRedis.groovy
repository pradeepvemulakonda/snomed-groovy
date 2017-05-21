package com.yengic.redis

import com.yengic.util.ThreadPoolExecutor
import redis.clients.jedis.*

/**
 * Created by Harita on 27/4/17.
 */
class TestRedis {

    def test() {
        Jedis jedis = new Jedis("0.0.0.0")
        def pool = new ThreadPoolExecutor()

        (1..5000000).each {
            i-> pool.execute({
                jedis.set("hello", "yello")
            })
            if(i==5000000)
               println i
        }
        pool.threadPool.shutdownNow()
        Jedis jedis1 = new Jedis("0.0.0.0")
        print jedis1.get("hello")
    }

    static void main(String[] args) {
        new TestRedis().test()
    }
}
