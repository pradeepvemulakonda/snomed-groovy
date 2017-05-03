import groovy.time.TimeCategory
import groovy.time.TimeDuration

/**
 * Created by l080747 on 28/04/2017.
 */

class ReadDescriptions {

    def read(filePath) {
        long heapFreeSize = Runtime.getRuntime().freeMemory();
        println "-->"+ heapFreeSize
        Date start = new Date()
        def mapOfDesc  = [:]
        def file = new File(filePath)
        file.withReader {
            it.eachLine {
                line ->
                    def values = line.split("\\t")
                    if(!mapOfDesc.containsKey(values[4])) {
                        def newList = []
                        newList << values[7]
                        mapOfDesc[values[4]] = newList
                    } else {
                        def existingList = mapOfDesc[values[4]]
                        existingList << values[7]
                    }
            }
        }
        mapOfDesc.toString()
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
        println td
        Thread.sleep(1000)
        Runtime.getRuntime().gc()
        Thread.sleep(1000000)
        print mapOfDesc.size()
    }

    def readFSA(filePath) {
        def client = new JedisClient().connect();
        Date start = new Date()
        def mapOfDesc  = [:]
        def file = new File(filePath)
        file.withReader {
            it.eachLine {
                line ->
                    def values = line.split("\\t")
                    def typeId = values[6]
                    if(typeId == '900000000000003001') {
                        client.createDescription(values[4], values[7])
                    }

                    //else {
                    //    client.createSynonyms(values[4], values[7])
                    // }
            }
        }
        mapOfDesc.toString()
        Date stop = new Date()
        TimeDuration td = TimeCategory.minus( stop, start )
        println td
        // Get current size of heap in bytes
        long heapSize = Runtime.getRuntime().totalMemory()
        print "-->"+ heapSize

        // Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
        long heapMaxSize = Runtime.getRuntime().maxMemory()
        print "-->"+ heapMaxSize

        // Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
        long heapFreeSize = Runtime.getRuntime().freeMemory();
        print "-->"+ heapFreeSize
        Thread.sleep(10000);
        println mapOfDesc.size()
    }
}
