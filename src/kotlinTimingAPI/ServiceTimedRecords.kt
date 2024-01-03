package kotlinTimingAPI

class ServiceTimedRecords {

    fun start() {
        doLongWork(11)
    }

    fun execute() {
        doLongWork(12)
    }

    fun destroy() {
        doLongWork(13)
    }
}