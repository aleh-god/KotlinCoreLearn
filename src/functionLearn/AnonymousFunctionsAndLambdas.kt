package functionLearn

import java.io.Serializable

private val lines = listOf(
    "NAME=Jesus",
    "Bad data!!!",
    "ROLE=admin",
)

private fun writePair(line: String) {
    val (key, value) = line.split("=")
    println("$key is equal to $value")
}

// Лямбду можем присвоить свойству
private val writePairAsProperty = { line: String ->
    val (key, value) = line.split("=")
    println("$key is equal to $value")
    // Последняя строка в лямбде возвращает результат, поэтому - нелокальный return не используется
}

// Анонимную функцию можно тоже присвоить свойству
private val AnonymousFunAsProperty = fun (line: String) {
    val (key, value) = line.split("=")
    println("$key is equal to $value")
}

// Добавление inline может решить проблему с Guard pattern для лямбды
private inline fun processLines(process: (String) -> Unit) {
    println("START")
    lines.forEach(process)
    println("END")
}

// Для ситуации, когда логике нужно возвратить результат

// Для лямбды нельзя указать возвращаемый тиg. Например, Serializable
// Можно указать в сигнатуре
// val lambdaPairUp: (String) -> Serializable
val lambdaPairUp = { line: String ->
    val (key, value) = line.split("=")
    key to value
}

val anonymousFunPairUp = fun(line: String) {
    val (key, value) = line.split("=")
    key to value
}

val anonymousFunPairUpWithType = fun(line: String): Serializable { //  Pair<String, String>
    val (key, value) = line.split("=")
    // для анонимных функций нужно указать явно return
    return key to value
}

// Анонимная функция может не содержать тело, а использовать цепочку функций
val anonymousFunWithoutBody = fun(line: String): Pair<String, String> =
    line
        .split("=")
        .let { (k,v) -> k to v }

// Так как возможности обоих в большинстве случаев пересекаются, то Анонимная функция очень редко используется в коде!

private fun main() {
    // Обычный вызов
    writePair(lines.first())

    // Вызов через свойство
    writePairAsProperty(lines.first())
    AnonymousFunAsProperty(lines[2])

    println(lambdaPairUp(lines.first())) // Return (NAME, Jesus)
    println(anonymousFunPairUp(lines.first())) // Return kotlin.Unit
    println(anonymousFunPairUpWithType(lines.first())) // Return (NAME, Jesus)
    println(anonymousFunWithoutBody(lines.first()))

    // Передача анонимной функции как аргумент функции
    processLines(
        fun (line: String) {
            // Guard pattern
            // Этот return будет возвращать только из своей функции.
            // Для вызовов внутри циклов это будет иметь значение!!!
            if ("=" !in line) return
            val (key, value) = line.split("=")
            println("$key is equal to $value")
        }
    )

    // Передача лямбды как аргумент функции
    processLines { line: String ->
        // Лямбды в отличие от анонимных функций не могут использовать не локальный return
        // Для переданных лямбд в inline функцию return будет возвращать из родительской функции (здесь это main)
        if ("=" !in line) return
        val (key, value) = line.split("=")
        println("$key is equal to $value")
    }
}