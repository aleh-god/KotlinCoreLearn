package functionLearn

internal class Document(var text: String) {
    val length get() = text.length
}

internal val book =
    Document("Strong skipping mode is an experimental feature in the Jetpack Compose Compiler 1.5.4+ that is currently being tested.")

fun main() {
    val getStory: (Document) -> String = Document::text::get
    val getLength: (Document) -> Int = Document::length::get
    val setStory: (Document, String) -> Unit = Document::text::set

    // Для привязанных ссылок Document не выражен в сигнатуре
    // Это позволяет использовать ссылки в коде, который ничего не знает о Document
    val getStoryBound: () -> String = book::text::get
    val getLengthBound: () -> Int = book::length::get

    val setStoryBound: (String) -> Unit = book::text::set
    setStoryBound("This change may seem small, but the behavior change is large, as is the cost of getting it wrong.")
    val setStoryBoundLikeExt: String.() -> Unit = book::text::set
    "This change may seem small, but the behavior change is large, as is the cost of getting it wrong."
        .setStoryBoundLikeExt()
}
