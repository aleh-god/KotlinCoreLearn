package com.learnxinyminutes.kotlin.KotlinRu

fun main(args: Array<String>) {

    InitOrderDemo("Привет")

    println("Построение класса Derived(\"hello\", \"world\")")
    val d = Derived("hello", "world")

}

class InitOrderDemo(name: String) {
    // При создании экземпляра класса блоки инициализации выполняются в том порядке, в котором они идут в теле класса, чередуясь с инициализацией свойств:
    val firstProperty = "Первое свойство: $name".also(::println)

    init {
        println("Первый блок инициализации: ${name}")
    }

    val secondProperty = "Второе свойство: ${name.length}".also(::println)

    init {
        println("Второй блок инициализации: ${name.length}")
    }

    // Теневое поле (backing field) Классы в Kotlin не могут иметь полей.
    // Kotlin предоставляет автоматическое теневое поле, к которому можно обратиться с помощью идентификатора field:
    var counter = 0
        set(value) {
            if (value >= 0) field = value // значение при инициализации записывается прямиком в backing field
        }

    var rating: Int = 5
        get() {
            if (field < 5) {
                print("Warning: this is a terrible book")
            }
            return field
        }
        set(value) {
            field = when {
                value > 10 -> 10
                value < 0 -> 0
                else -> value
            }
        }

    // Переопределение обеспечивает мощную и лаконичную функциональность, но помните, что другие программисты могут об этом не знать, обращаясь к геттеру свойства в другой части кода
    // Мы должны быть осторожны, чтобы не выполнять дорогостоящие вычисления в этих методах и минимизировать побочные эффекты, чтобы наш код оставался чистым и понятным.
    val isWorthReading get() = this.rating > 5

    // Теневые свойства
    // Если вы хотите предпринять что-то такое, что выходит за рамки вышеуказанной схемы "неявного теневого поля", вы всегда можете использовать теневое свойство (backing property):
    private var _table: Map<String, Int>? = null
    public val table: Map<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap() // параметры типа вычисляются автоматически
                // (ориг.: "Type parameters are inferred")
            }
            return _table ?: throw AssertionError("Set to null by another thread")
        }

    // Константы времени компиляции
    /*
    Находиться на самом высоком уровне или быть членами объекта object
    Быть проинициализированными значением типа String или значением примитивного типа
    Не иметь переопределённого геттера
     */
    val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"

    // Свойства с поздней инициализацией
    lateinit var subject: String

}

// Если у класса есть основной конструктор, каждый дополнительный конструктор должен прямо или косвенно ссылаться (через другой(ие) конструктор(ы)) на основной.
class Person(val name: String) {

    var children: MutableList<Person> = mutableListOf()

    // Осуществляется это при помощи ключевого слова this:
    constructor(name: String, parent: Person) : this(name) {
        parent.children.add(this)
    }
}

// Порядок инициализации производного класса
open class Base(val name: String) {

    init { println("Инициализация класса Base") }

    open val size: Int =
        name.length.also { println("Инициализация свойства size в класса Base: $it") }
}

class Derived(
    name: String,
    val lastName: String
) : Base(name.capitalize().also { println("Аргументы, переданные в конструктор класса Base: $it") }) {

    init { println("Инициализация класса Derived") }

    override val size: Int =
        (super.size + lastName.length).also { println("Инициализация свойства size в классе Derived: $it") }
}

// Для того, чтобы отметить конкретный супертип (родительский класс), от которого мы наследуем данную реализацию, мы используем ключевое слово super.
// Для задания имени родительского супертипа используются треугольные скобки, например super<Base>:
open class Rectangle {
    open fun draw() { /* ... */ }
}

interface Polygon {
    fun draw() { /* ... */ } // члены интерфейса открыты ('open') по умолчанию
}

class Square() : Rectangle(), Polygon {
    // Компилятор требует, чтобы функция draw() была переопределена:
    override fun draw() {
        super<Rectangle>.draw() // вызов Rectangle.draw()
        super<Polygon>.draw() // вызов Polygon.draw()
    }
}