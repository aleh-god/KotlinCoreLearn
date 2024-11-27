package functionLearn

internal val regex = "\\s+" // это получатель вызова, объект к которому вызывается функция или receiver
    .toRegex()
internal const val text =
    "Strong skipping mode is an experimental feature in the Jetpack Compose Compiler 1.5.4+ that is currently being tested."

internal fun countWords(input: String) = input.split(regex).size
internal fun String.wordCount() = this.split(regex).size

fun main() {
    val baseFuncResult = countWords(text)
    val extFunResult = text.wordCount()

    // Function Type
    // callable reference operator
    val funWithoutReceiver: (String) -> Int = ::countWords
    val useReferenceResult = funWithoutReceiver(text)
    // Ext Function Type
    val funWithReceiver: String.() -> Int = String::wordCount
    val useReferenceResultAgain = text.funWithReceiver()
    val useExtFunTypeLikeBase = funWithReceiver(text)

    val canSetFunTypeToExtType: String.() -> Int = ::countWords
    val canSetExtFunTypeToType: (String) -> Int = String::wordCount

    val reworkFunResult = notReadableFunction(text, String::reversed)

    // Bound function reference
    // Уже другой функциональный тип
    val funWithReceiverForObject: () -> Int = text::wordCount
    val resultForObject = funWithReceiverForObject()
}

internal fun notReadableFunction(string: String, customTransform: (String) -> String): String {
    return customTransform(
        string
            .lowercase()
            .replace(".", "")
    )
}

internal fun chainUseLetFun(string: String, customTransform: (String) -> String): String =
    string
        .lowercase()
        .let { customTransform(it) }
        .replace(".", "")

/*
Обычную функцию из java библиотеки нельзя использовать, как экстеншен функцию

internal fun chainUseLetFun2(string: String, customTransform: (String) -> String): String =
    string
        .lowercase()
        .customTransform()
        .replace(".", "")
 */

internal fun useChainAsExtFun(string: String, customTransform: String.() -> String): String =
    string
        .lowercase()
        .customTransform()
        .replace(".", "")
