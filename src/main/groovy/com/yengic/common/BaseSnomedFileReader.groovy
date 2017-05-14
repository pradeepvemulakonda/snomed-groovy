package com.yengic.common

import groovy.time.TimeCategory
import groovy.time.TimeDuration

/**
 * Created by l080747 on 28/04/2017.
 */

class BaseSnomedFileReader {

    def readAndProcess(filePath, Closure closure) {
        Date start = new Date()
        def file = new File(filePath)
        file.withReader {
            it.eachLine {
                line, number ->
                    if(number == 1) {
                        return
                    }
                    def values = line.split("\\t")
                    closure values
            }
        }

        Date stop = new Date()
        TimeDuration td = TimeCategory.minus( stop, start )
        // Get current size of heap in bytes
        long heapSize = Runtime.getRuntime().totalMemory()
        print "-->"+ heapSize

        // Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
        long heapMaxSize = Runtime.getRuntime().maxMemory()
        print "-->"+ heapMaxSize

        // Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
        long heapFreeSize2 = Runtime.getRuntime().freeMemory()
        print "-->"+ heapFreeSize2
        println ""
        println td
    }

}
