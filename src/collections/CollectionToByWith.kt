package com.learnxinyminutes.kotlin.collections

@OptIn(ExperimentalStdlibApi::class)
fun main(args: Array<String>) {
    /*
Do note the 3 red fonts items, which will be important to describe To, By, With

The Destination — where the result will be
The Key Selector — determined (Определяет) what key should the map item based on
The Value Transformer — determine (Определяет) how the value of the map item is formed.

Destination = Iterable.associate( Pair( Key_Selector, Value_Transformer ) )

In associate function, we decide everything.

Create a new destination
Decide (Определить) the key for each map item. In our example, it is it.firstName.
Decide (Определить) the value for each map item. In our example, it is it.lastName.

To — a custom destination to append your result to
By — a custom lambda to determine the key selector
With — a custom lambda to form the value result

     */

    val list = listOf(
        Data("John", "Ali"),
        Data("Naidu", "Tan"),
        Data("Evgenii", "Dmitry"))

    /*
    Returns a Map containing key-value pairs provided by transform function applied to elements of the given collection.
    Возвращает карту, содержащую пары ключ-значение, предоставленные функцией преобразования, примененной к элементам данной коллекции.

    fun <T, K, V> Iterable<T>.associate(
        transform: (T) -> Pair<K, V>
    ): Map<K, V>

     */

    var destination = list.associate { it -> Pair(it.firstName, it.lastName) }        // it - T data object
                                                                                      // data class Pair - Represents a generic pair of two values.
    println(destination)

    /* 1. “To” helper function to append to an existing Destination. In this case, it’s associateTo

    Whenever we see To, this would allow us to provide an existing destination to the function so that it could append the result to it.
    Всякий раз, когда мы видим To, это позволит нам указать существующее место назначения для функции, чтобы она могла добавить к нему результат.

    Populates and returns the destination mutable map with key-value pairs provided by transform function applied to each element of the given collection.
    Заполняет и возвращает изменяемую карту назначения парами ключ-значение, предоставленными функцией преобразования, примененной к каждому элементу данной коллекции.

    fun <T, K, V, M : MutableMap<in K, in V>> Iterable<T>.associateTo(
        destination: M,
        transform: (T) -> Pair<K, V>
    ): M
    */

    val charCodes = intArrayOf(72, 69, 76, 76, 79)      // Iterable<T>
    val byChar                                          // destination: M
    = mutableMapOf<Int, Char>()                         // M : MutableMap<in K, in V>
    charCodes.associateTo(byChar)                       // T.associateTo(M)
    { it to Char(it) }                                  // transform: (T) -> Pair<K, V>

    // Инициализация переменной для результата
    var destinationTo = mutableMapOf<String, String>()

    list.associateTo(destinationTo) { Pair(it.firstName, it.lastName) }
    // Другой вариант
    destinationTo.putAll( list.associate { Pair(it.firstName, it.lastName) })

    // Короткий вариант
    list.associateTo(destinationTo) { it.lastName to it.firstName }    // Creates a tuple of type Pair from this and that.

    // Самый короткий вариант, с переменной
    val destinationToShort = list.associateTo(HashMap()) { it.lastName to it.firstName }
    println(destinationToShort)

    /* 2. “By” helper function to determine the key selector. In this case, it’s associateBy
    Всякий раз, когда мы видим By, это в основном говорит о том, что у функции есть параметр для лямбда, который помогает нам определить селектор ключа.
    Returns a Map containing the elements from the given collection indexed by the key returned from keySelector function applied to each element.
    Возвращает карту, содержащую элементы из данной коллекции, проиндексированные ключом, возвращенным функцией keySelector, примененной к каждому элементу.
    fun <T, K> Iterable<T>.associateBy(
        keySelector: (T) -> K
    ): Map<K, T>
     */

    fun getKey(data: Data): String { return data.firstName }        // keySelector: (T) -> K
    fun getValue(data: Data): String { return data.lastName }       // valueTransform: (T) -> V

    var  destinationBy = list.associate { Pair(getKey(it), it.lastName) }

    /* associateBy is provided, with two lambdas, one of the key, the other for the value.
    Несмотря на то, что у Associate есть лямбда-выражения Key и Value, основная цель By - предоставить способ определения ключа.
    Returns a Map containing the values provided by valueTransform and indexed by keySelector functions applied to elements of the given collection.

    fun <T, K, V> Iterable<T>.associateBy(
        keySelector: (T) -> K,
        valueTransform: (T) -> V
    ): Map<K, V>
     */

    destinationBy = list.associateBy(::getKey, ::getValue)

    /* One good example is groupBy.
    Groups elements of the original array by the key returned by the given keySelector function applied to each element and returns a map where each group key is associated with a list of corresponding elements.
    The returned map preserves the entry iteration order of the keys produced from the original array.
    Группирует элементы исходного массива по ключу, возвращаемому данной функцией keySelector, примененной к каждому элементу, и возвращает карту, где каждый групповой ключ связан со списком соответствующих элементов.
    Возвращенная карта сохраняет порядок итераций ввода ключей, полученных из исходного массива.
    inline fun <T, K, V> Iterable<T>.groupBy(
        keySelector: (T) -> K,
        valueTransform: (T) -> V
    ): Map<K, List<V>>
     */
    val nameToTeam = listOf("Alice" to "Marketing", "Bob" to "Sales", "Carol" to "Marketing", "Jesus" to "Sales")
    val namesByTeam = nameToTeam.groupBy({ it.second }, { it.first })
    println(namesByTeam) // Map<K, List<T>> {Marketing=[Alice, Carol], Sales=[Bob]}

    var mutableNamesByTeam = mutableMapOf<String, MutableList<String>>()
    nameToTeam.groupByTo(mutableNamesByTeam, { it.second }, { it.first })

    // Короткая запись, без инициализации переменной
    mutableNamesByTeam = nameToTeam.groupByTo(HashMap(), { it.second }, { it.first })
    // same content as in namesByTeam map, but the map is mutable
    println("mutableNamesByTeam == namesByTeam is ${mutableNamesByTeam == namesByTeam}") // true


    /* 3. “With” helper function to determine the value result. In this case, it’s associateWith
    associateWith
    Returns a Map where keys are elements from the given collection and values are produced by the valueSelector function applied to each element.

    fun <K, V> Iterable<K>.associateWith(
        valueSelector: (K) -> V
    ): Map<K, V>
     */

    val wordsX = listOf("a", "abc", "ab", "def", "abcd", "Jesus")
    val withLength = wordsX.associateWith { it.length }
    println(withLength.keys) // [a, abc, ab, def, abcd]
    println(withLength.values) // [1, 3, 2, 3, 4]

    // To make it easier for us associateWith is provided.
    var destinationWith = list.associateWith(::getValue)

    // Two good examples are maxWith and minWith

}

data class Data(val firstName: String, val lastName: String)

/*

associateByTo
Populates and returns the destination mutable map with key-value pairs, where key is provided by the keySelector function applied to each element of the given collection and value is the element itself.
Заполняет и возвращает изменяемую карту назначения парами «ключ-значение», где ключ предоставляется функцией keySelector, применяемой к каждому элементу данной коллекции, а значением является сам элемент.

fun <T, K, M : MutableMap<in K, in T>> Iterable<T>.associateByTo(
    destination: M,
    keySelector: (T) -> K
): M


associateWithTo
Populates and returns the destination mutable map with key-value pairs for each element of the given collection, where key is the element itself and value is provided by the valueSelector function applied to that key.

fun <K, V, M : MutableMap<in K, in V>> Iterable<K>.associateWithTo(
    destination: M,
    valueSelector: (K) -> V
): M
 */
