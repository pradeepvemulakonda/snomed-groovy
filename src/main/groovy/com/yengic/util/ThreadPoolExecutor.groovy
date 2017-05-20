package com.yengic.util

import com.yengic.common.ApplicationProperties

import javax.inject.Inject
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by Pradeep on 13/04/2017.
 */
class ThreadPoolExecutor implements AutoCloseable {

    ExecutorService threadPool
    def futureTasks
    def noOfThreads = ApplicationProperties.NO_OF_THREADS

    @Inject
    ThreadPoolExecutor() {
        threadPool = Executors.newFixedThreadPool(noOfThreads)
        futureTasks = []
    }

    def execute = { closure ->
        futureTasks.push(threadPool.submit( closure as Callable ))
    }

    def waitForThreadsToComplete(Closure c) {
        def currentFeatureTask = null
        try {
            futureTasks.each {
                currentFeatureTask = it
                it.get()
            }
        }catch(Exception e) {
            c currentFeatureTask
        }
    }

    @Override
    void close() throws Exception {
        threadPool.shutdown()
    }
}