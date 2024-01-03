package kotlinTimingAPI

import kotlin.time.*
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.TimeSource.Monotonic.markNow
import kotlin.time.TimeSource.Monotonic.ValueTimeMark

fun mainKotlinTimingAPI() {
    println("Start kotlinTimingAPI.stopWatch")
    val durationInMillisec = stopWatch {
        doLongWork(30)
    }
    println("Stop, sec: ${durationInMillisec.toDouble().div(100)}")

    // measureTimed
    var resultDuration: Duration = Duration.ZERO
    println("Start measureTime")
    resultDuration = measureTime {
        doLongWork(4)
    }
    println("Stop, milli sec: $resultDuration")

    // measureTimedValue
    println("Start measureTime")
    val timedValue = measureTimedValue {
        doLongWork(9)
    }
    println("Stop, ${timedValue.value}: ${timedValue.duration}")

    // destructuring declaration
    println("Start destructuring declaration")
    val (value, duration) = measureTimedValue {
        doLongWork(8)
    }
    println("Stop, $value: $duration")

    val firstDuration = 2.hours
    val myDuration = firstDuration + 10.minutes

    println("Duration.inSeconds: ${myDuration.inWholeSeconds}")
    println("Duration.MINUTES: ${myDuration.toDouble(DurationUnit.MINUTES)}")
    myDuration.toComponents { hours, minutes, seconds, nanoseconds ->
        print("myDuration.toComponents: ")
        print("$hours ")
        print("$minutes ")
        print("$seconds ")
        print("$nanoseconds ")
        println()
    }

    val isoDuration = "PT13H33M"
    val iso = Duration.parseIsoStringOrNull(isoDuration)
    iso?.toComponents { seconds, nanoseconds ->
        print("isoDuration.toComponents: ")
        print("$seconds ")
        print("$nanoseconds ")
        println()
    }

    val watchPlugin = object : FrameWorkPlugin() {
        private var startTime: ValueTimeMark? = null
        override fun beforeOperation() {
            startTime = markNow()
        }

        override fun afterOperation() {
            val endTime: ValueTimeMark = markNow()
            val duration = endTime - startTime!!
                println("WorkPlugin: ${duration.inWholeSeconds} sec")
        }
    }
    watchPlugin.execute()

    val records = mutableMapOf<String, ValueTimeMark>()
    records["started"] = markNow()
}

fun stopWatch(block: () -> Unit): Long {
    val startTime = System.nanoTime()
    block()
    val endTime = System.nanoTime()
    // duration in milliseconds
    return (endTime - startTime) / 1_000_000
}

fun doLongWork(loopsNumber: Int = 10): Long {
    var result = 0L
    repeat(loopsNumber) {
        var sum: Long = 0L
        (0..1_000_000).forEach { item ->
            sum += item
        }
        result += sum
    }
    return result
}
