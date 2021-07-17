package com.learnxinyminutes.kotlin.collections

fun main(args: Array<String>) {


    println("Transforming collections: map")
    /*
Let's continue with a classic when it comes to transforming collections: the map function! (Don’t be confused! The map function has nothing to do with the Map collection type. You can treat them as two completely different things.)

Just like the forEach function, the map function is of higher order. So, it:

Takes each element from our collection,
applies a function to it, and
creates another collection, containing the return values of those function applications.
The result of the map function doesn’t have to be the same type as the one of our input collection, either.

This makes the map function very versatile – whether you want to parse a collection of strings into a collection of integers, or resolve a list of user names to a list of full user profiles –– if you’re transforming one collection into another, it’s probably a good first instinct to think map.
     */

    var fruits = listOf(
        "Apple",
        "Banana",
        "Cherry"
    )
    val stiurf = fruits.map {
        it.reversed()
    }
    println(stiurf)

    /*
    However, you might have a transformation inside your map function where you can’t generate valid results for all input elements. In this case, we can use the mapNotNull function, and our resulting collection will only contain those function results that evaluated to an actual value. This also ensures that type of our resulting variable is non-nullable.
     */
    val strs = listOf(
        "1",
        "2",
        "three",
        "4",
        "V"
    )

    // Комбинация двух функций mapNotNull и toIntOrNull безопасна и избавляет от неверных результатов
    val nums: List<Int> = strs.mapNotNull {
        it.toIntOrNull()
    }

    println(nums)

    val rank = listOf(
        "Gold",
        "Silver",
        "Bronze"
    )

    // If we need to keep track of the index of the element which we’re currently transforming, we can use the mapIndexed function.
    val ranking = rank.mapIndexed { idx, m ->
        "$m ($idx)"
    }

    println(ranking)

    println("Filtering collections: filter and partition")
    /*
    Предикат - это функция, которая принимает элемент коллекции и возвращает логическое значение: true означает, что данный элемент соответствует предикату, false означает противоположное. Таким образом, этот предикат действует как «швейцар» - если значение истинно, элемент коллекции передается в коллекцию результатов, в противном случае он отбрасывается.
     */

    val people = listOf(
        PersonZ("Joe", 15),
        PersonZ("Agatha", 25),
        PersonZ("Amber", 19),
        Cyborg("Rob")
    )

    val discoVisitors = people.filter {
        it.age >= 18
    }

    println(discoVisitors)

    // If you’re testing a negative condition, you can use the filterNot function instead, which behaves identically, but inverts the condition.
    val students = people.filterNot {
        it.age >= 18
    }

    println(students)

    // By using partition, we combine the powers of filter and filterNot. It returns a pair of lists, where the first list contains all the elements for which the predicate holds true, and the second contains all the elements that fail the test
    val (adults, children) = people.partition {
        it.age >= 18
    }

    println(adults)
    println(children)

    // If you’re bringing a collection of nullable items to the party, you can use the filterNotNull function which, as you may have guessed, automatically discards any elements that are null, and gives you a new collection with an adjusted, non-nullable type accordingly.
    val peopleZ = listOf(
        PersonZ("Joe", 15),
        null,
        PersonZ("Agatha", 25),
        null,
        PersonZ("Amber", 19),
        Cyborg("Rob"),
        null,
    )

    val actualPeople = peopleZ.filterNotNull()

    println(actualPeople)

    // Speaking of adjusting types – if your collection contains multiple elements from a type hierarchy, but you’re only interested in elements of a specific type, you can use filterIsInstance, and specify the desired type as a generic parameter.
    val cyborgs = peopleZ.filterIsInstance<Cyborg>()

    println(cyborgs)

    println("Retrieve collection parts: take and drop")


    var objects = listOf("🌱", "🚀", "💡", "🐧", "⚙️")

    val seedlingAndRocket = objects.take(2)

    println(seedlingAndRocket)

    val penguinAndGear = objects.drop(3)

    println(penguinAndGear)

    // And you don’t have to be afraid to “overdrop” either – dropping more elements from a collection than it contains just gives you an empty list:
    val nothing = objects.drop(8)

    println(nothing)
    println(objects)

    println("Aggregating collections: sums, averages, minimums, maximums, and counting")

    val randomNumbers = listOf(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 6)

    println(randomNumbers.average())
    println(randomNumbers.sum())

    val randomNames = listOf("Dallas", "Kane", "Ripley", "Lambert")

    // sumOf will use the result of that selector function to add up all our elements.
    val cumulativeLength = randomNames.sumOf { it.length }

    println(cumulativeLength)

    // If we’re only interested in the greatest or smallest value contained in our collection of numbers, we can use the maxOrNull and minOrNull functions.
    println(randomNumbers.minOrNull())
    println(randomNumbers.maxOrNull())

    // And just like sumBy, we have the sibling functions maxOf and minOf, where we once again pass a selector function, which is going to be used to determine the maximum or minimum of a collection.
    val longestName = randomNames.maxOf { it.length }
    println(longestName)

    val shortestName = randomNames.minOf { it.length }
    println(shortestName)

    val digits = randomNumbers.count()
    println(digits)

    val bigDigits = randomNumbers.count { it > 5 }
    println(bigDigits)

    // There’s also the powerful joinToString function, which allows us to turn all elements of our collection into a string, complete with a metric ton of customization options like separators, prefixes and postfixes, limits or a placeholder if you have more elements than what your specified limit allows.
    val str = randomNumbers.joinToString (
        separator = "-",
        prefix = "pi://",
        limit = 5
    ) {
        "[$it]"
    }

    println(str)

    println("Checking predicates: any, none and all")

    val friendGroup = listOf(
        PersonD("Jo", 19),
        PersonD("Mic", 15),
        PersonD("Hay", 33, true),
        PersonD("Cal", 25)
    )

    // It returns true if there is at least one element in our collection for which the predicate returns true.
    val groupCanTravel = friendGroup.any { it.driversLicense }
    println(groupCanTravel)

    // Here, we can use the none function, which only returns true when there is not a single element in our collection that holds true for our predicate:
    // Здесь мы можем использовать функцию none, которая возвращает true только тогда, когда в нашей коллекции нет ни одного элемента, который соответствует нашему предикату:
    val groupGetsInClub = friendGroup.none { it.age < 18 }
    println(groupGetsInClub)

    // все возвращает истину, если каждый элемент в нашей коллекции соответствует нашему предикату.
    val groupHasShortNames = friendGroup.all { it.name.length < 4 }
    println(groupHasShortNames)

    println("Predicates for empty collections")

    // How do any, none, and all behave for empty collections?
    val nobody = emptyList<PersonD>()


    println(
        nobody.any { it.driversLicense } // false
    )

    println(
        nobody.none { it.age < 18 } // true
    )

    println(
        nobody.all { it.name.count() < 4 } // true
    )
    // Но это сделано намеренно и разумно: вы не можете назвать элемент, который нарушает предикат. Следовательно, предикат должен быть истинным для всех элементов в коллекции, даже если их нет!
    // Поначалу это может показаться немного утомительным, но вы обнаружите, что эта концепция, называемая пустой истиной, на самом деле очень хорошо сочетается с проверкой условий и выражением логики в программном коде.

    println("Collection parts: chunked and windowed")

    // the chunked function cuts our original collection into lists of lists, where each list has the specified size.
    objects = listOf("🌱", "🚀", "💡", "🐧", "⚙️", "🤖", "📚")
    println(
        objects.chunked(3)
    )
    // [[🌱, 🚀, 💡], [🐧, ⚙️, 🤖], [📚]]

    // Чтобы немедленно преобразовать только что созданные фрагменты, мы можем применить функцию преобразования. Например, мы можем изменить порядок элементов в результирующих списках, не выполняя отдельный вызов карты:
    println(
        objects.chunked(3) { it.reversed() }
    )
    // [[💡, 🚀, 🌱], [🤖, ⚙️, 🐧], [📚]]

    // эта функция генерирует «скользящее окно» нашей коллекции:
    println(
        objects.windowed(3)
    )
    // [[🌱, 🚀, 💡], [🚀, 💡, 🐧], [💡, 🐧, ⚙️], [🐧, ⚙️, 🤖], [⚙️, 🤖, 📚]]

    // We can change both window and step size, the latter being the number of elements that the window should “slide along” for each step:
    // Как вы можете видеть в приведенном выше примере, мы также можем контролировать, должен ли наш результат содержать частичные окна. Это меняет поведение, когда мы достигли конца нашей коллекции ввода и у нас заканчиваются элементы.
    println(
        objects.windowed(4, 2, partialWindows = true)
    )
    // [[🌱, 🚀, 💡, 🐧], [💡, 🐧, ⚙️, 🤖], [⚙️, 🤖, 📚], [📚]]

    // windowed also allows us to perform an additional transformation at the end, which can modify the individual windows directly:
    println(
        objects.windowed(4, 2, true) {
        it.reversed()
    })

    println("Un-nesting Collections: Flatten and Flatmap")

    // What if we want to un-nest these, turning them back into flat lists of elements?
    objects = listOf("🌱", "🚀", "💡", "🐧", "⚙️", "🤖", "📚")
    objects.windowed(4, 2, true) {
        it.reversed()
    }.flatten()

    println(objects)
    // [🐧, 💡, 🚀, 🌱, 🤖, ⚙️, 🐧, 💡, 📚, 🤖, ⚙️, 📚]

    // flatMap is like a combination of first using map, and then using flatten – It takes a lambda which generates a collection from each of the elements in our input collection:
    val lettersInNames = listOf("Lou", "Mel", "Cyn").flatMap {
        it.toList()
    }
    println(lettersInNames)
    // [L, o, u, M, e, l, C, y, n]

    // Если вы выполняете операцию со списком, который, в свою очередь, генерирует коллекцию для каждого из входных элементов, подумайте, может ли flatMap помочь вам упростить ваш код!

    println("Combining collections: zip and unzip")

    val germanCities = listOf(
        "Aachen",
        "Bielefeld",
        "München"
    )

    val germanLicensePlates = listOf(
        "AC",
        "BI",
        "M"
    )

    val citiesToPlates = germanCities.zip(germanLicensePlates)
    // Как видите, заархивировав эти две коллекции, мы получаем список пар, где каждая пара содержит элементы с одинаковым индексом из двух исходных коллекций.
    // Образно говоря, это похоже на молнию на куртке, где зубы совпадают один за другим
    println(citiesToPlates)
    // [(Aachen, AC), (Bielefeld, BI), (München, M)]

    // The standard library also contains the inverse function, called unzip, which takes a list of pairs, and splits them back into a pair of two separate lists:
    val (cities, plates) = citiesToPlates.unzip()

    println(cities)
    // [AACHEN, BIELEFELD, MÜNCHEN]
    println(plates)
    // [ac, bi, m]

    // In a way, zipWithNext is really a specialized case of the windowed function we got to know today: Instead instead of zipping together two separate lists element by element, this function takes one collection, and zips each of its items with the one that follows it:
    var random = listOf(3, 1, 4, 1, 5, 9, 2, 6, 5, 4)
    println(random.zipWithNext())
    // [(3, 1), (1, 4), (4, 1), (1, 5), (5, 9), (9, 2), (2, 6), (6, 5), (5, 4)]

    // Если мы хотим проверить «изменение» - насколько значение увеличивается или уменьшается на каждом шаге - мы можем довольно элегантно выразить это с помощью zipWithNext. Мы предоставляем лямбду, которая принимает пару из одного числа и следующего сразу за ним:
    val changes = random.zipWithNext { a, b -> b - a }
    println(changes)
    // [-2, 3, -3, 4, 4, -7, 4, -1, -1]

    println("Custom aggregations: reduce and fold")

    // In this case, we can rely on the reduce function as a more generic version for aggregating a collection:
    val multiplicativeAggregate = random.reduce { acc, value -> acc * value }
    // acc - An accumulator, which has the same type as our collection, and
    // value - An individual item from our collection.

    println(multiplicativeAggregate) // 129600
    // Как видите, с помощью reduce мы можем скрыть множество механизмов агрегирования нашей коллекции за одним вызовом функции и оставаться верными лаконичному характеру Kotlin.

    // С помощью функции fold мы можем указать наш собственный аккумулятор - и на самом деле он может даже иметь другой тип, чем элементы в нашей коллекции ввода!
    // В качестве примера, мы можем взять список слов и умножить количество их символов вместе, используя fold:
    fruits = listOf("apple", "cherry", "banana", "orange")
    val multiplied = fruits.fold(1) { acc, value ->
        acc * value.length
    }
    // Базовый механизм тот же - лямбда, переданная функции свертки, вызывается с аккумулятором и значением и вычисляет новый аккумулятор.
    // Отличие в том, что мы сами указываем начальное значение аккумулятора. "1" так как у нас умножение.
    println(multiplied) // 1080

    // родственные функции reduceRight и foldRight изменяют порядок итерации
    random = listOf(3, 1, 4, 1, 5, 9, 2, 6, 5, 4)
    val minusAggregate = random.reduceRight{ acc, value -> acc - value }
    println(minusAggregate)

    //reduceOrNull позволяет работать с пустыми коллекциями без исключения исключений.

    var strings = listOf("a", "b", "c", "d") // Returns null if the array is empty.
    println(strings.reduceOrNull { acc, string -> acc + string }) // abcd
    println(strings.reduceIndexedOrNull { index, acc, string -> acc + string + index }) // ab1c2d3

    val randomNull = listOf<Int>() // Returns null if the array is empty.
    val multiplicativeAggregateNull = randomNull.reduceOrNull{ acc, value -> acc * value }
    println(multiplicativeAggregateNull)

    //runningFold и runningReduce не просто возвращают одно значение, представляющее конечное состояние аккумулятора, но вместо этого также возвращают список всех промежуточных значений аккумулятора.
    println(strings.runningReduce { acc, string -> acc + string }) // [a, ab, abc, abcd]
    println(strings.runningReduceIndexed { index, acc, string -> acc + string + index }) // [a, ab1, ab1c2, ab1c2d3]

    println(emptyList<String>().runningReduce { _, _ -> "X" }) // []

    println(strings.runningFold("s") { acc, string -> acc + string }) // [s, sa, sab, sabc, sabcd]
    println(strings.runningFoldIndexed("s") { index, acc, string -> acc + string + index }) // [s, sa0, sa0b1, sa0b1c2, sa0b1c2d3]

    println(emptyList<String>().runningFold("s") { _, _ -> "X" }) // [s]
}

data class PersonD(val name: String, val age: Int, val driversLicense: Boolean = false)


open class PersonZ(val name: String, val age: Int) {
    override fun toString() = name
}

class Cyborg(name: String) : PersonZ(name, 99)
