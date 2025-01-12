package patterns

private class DocumentClassicWay(private val format: Format) {

    private val elements = mutableListOf<String>()

    fun addParagraph(text: String) {
        // Класс зависит от конкретных реализаций
        val paragraph = when (format) {
            Format.HTML -> "<p>$text</p>"
            Format.MARKDOWN -> "$text\n"
        }

        elements.add(paragraph)
    }

    fun render() = elements.joinToString("\n")

    enum class Format {
        HTML,
        MARKDOWN;
    }
}

//  Simple Factory Method

private abstract class Document {
    private val elements = mutableListOf<String>()

    abstract fun createParagraph(text: String): String

    fun addParagraph(text: String) = elements.add(createParagraph(text))
    fun render() = elements.joinToString("\n")
}

private class HtmlDocument : Document() {

    override fun createParagraph(text: String): String = "<p>$text</p>"
}

private class MarkdownDocument : Document() {

    override fun createParagraph(text: String): String = "$text\n"
}

// Full Factory Method

// Product (part 1) in Factory Method pattern
private interface Element {

    fun render(): String
}

// Product (part 2) in Factory Method pattern
private abstract class Paragraph(protected val text: String): Element

// Concrete product in Factory Method pattern
private class HtmlParagraph(text: String) : Paragraph(text) {

    override fun render(): String = "<p>$text</p>"
}

private class MarkdownParagraph(text: String) : Paragraph(text) {

    override fun render(): String = "$text\n"
}

// Creator in Factory Method pattern
// Преимущество  Factory Method pattern - любые изменения в логике не затрагивают ReworkDocument
private abstract class ReworkDocument {
    private val elements = mutableListOf<Element>()

    // Factory Method
    abstract fun createParagraph(text: String): Paragraph

    fun addParagraph(text: String) = elements.add(createParagraph(text))
    fun render() = elements.joinToString("\n") { it.render() }
}

// Concrete creator in Factory Method pattern
private class ReworkHtmlDocument : ReworkDocument() {

    override fun createParagraph(text: String) = HtmlParagraph(text)
}

private class ReworkMarkdownDocument : ReworkDocument() {

    override fun createParagraph(text: String) = MarkdownParagraph(text)
}

fun main() {
    val doc = HtmlDocument()
    doc.addParagraph("First paragraph.")
    println(doc.render())

    val docRework = ReworkHtmlDocument()
    docRework.addParagraph("First Rework paragraph.")
    println(docRework.render())
}
