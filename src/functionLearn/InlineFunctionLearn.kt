package functionLearn

fun main() {
    processRecords("Alice", "Billy", "Charlie", "Donald")
}

fun processRecords(vararg records: String) {
    for (record in records) {
        executeAndMeasure(
            label = record,
            block = {
                if (record.startsWith("C")) return // локальный return это - return@executeAndMeasure
                save(record)
            }
        )
    }
}

inline fun executeAndMeasure(label: String, block: () -> Unit) {
    val start = System.nanoTime()
    run { block() } // можно выполнять в других инлайн функциях
    val end = System.nanoTime()
    println("Duration for $label: ${(end - start) / 1_000_000} ms")
}

inline fun executeAndMeasureCrossinline(label: String, crossinline block: () -> Unit) {
    val start = System.nanoTime()
    // Нельзя выполнять в обычных лямбдах, crossinline - можно
    Thread { block() }.start()
    val end = System.nanoTime()
    println("Duration for $label: ${(end - start) / 1_000_000} ms")
}

inline fun executeAndMeasureNoinline(
    label: String,
    crossinline block: () -> Unit,
    noinline onError: (e: Exception) -> Unit
) {
    Thread {
        try {
            val start = System.nanoTime()
            block()
            val end = System.nanoTime()
            println("Duration for $label: ${(end - start) / 1_000_000} ms")
        } catch (e: Exception) {
            onError(e)
        }
    }.start()
}

fun save(record: String) {
    println(record)
}
