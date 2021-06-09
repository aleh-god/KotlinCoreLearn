package com.learnxinyminutes.kotlin.KotlinRu

fun main(args: Array<String>) {

    // Ссылки на классы
    //  Чтобы получить ссылку на статический Kotlin класс, используйте синтаксис литерала класса:
    val c = PersonX::class

    // Вы можете получить ссылку на класс определённого объекта ::class.qualifiedName
    val widget: Widget = GoodWidget()
    // Throws an AssertionError calculated by lazyMessage if the value is false and runtime assertions have been enabled during compilation.
    // fun assert(value: Boolean, lazyMessage: () -> Any)
    assert(widget is GoodWidget) { "Bad widget: ${widget::class.qualifiedName}" }

    // Ссылки на функции
    fun isOdd(x: Int) = x % 2 != 0
    isOdd(5)

    val numbers = listOf(1, 2, 3)
    // ::isOdd — значение функционального типа (Int) -> Boolean.
    println(numbers.filter(::isOdd)) // выведет [1, 3]
    // вы можете указать нужный контекст путём сохранения ссылки на функцию в переменной, тип которой задан явно:
    val predicate: (Int) -> Boolean = ::isOdd   // ссылается на isOdd(x: Int)

    fun length(s: String) = s.length
    // вы можете применять композиции функций к ссылкам на функции:
    val oddLength = compose(::isOdd, ::length)
    val strings = listOf("a", "ab", "abc")

    println(strings.filter(oddLength)) // выведет "[a, abc]"

    // Ссылки на свойства
    println(::x.get()) // выведет "1"
    ::x.set(2)
    println(x)         // выведет "2"

    // Для доступа к свойству, которое является членом класса, мы указываем класс:
    val prop = PersonX::name // ссылка на поле name класса
    println(prop.get(PersonX("Jesus", 33))) // выведет "Jesus"

    println(String::lastChar.get("abc")) // выведет "c"

    // Обращение к конструкторам происходит с помощью оператора :: и имени класса.
    function(::Foo)



}

// Выражение ::x возвращает объект свойства типа KProperty<Int>, который позволяет нам читать его значение с помощью get() или получать имя свойства при помощи обращения к свойству name.
var x = 1

// Для свойства-расширения:
val String.lastChar: Char
    get() = this[length - 1]

fun <A, B, C> compose(f: (B) -> C,
                      g: (A) -> B
                                ): (A) -> C {
    return { x -> f(g(x)) }
}

class PersonX(val name: String, var age: Int)

open class Widget

class GoodWidget : Widget()

class Foo

fun function(factory : () -> Foo) {
    val x : Foo = factory()
}