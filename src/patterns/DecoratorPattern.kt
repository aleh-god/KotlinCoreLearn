package patterns

import java.time.Clock
import java.util.*

// Проблема 1: Чем больше настроек для Logger, тем больше перегружен конструктор класса
private class LoggerSimpleWay(
    private val includeThreadName: Boolean,
    private val includeUniqueId: Boolean,
    // Проблема 2: Если мы не используем DateTime, то все равно запрашиваем зависимость Clock
    private val includeDateTime: Boolean,
    private val clock: Clock,
) {

    fun log(message: String) {
        if (includeDateTime) print("[${clock.instant()}] ")
        if (includeUniqueId) print("{${UUID.randomUUID()}} ")
        // Проблема 3: Конкретная реализация метода привязывает решение к выводу на консоль
        print(message)
        if (includeThreadName) print(" (on ${Thread.currentThread().name} thread)")
        println()
    }
}

// Реализация Decorator pattern
// Это Component
private interface Logger {

    fun log(message: String)
}

// Это Concrete Component
private class ConsoleLogger : Logger {
    override fun log(message: String) {
        println(message)
    }
}

// Это Decorator
// Если структура декоратора простая, то от abstract class можно отказаться в сторону прямой реализации interface Logger
private abstract class LoggerDecorator(protected val logger: Logger) : Logger

// Это Concrete Decorator
private class UniqueIdLogger(logger: Logger) : LoggerDecorator(logger) {

    override fun log(message: String) = logger.log("{${UUID.randomUUID()}} $message")
}

private class ThreadNameLogger(logger: Logger) : LoggerDecorator(logger) {

    override fun log(message: String) = logger.log("$message (on ${Thread.currentThread().name} thread)")
}

private class DateTimeLogger(
    logger: Logger,
    private val clock: Clock,
) : LoggerDecorator(logger) {

    override fun log(message: String) = logger.log("[${clock.instant()}] $message")
}

// Реализация Decorator pattern through Kotlin way
private fun interface LoggerKotlinWay {

    fun log(message: String)
}

// Если параметр для лямбды один, то его можно опустить - это it
private val consoleLoggerKotlinWay = LoggerKotlinWay { println(it) }

private fun LoggerKotlinWay.withUniqueId() = LoggerKotlinWay { log("{${UUID.randomUUID()}} $it") }
private fun LoggerKotlinWay.withThreadName() = LoggerKotlinWay { log("$it (on ${Thread.currentThread().name} thread)") }
private fun LoggerKotlinWay.withDateTime(clock: Clock) = LoggerKotlinWay { log("[${clock.instant()}] $it") }

private fun main() {
    val loggerSimpleWay = LoggerSimpleWay(
        includeDateTime = true,
        includeThreadName = true,
        includeUniqueId = true,
        clock = Clock.systemDefaultZone(),
    )

    loggerSimpleWay.log("SimpleWay is started")

    val consoleLogger = UniqueIdLogger(
        logger = ThreadNameLogger(
            logger = DateTimeLogger(
                logger = ConsoleLogger(),
                clock = Clock.systemDefaultZone(),
            )
        )
    )

    consoleLogger.log("Decorator is started")

    val consoleLoggerKotlinWay = consoleLoggerKotlinWay
        .withDateTime(
            clock = Clock.systemDefaultZone(),
        )
        .withUniqueId()
        .withThreadName()

    consoleLoggerKotlinWay.log("KotlinWay is started")
}