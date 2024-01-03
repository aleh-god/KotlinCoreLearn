package kotlinTimingAPI

abstract class FrameWorkPlugin {

    abstract fun beforeOperation()

    abstract fun afterOperation()

    fun execute() {
        beforeOperation()
        doLongWork(14)
        afterOperation()
    }
}
