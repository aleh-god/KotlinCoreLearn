package collections

@ExperimentalStdlibApi
fun main(args: Array<String>) {
/*
List<T> stores elements in a specified order and provides indexed access to them.
Indices start from zero – the index of the first element – and go to lastIndex which is the (list.size - 1).

List <T> хранит элементы в указанном порядке и предоставляет к ним индексированный доступ.
Индексы начинаются с нуля - индекса первого элемента - и переходят к lastIndex, который равен (list.size - 1).
 */

    val anyList2 = listOf("bezkoder.com", 2019, null)
    // [bezkoder.com, 2019, null]
    val anyList3 = listOfNotNull("bezkoder.com", 2019, null)
    // [bezkoder.com, 2019]

    var numbers = listOf("one", "two", "three", "four")
    println("Number of elements: ${numbers.size}")
    println("Third element: ${numbers.get(2)}")
    println("Fourth element: ${numbers[3]}")
    println("Index of element \"two\" ${numbers.indexOf("two")}")

/*
List elements (including nulls) can duplicate: a list can contain any number of equal objects or occurrences of a single object.
Two lists are considered equal if they have the same sizes and structurally equal elements at the same positions.

Элементы списка (включая пустые значения) могут дублироваться: список может содержать любое количество одинаковых объектов или экземпляров одного объекта.
Два списка считаются равными, если они имеют одинаковые размеры и структурно одинаковые элементы в одинаковых позициях.
*/

    val bob = Person("Bob", age = 31)
    var people = listOf(Person("Adam", age = 20), bob, bob)
    val people2 = listOf(Person("Adam", age = 20), Person("Bob", age = 31), bob)
    println("equal = ${people == people2}")
    bob.age = 32
    println("equal, try two = ${people == people2}")

/*
MutableList<T> is a List with list-specific write operations, for example, to add or remove an element at a specific position.
*/

    val numbersX = mutableListOf(1, 2, 3, 4)
    numbersX.add(5)
    numbersX.removeAt(1)
    numbersX[0] = 0
    numbersX.shuffle()
    println(numbersX)

/*
As you see, in some aspects lists are very similar to arrays.
However, there is one important difference: an array's size is defined upon initialization and is never changed; in turn,
a list doesn't have a predefined size; a list's size can be changed as a result of write operations: adding, updating, or removing elements.

In Kotlin, the default implementation of List is ArrayList which you can think of as a resizable array.

Как видите, в некоторых аспектах списки очень похожи на массивы.
Однако есть одно важное отличие: размер массива определяется при инициализации и никогда не изменяется; в очереди,
список не имеет предопределенного размера; размер списка может быть изменен в результате операций записи: добавления, обновления или удаления элементов.

В Kotlin реализацией List по умолчанию является ArrayList, который можно рассматривать как массив с изменяемым размером.
 */

    // interface List<out E> : Collection<E>
    // E - the type of elements contained in the list. The list is covariant in its element type.

    val numberList = listOf(1, 2, 3, 4, 5, 10, 15, 20, 30, 50, 100)
    val stringList = listOf("one", "two", "three", "four")
    val peopleList = listOf(Person("Adam", age = 20), Person("Eva", age = 19), Person("Jesus", age = 33), Person("Abraham", age = 175))

    var resultInt = 0
    var resultBoolean = false
    var resultString = "result"

    println("\nProperties: .size")

    resultInt = numberList.size // Returns the size of the collection.
    println("Returns the size of the collection = $resultInt")

    println("\nFunctions: .contains() .containsAll() .get() .indexOf() .lastIndexOf() .isEmpty() .listIterator() .iterator() .subList()")

    resultBoolean = numberList.contains(30) // Checks if the specified element is contained in this collection.
    println("Checks if the specified element is contained in this collection = $resultBoolean")

    resultBoolean = stringList.containsAll(listOf("one", "two", "three", "four")) // Checks if all elements in the specified collection are contained in this collection.
    println("Checks if all elements in the specified collection are contained in this collection = $resultBoolean")

    resultString = stringList.get(3)
    println("Returns the element at the specified index in the list = $resultString")

    resultInt = numberList.indexOf(10)
    println("Returns the index of the first occurrence of the specified element in the list = $resultInt")

    resultInt = numberList.indexOf(9)
    println("Or returns -1 if the specified element is not contained in the list = $resultInt")

    resultInt = numberList.lastIndexOf(50)
    println("Returns the index of the last occurrence of the specified element in the list, or -1 if the specified element is not contained in the list = $resultInt")

    resultBoolean = stringList.isEmpty()
    println("Returns true if the collection is empty (contains no elements), false otherwise = $resultBoolean")


    println("stringList.listIterator() - Returns a list iterator")

    val listIterator = stringList.listIterator() // Returns a list iterator over the elements in this list (in proper sequence).

    while (listIterator.hasNext()) {      // Returns true if the iteration has more elements.
        print("Index: ${listIterator.nextIndex()}") // Returns the index of the element that would be returned by a subsequent call to next.
        println(", value: ${listIterator.next()}")  // Returns the next element in the iteration.
    }

    println("Iterating backwards:")

    while (listIterator.hasPrevious()) {        // Returns true if there are elements in the iteration before the current element.
        print("Index: ${listIterator.previousIndex()}")     // Returns the index of the element that would be returned by a subsequent call to previous.
        println(", value: ${listIterator.previous()}")  // Returns the previous element in the iteration and moves the cursor position backwards.
    }

    println(".subList() - Creates a sublist between the specified fromIndex (inclusive) and toIndex (exclusive):")
    numberList.subList(3,6).forEach { print(" Element it = $it") }

    println("\n\nExtension Properties: .indices .lastIndex")

    // class IntRange : IntProgression, ClosedRange<Int> // KotlinRu.A range of values of type Int.
    val intRangeList: IntRange = numberList.indices
    println("intRangeList.isEmpty() is ${intRangeList.isEmpty()}")
    println("Диапазон индексов заданного List = ${ numberList.indices }")
    println("Первое значение диапазона IntRange индексов заданного List = ${ numberList.indices.start }")
    println("Последнее значение диапазона IntRange индексов заданного List = ${ numberList.indices.endInclusive }")
    println("Returns the index of the last item in the list or -1 if the list is empty = ${ numberList.lastIndex }")
    println("Последний элемент Листа, не зная размер Листа = ${ numberList[numberList.lastIndex] }")

    println("\nExtension Functions")

    val isEven: (Int) -> Boolean = { it % 2 == 0 }
    val zeroToTen = 0..10

    println("\n.all - Returns true if all elements match the given predicate(условие).")

    println("zeroToTen.all { isEven(it) } is ${zeroToTen.all { isEven(it) }}") // false
    println("zeroToTen.all(isEven) is ${zeroToTen.all(isEven)}") // false

    resultBoolean = listOf(2,2,2).all({ it == 2 })
    println("listOf(2,2,2).all({ it == 2 }) = $resultBoolean")

    val evens = zeroToTen.map { it * 2 }
    println("map { zeroToTen * 2 } is ${evens.all { isEven(it) }}") // true
    var emptyList = emptyList<Int>()
    println("emptyList.all { false } is ${emptyList.all { false }}") // true

    println("\n.any - Returns true if array has at least(наименее, хотя бы) one element.")

    emptyList = emptyList<Int>()
    println("emptyList.any() is ${emptyList.any()}") // false

    var nonEmptyList = listOf(1, 2, 3)
    println("nonEmptyList.any() is ${nonEmptyList.any()}") // true

    println("Returns true if at least one element matches the given predicate.")
    println("zeroToTen.any { isEven(it) } is ${zeroToTen.any { isEven(it) }}") // true
    println("zeroToTen.any(isEven) is ${zeroToTen.any(isEven)}") // true

    val odds = zeroToTen.map { it * 2 + 1 }
    println("odds.any { isEven(it) } is ${odds.any { isEven(it) }}") // false

    println("emptyList.any { true } is ${emptyList.any { true }}") // false

    val moreThenFive: (Int) -> Boolean = { it > 4 }
    println("(1, 2, 3) moreThenFive is ${nonEmptyList.any { moreThenFive(it) }}")
    println("zeroToTen moreThenFive is ${zeroToTen.any(moreThenFive)}")

    println("\n.asSequence() - Creates a sequence that returns all elements from this listIterator():")
    val sequence = stringList.listIterator().asSequence()
    println(sequence.drop(1).joinToString()) // two, three, four

    println("\n.asReversed() - Returns a reversed read-only view of the original List.")
    val original = mutableListOf('a', 'b', 'c', 'd', 'e')
    val originalReadOnly = original as List<Char>
    val reversed = originalReadOnly.asReversed()

    println(original) // [a, b, c, d, e]
    println(reversed) // [e, d, c, b, a]

    // All changes made in the original list will be reflected in the reversed one.
    println("changing the original list affects its reversed view")
    original.add('f')
    println(original) // [a, b, c, d, e, f]
    println(reversed) // [f, e, d, c, b, a]

    original[original.lastIndex] = 'z'
    println(original) // [a, b, c, d, e, z]
    println(reversed) // [z, e, d, c, b, a]

    println("\n.asReversedMutable() - Returns a reversed mutable view of the original mutable List")

    val originalX = mutableListOf(1, 2, 3, 4, 5)
    val reversedX = originalX.asReversed()

    println(originalX) // [1, 2, 3, 4, 5]
    println(reversedX) // [5, 4, 3, 2, 1]

    println("All changes made in the original list will be reflected in the reversed one and vice versa.")
    // changing the reversed view affects the original list
    reversedX.add(0)
    println(originalX) // [0, 1, 2, 3, 4, 5]
    println(reversedX) // [5, 4, 3, 2, 1, 0]

    // changing the original list affects its reversed view
    originalX[2] = -originalX[2]
    println(originalX) // [0, 1, -2, 3, 4, 5]
    println(reversedX) // [5, 4, 3, -2, 1, 0]

    println("\n.asSequence() - Creates a Sequence instance that wraps the original collection returning its elements when being iterated.")
    val collection = listOf('a', 'b', 'c')
    val sequenceX = collection.asSequence()
    println(sequenceX.joinToString()) // a, b, c

    println("\n.associate(transform: (T) -> Pair<K, V>) - Returns a Map containing key-value pairs provided by transform function applied to elements of the given collection.")
    // transform: (T) -> Pair<K, V> - это переменная, в которую можно поместить лямбду
    // If any of two pairs would have the same key the last one gets added to the map.
    //The returned map preserves the entry iteration order of the original array.
    val names = listOf("Grace Hopper", "Jacob Bernoulli", "Johann Bernoulli")
    val byLastName = names.associate { it.split(" ").let { (firstName, lastName) -> lastName to firstName } }
    // Jacob Bernoulli does not occur in the map because only the last pair with the same key gets added
    println(byLastName) // {Hopper=Grace, Bernoulli=Johann}

    println(".associateBy( (T) -> K, (T) -> V ) Returns a Map containing the values provided by valueTransform and indexed by keySelector functions applied to elements of the given array.")
    // Из массива или Итерабле в Мапу, через: Значение из массива, ключ через функцию с параметром значения массива
    // Можно через две функции:
    // keySelector: (T) -> K - это переменная для лямбды, соотв типа.
    // valueTransform: (T) -> V - это переменная для лямбды, соотв типа
    var scientists = listOf(Person("Grace", "Hopper"), Person("Jacob", "Bernoulli"), Person("Johann", "Bernoulli"))
    val byLastNameBY = scientists.associateBy { it.lastName } // lastName - ключ, значение - collections.Person
    println(byLastNameBY)

    println(".associateByTo(T, K, V, M) - Populates and returns the destination mutable map with key-value pairs, where key is provided by the keySelector function and and value is provided by the valueTransform function applied to elements of the given collection.")
    scientists = listOf(Person("Grace", "Hopper"), Person("Jacob", "Bernoulli"), Person("Johann", "Bernoulli"))
    val byLastNameByTo = mutableMapOf<String, String>()
    println("byLastName.isEmpty() is ${byLastNameByTo.isEmpty()}") // true
    scientists.associateByTo(byLastNameByTo, { it.lastName }, { it.name } )
    println("byLastName.isNotEmpty() is ${byLastName.isNotEmpty()}") // true
    // Jacob Bernoulli does not occur in the map because only the last pair with the same key gets added
    println(byLastName) // {Hopper=Grace, Bernoulli=Johann}

    println(".associateWith( valueSelector: (K) -> V ) - Returns a Map where keys are elements from the given array and values are produced by the valueSelector function applied to each element.")
    val words = listOf("a", "abc", "ab", "def", "abcd")
    val withLength = words.associateWith { it.length }
    println(withLength.keys) // [a, abc, ab, def, abcd]
    println(withLength.values) // [1, 3, 2, 3, 4]

    println(".binarySearch() - Searches this list or its range for the provided element using the binary search algorithm.")
    // The list is expected to be sorted into ascending order according to the Comparable natural ordering of its elements, otherwise the result is undefined.
    // If the list contains multiple elements equal to the specified element, there is no guarantee which one will be found.
    // null value is considered to be less than any non-null value.
    val list = mutableListOf('a', 'b', 'c', 'd', 'f')
    var invertedInsertionPoint = list.binarySearch('d') // 3
    println("invertedInsertionPoint = $invertedInsertionPoint \n remove('d')")
    list.remove('d')

    invertedInsertionPoint = list.binarySearch('d')
    println("invertedInsertionPoint = $invertedInsertionPoint")
    println("Вернуть индекс элемента, если он содержится в списке в указанном диапазоне; в противном случае - инвертированная точка вставки (-точка вставки - 1). Точка вставки определяется как индекс, по которому должен быть вставлен элемент, чтобы список (или указанный поддиапазон списка) по-прежнему оставался отсортированным. ")
    val actualInsertionPoint = -(invertedInsertionPoint + 1)
    println("actualInsertionPoint = -(invertedInsertionPoint + 1) = $actualInsertionPoint") // 3

    list.add(actualInsertionPoint, 'd')
    println(list) // [a, b, c, d, e]

    println(".binarySearchBy(key: 7) - Searches this list or its range for an element having the key returned by the specified selector function equal to the provided key value using the binary search algorithm.")
    val numbersZ = listOf(1, 3, 7, 10, 12)
    var boxes = numbersZ.map { Box(it) }
    println(boxes)
    println(boxes.binarySearchBy(7) { it.value }) // 3

    println(".chunked(3) - Splits this collection into a list of lists each not exceeding the given size.")//
    // List<List<T>> The last list in the resulting list may have fewer elements than the given size.
    // size - the number of elements to take in each list, must be positive and can be greater than the number of elements in this collection.
    val wordsX = "one two three four five six seven eight nine ten".split(' ')
    val chunks = wordsX.chunked(3)
    println(chunks) // [[one, two, three], [four, five, six], [seven, eight, nine], [ten]]

    val numbersY = listOf(1, 3, 7, 10, 12, 15)
    var elementFromTheArray: Int = numbersY.component1()
    println("1 element from the array = $elementFromTheArray")
    elementFromTheArray = numbersY.component2()
    println("2 element from the array = $elementFromTheArray")
    elementFromTheArray = numbersY.component3()
    println("3 element from the array = $elementFromTheArray")
    elementFromTheArray = numbersY.component4()
    println("4 element from the array = $elementFromTheArray")
    elementFromTheArray = numbersY.component5()
    println("5 element from the array = $elementFromTheArray")
    // elementFromTheArray = numbersY.component6() Error!

    numbers = listOf("one", "two", "three", "four")
    people = listOf(Person("Adam", age = 20), Person("Eva", age = 19), Person("Jesus", age = 33), Person("Abraham", age = 175))
    boxes = listOf(Box(1), Box(2), Box(5), Box(10))

    // Returns true if element is found in the collection.
    resultBoolean = numbers.contains("two")
    resultBoolean = people.contains(Person("Eva", age = 19))
    resultBoolean = boxes.contains(Box(10))

    val collectionZ = mutableListOf('a', 'b')
    val test = listOf('a', 'b', 'c')
    println("collection.containsAll(test) is ${collectionZ.containsAll(test)}") // false
    collectionZ.add('c')
    println("collection.containsAll(test) is ${collectionZ.containsAll(test)}") // true

    // Returns the number of elements in this array.
    resultInt = numbers.count({it == "one"})
    println(resultInt)
    resultInt = people.count()
    println(resultInt)
    resultInt = boxes.count { it.value > 3 }
    println(resultInt)

    // Returns a list containing only distinct elements from the given array.
    // Возвращает список, содержащий только отдельные элементы из данного массива.
    val listD = listOf('a', 'A', 'b', 'B', 'A', 'a')
    println(listD.distinct()) // [a, KotlinRu.A, b, KotlinRu.B]
    println(listD.distinctBy { it.uppercaseChar() }) // [a, b]

    // Returns a list containing all elements except first n elements.
    // Возвращает список, содержащий все элементы, кроме первых n элементов.
    val chars = ('a'..'z').toList()
    println(chars.drop(23)) // [x, y, z]
    // Returns a list containing all elements except last n elements.
    println(chars.dropLast(23)) // [a, b, c]
    // Returns a list containing all elements except last elements that satisfy the given predicate.
    println(chars.dropWhile { it < 'x' }) // [x, y, z]
    println(chars.dropLastWhile { it > 'c' }) // [a, b, c]

    // Возвращает элемент по заданному индексу или генерирует исключение IndexOutOfBoundsException, если индекс выходит за пределы этого массива.
    val listA = listOf(1, 2, 3)
    println(listA.elementAt(0)) // 1
    println(listA.elementAt(2)) // 3
    // list.elementAt(3) // will fail with IndexOutOfBoundsException

    val emptyListA = emptyList<Int>()
    // emptyList.elementAt(0) // will fail with IndexOutOfBoundsException

    println(listA.elementAtOrElse(2) { 42 }) // 3
    println(listA.elementAtOrElse(3) { 42 }) // 42
    println(listA.elementAtOrElse(3) { "Out of range" }) // 1
    println(listA.elementAtOrNull(3)) // null

    println(emptyListA.elementAtOrElse(0) { "no int" }) // no int
    println(emptyListA.elementAtOrNull(0)) // null

    val dangerList: List<String>?  = listOf("Jesus", "Abraham")
    val danger: List<String>  = listOf("Jesus", "Abraham")
    // dangerList.forEach { print(it) } ошибка компиляции, экс-функции не могут работать с null
    dangerList.orEmpty().forEach { print(it) } // Если null, то возвращает пустой список
    print(danger.getOrElse(0) { "Adam" } ) // Если нету значения, то выполняется лямбда функция, которая возвращает литерал
    danger
        .takeIf { it.first() == "S" } // если false, то возвращает null
        ?.let { print("result = $it") }



}

data class Box(val value: Int)