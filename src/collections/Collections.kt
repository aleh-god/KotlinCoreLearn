package collections


/*
The Kotlin Standard Library provides implementations for basic collection types: sets, lists, and maps.
A pair of interfaces represent each collection type:
A read-only interface that provides operations for accessing collection elements.
A mutable interface that extends the corresponding read-only interface with write operations: adding, removing, and updating its elements.

Тип List<out T> в Kotlin — интерфейс, который предоставляет read-only операции, такие как size, get, и другие.
Так же, как и в Java, он наследуется от Collection<T>, а значит и от Iterable<T>.
Методы, которые изменяют список, добавлены в интерфейс MutableList<T>.
То же самое относится и к Set<out T>, MutableSet<T>, Map<K, out V>, MutableMap<K, V>.
 */

private val list: List<String>
    get() {
        val words = "A long time ago in a galaxy far far away".split(" ")
        return words
    }

fun main(args: Array<String>) {

    //  Простой способ создания такой коллекции выглядит так:
    var initList = listOf(1, 2, 3)
    initList = mutableListOf(1, 2, 3)
    var initSet = setOf(1, 2, 3)
    initSet = mutableSetOf(1, 2, 3)
    // Создание ассоциативного списка некритичным для производительности способом может быть осуществленно с помощью простой идиомы:
    var initMap = mapOf(1 to "one", 2 to "two")

    //  You can use Collection as a parameter of a function that applies to different collection types.
    //  For more specific cases, use the Collection 's inheritors: List and Set.
    fun printAll(strings: Collection<String>) {
        for(s in strings) print("$s ")
        println()
    }
    val stringList = listOf("one", "two", "one")
    printAll(stringList)

    val stringSet = setOf("one", "two", "three")
    printAll(stringSet)

    fun List<String>.getShortWordsTo(shortWords: MutableList<String>, maxLength: Int) {
        this.filterTo(shortWords) { it.length <= maxLength }            // Фильтруем this-объект на строки длиной меньше параметра. Результат в изменяемый Лист
        // throwing away the articles
        val articles = setOf("a", "A", "an", "An", "the", "The")        // Набор артиклей, которые не слова
        shortWords -= articles                                          // Вычитаем артикли из списка слов
    }

    // MutableCollection<T> is a Collection with write operations, such as add and remove.
    val words = "A long time ago in a galaxy far far away".split(" ")
    val shortWords = mutableListOf<String>()
    words.getShortWordsTo(shortWords, 3)
    println(shortWords)

    // val numbers всего лишь гранитель ссылки на объект
    val numbers = mutableListOf("one", "two", "three", "four")
    numbers.add("five")   // Поэтому, сам объект менять можно
    // numbers = mutableListOf("six", "seven")      // А переназначать значение переменной (ее ссылку на новый объект) - нельзя

    val strs: MutableList<String> = ArrayList()
    val objs: MutableList<Any> = ArrayList() //= strs // !!! A compile-time error here saves us from a runtime exception later.

    objs.add(1) // Put an Integer into a list of Strings

    // val s = strs[0] // !!! ClassCastException: Cannot cast Integer to String

    // read-only операции
    val numbersX: MutableList<Int> = mutableListOf(1, 2, 3)
    val readOnlyView: List<Int> = numbersX
    println(numbersX)        // выведет "[1, 2, 3]"
    numbersX.add(4)
    println(readOnlyView)   // выведет "[1, 2, 3, 4]"
    // readOnlyView.clear()    // -> не скомпилируется

    val strings = hashSetOf("a", "b", "c", "c")
    assert(strings.size == 3)

    // Заметьте, что read-only типы ковариантны.
    // Это значит, что вы можете взять List<Rectangle> (список прямоугольников)
    // и присвоить его List<Shape> (списку фигур) предполагая, что Rectangle наследуется от Shape.

    val covariantList: List<Rectangle> = listOf(Rectangle(15.0), Rectangle(34.0))
    val covariantListSuper: List<Shape> = covariantList
    // val covariantList2: List<Rectangle> = covariantListSuper  // Type mismatch

    // Безопасный геттер неизменяемой копии изменяемого листа
    val getterSafety : Controller = Controller()
    println(getterSafety.items)

}

open class Shape(_square: Double) { val square = _square }
class Rectangle(square: Double) : Shape(square)

class Controller {
    private val _items = mutableListOf<String>("Bob", "Tom")        // Изменяемый лист, фиксированная ссылка
    val items: List<String> get() = _items.toList()                 // Безопасный геттер неизменяемой копии изменяемого листа
}
