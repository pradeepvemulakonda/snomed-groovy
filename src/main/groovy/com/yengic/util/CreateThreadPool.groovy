package com.yengic.util

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.FutureTask

/**
 * Created by L080747 on 13/04/2017.
 */
class CreateThreadPool implements AutoCloseable {
    def threadPool = Executors.newFixedThreadPool(150)
    List<Future> futureTasks = new ArrayList<>()

    def start = {closure ->
        futureTasks.push(threadPool.submit( closure as Callable ))
    }

    @Override
    void close() throws Exception {
        threadPool.shutdown()
    }
}