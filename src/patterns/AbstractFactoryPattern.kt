package patterns

// Full Factory Method pattern

// Product
private interface ElementBeta {

    fun render(): String
}

private abstract class ParagraphBeta(protected val text: String) : ElementBeta

private abstract class Heading(protected val level: Int, protected val text: String) : ElementBeta {

    init {
        require(level in 1..6) { "Wrong level" }
    }
}

// Concrete product
private class HtmlParagraphBeta(text: String) : ParagraphBeta(text) {

    override fun render(): String = "<p>$text</p>"
}

private class HtmlHeading(level: Int, text: String) : Heading(level, text) {

    override fun render(): String = "<h$level>$text</h$level>"
}

private class MarkdownParagraphBeta(text: String) : ParagraphBeta(text) {

    override fun render(): String = "$text"
}

private class MarkdownHeading(level: Int, text: String) : Heading(level, text) {

    override fun render(): String = "${"#".repeat(level)} $text"
}

// Creator
private abstract class DocumentBeta {
    private val elements = mutableListOf<ElementBeta>()

    // Каждый новый тип элемента требует дополнительных абстракций и реализаций
    abstract fun createParagraph(text: String): ParagraphBeta
    abstract fun createHeading(level: Int, text: String): Heading

    fun addParagraph(text: String) = elements.add(createParagraph(text))
    fun addHeading(level: Int, text: String) = elements.add(createHeading(level, text))

    fun render() = elements.joinToString("\n") { it.render() }
}

// Concrete creator
// 1. Реализация класса гарантирует, что все элементы - являются элементами разметки Html
private class HtmlDocumentBeta : DocumentBeta() {

    // 2. Эти методы только создают элементы. А еще есть задача порядка и отрисовки.
    override fun createParagraph(text: String) = HtmlParagraphBeta(text)
    override fun createHeading(level: Int, text: String): Heading = HtmlHeading(level, text)
}

// 3. Значит эти классы - фабрики. И нам не нужно наследование.
private class MarkdownDocumentBeta : DocumentBeta() {

    override fun createParagraph(text: String) = MarkdownParagraphBeta(text)
    override fun createHeading(level: Int, text: String): Heading = MarkdownHeading(level, text)
}

// Abstract Factory pattern. Фабрика создает набор элементов одной темы.

// Abstract Factory in pattern
private interface ElementFactory {

    fun createParagraph(text: String): ParagraphBeta
    fun createHeading(level: Int, text: String): Heading
}

// Concrete Factory in pattern
private class HtmlElementFactory : ElementFactory {

    override fun createParagraph(text: String) = HtmlParagraphBeta(text)
    override fun createHeading(level: Int, text: String): Heading = HtmlHeading(level, text)
}

private class MarkdownElementFactory : ElementFactory {

    override fun createParagraph(text: String) = MarkdownParagraphBeta(text)
    override fun createHeading(level: Int, text: String): Heading = MarkdownHeading(level, text)
}

// Client in pattern
private class DocumentWithElementFactory(private val elementFactory: ElementFactory) {
    private val elements = mutableListOf<ElementBeta>()

    fun addParagraph(text: String) = elements.add(
        element = elementFactory.createParagraph(text)
    )

    fun addHeading(level: Int, text: String) = elements.add(
        element = elementFactory.createHeading(level, text)
    )

    fun render() = elements.joinToString("\n") { it.render() }
}

// Abstract Factory through Kotlin way

// Можно сделать sealed
private interface ElementKotlinWay {

    val render: () -> String
}

private class ParagraphKotlinWay(override val render: () -> String) : ElementKotlinWay

private class HeadingKotlinWay(private val level: Int, private val doRender: (level: Int) -> String) :
    ElementKotlinWay {

    init {
        require(level in 1..6) { "Wrong level" }
    }

    override val render: () -> String = { doRender(level) }
}

private interface ElementFactoryKotlinWay {

    fun createParagraph(text: String): ParagraphKotlinWay
    fun createHeading(level: Int, text: String): HeadingKotlinWay
}

private object HtmlElementFactoryKotlinWay : ElementFactoryKotlinWay {

    override fun createParagraph(text: String) = ParagraphKotlinWay { "<p>$text</p>" }

    override fun createHeading(level: Int, text: String) = HeadingKotlinWay(level) { level ->
        "<h$level>$text</h$level>"
    }
}

private class DocumentKotlinWay(private val elementFactory: ElementFactoryKotlinWay) {
    private val elements = mutableListOf<ElementKotlinWay>()

    fun addParagraph(text: String) = elements.add(
        element = elementFactory.createParagraph(text)
    )

    fun addHeading(level: Int, text: String) = elements.add(
        element = elementFactory.createHeading(level, text)
    )

    fun render() = elements.joinToString("\n") { it.render() }
}

// Abstract Factory through Good Kotlin way

// Для безопасности типов, различные Element можно представить как value classes

private class ElementFactoryGoodKotlinWay(
    val createParagraph: (text: String) -> String,
    val createHeading: (level: Int, text: String) -> String,
)

private val htmlElementFactoryGoodKotlinWay = ElementFactoryGoodKotlinWay(
    createHeading = { level, text -> "<h$level>$text</h$level>" },
    createParagraph = { text -> "<p>$text</p>" },
)

private class DocumentGoodKotlinWay(private val elementFactory: ElementFactoryGoodKotlinWay) {
    private val elements = mutableListOf<String>()

    fun addParagraph(text: String) = elements.add(
        element = elementFactory.createParagraph(text)
    )

    fun addHeading(level: Int, text: String) = elements.add(
        element = elementFactory.createHeading(level, text)
    )

    fun render() = elements.joinToString("\n")
}

fun main() {
    val docBeta = HtmlDocumentBeta()
    docBeta.addHeading(1, "HtmlHead")
    docBeta.addParagraph("First Htmlparagraph.")
    println(docBeta.render())

    println()

    val docWithElementFactory = DocumentWithElementFactory(MarkdownElementFactory())
    docWithElementFactory.addHeading(2, "MarkdownHead")
    docWithElementFactory.addParagraph("First MarkdownParagraph.")
    println(docWithElementFactory.render())

    println()

    val docKotlinWay = DocumentKotlinWay(HtmlElementFactoryKotlinWay)
    docKotlinWay.addHeading(3, "KotlinWayHead")
    docKotlinWay.addParagraph("First KotlinWayParagraph.")
    println(docKotlinWay.render())

    println()

    val docGoodKotlinWay = DocumentGoodKotlinWay(htmlElementFactoryGoodKotlinWay)
    docGoodKotlinWay.addHeading(2, "GoodKotlinWayHead")
    docGoodKotlinWay.addParagraph("First GoodKotlinWayParagraph.")
    println(docGoodKotlinWay.render())
}