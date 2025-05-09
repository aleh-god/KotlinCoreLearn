package com.learnxinyminutes.kotlin.KotlinRu

import kotlin.random.Random

fun main(args: Array<String>) {

    /*
    Стандартная библиотека Kotlin содержит несколько функций, единственной целью которых является выполнение блока кода в контексте объекта.
    Эти функции формируют временную область видимости для объекта, к которому были применены, и вызывают код, указанный в переданном лямбда-выражении.
    В этой области видимости можно получить доступ к объекту без явного к нему обращения по имени.
    Такие функции называются функциями области видимости (англ. scope functions). Всего их пять: let, run, with, apply, и also.
    По сути, все эти функции делают одно и то же: выполняют блок кода для объекта.
    Отличие состоит в том, как этот объект становится доступным внутри блока и каков результат всего выражения.

    run, запустить
    with, с участием
    apply, применить

    let, позволить
    also, также

    Между ними есть два основных различия:
    * Способ ссылки на контекстный объект
    * Возвращаемое значение.

    * Способ ссылки на контекстный объект
    Каждая функция области видимости использует один из двух способов доступа к объекту контекста: как лямбда-получатель (this) или как лямбда-аргумент (it)
    this
    -> run, with и apply ссылаются на объект контекста как лямбда-получатель
    в их лямбдах объект доступен, как это было бы в обычных функциях класса.
    this рекомендуется для лямбд, которые в основном работают с членами объекта: вызывают его функции или присваивают свойства.

    it
    -> let и also передают контекстный объект как аргумент в лямбду fun(argument)
    к объекту обращаются неявным способом при помощи ключевого слова it
    Однако при вызове функций или свойств объекта у вас не будет доступа к такому неявному объекту как this.

    * Возвращаемое значение.
    apply и also возвращают объект контекста.
    let, run и with возвращают результат лямбды.

    Контекстный объект
    Функции apply и also возвращают объект контекста.
    Следовательно, с их помощью можно вызвать длинную цепочку функций относительно оригинального контекстного объекта.
    Такая цепочка функций известна как side steps.

     */

    // Обычный код через переменную val
    val alice = PersonZ("Alice", 20, "Amsterdam")
    println(alice)
    alice.moveTo("London")
    alice.incrementAge()
    println(alice)

    // Код с let
    // Функции области видимости не предоставляют никаких новых технических возможностей, но они могут сделать ваш код более кратким и читабельным.
    PersonZ("Alice", 20, "Amsterdam").let {
        println(it)
        it.moveTo("London")
        it.incrementAge()
        println(it)
    }

    // В большинстве случаев this можно опустить при доступе к элементам объекта-получателя, что сделает код короче.
    val adam = PersonZ("Adam").apply {
        age = getRandomInt()            // Можно обрабатывать данные, не обращаясь к ссылке на объект
        city = "London"                 // this.city = 20 или adam.city = 20
    }

    println(adam.toString())

    fun writeToLog(message: String) {
        println("INFO: $message")
    }

    fun getRandomInt(): Int {
        // Должны вернуть Int. Для этого создали объект Random.nextInt(100). Дополнительно нужно вызвать функцию writeToLog с использованием объекта
        return Random.nextInt(100).also {
            writeToLog("Метод getRandomInt() сгенерировал значение $it")
            // когда вы передаете объект в качестве аргумента, вы можете указать пользовательское имя для этого объекта внутри области видимости.
            // value -> writeToLog("Метод getRandomInt() сгенерировал значение $value")
        }
    }
    val i = getRandomInt()

    // Возвращаемое значение

    // apply и also - возвращают Контекстный объект
    // obj.apply { this } -> obj
    // obj.also { it } -> obj

    // цепочка функций, известна как side steps.
    val numberList = mutableListOf<Double>()

    numberList.also { println("Заполнение списка") }    // it можно опустить
        .apply {
            this.add(2.71)                                   // this можно пропустить
            add(3.14)
            add(1.0)
        }
        .also { println("Сортировка списка") }      // Здесь Scope-fun и лямбда { it -> Unit }
        .sort()                                     // Здесь обычная функция
    println(numberList)

    // let, run и with возвращают результат лямбды.
    // .run { this } -> result
    // with() { this } -> result
    // .let { it } -> result

    // Таким образом, вы можете использовать их при присваивании переменной результата вычислений, либо использовать результат для последующего вызова цепочки операций и тд.
    val numbers = mutableListOf("one", "two", "three", "four", "five")

    val countEndsWithE = numbers.run {
        this.add("six")
        add("seven")
        add("nine")
        add("ten")
        // Результат лямбды последнее выражение
        count { it.endsWith("e") }              // this.count можно пропустить
    }
    println("Элементы в $countEndsWithE, которые заканчиваются на e.")

    // Кроме того, вы можете игнорировать возвращаемое значение и использовать функцию для создания временной области видимости для переменной.
    with(numbers) {
        val firstItem = first()
        val lastItem = last()
        println("Первый элемент: $firstItem, последний элемент: $lastItem")
    }

    // let
    //Контекстный объект доступен в качестве аргумента (it). Возвращаемое значение - результат выполнения лямбды.

    // let позволяет использовать полученный результат для вызова одной или нескольких функций в блоке кода.
    val resultList = numbers.map { it.length }.filter { it > 3 }
    println(resultList)

    // С функцией let это код может быть переписан следующим образом:

    numbers.map { it.length }.filter { it > 3 }.let {
        println(it)
        // при необходимости можно вызвать больше функций
        println("Первый элемент ${ it.first() }")
    }

    // let часто используется для выполнения блока кода только с non-null значениями.
    // Чтобы выполнить действия с non-null объектом, используйте оператор безопасного вызова ?. совместно с функцией let.

    val str: String? = "Hello or null"
    fun processNonNullString(str: String) { println(str) }
    // processNonNullString(str)       // compilation error: str может быть null

    val length = str?.let {
        println("Вызов функции let() для $it")
        processNonNullString(it)      // OK: 'it' не может быть null внутри конструкции '?.let { }'
        it.length
    }

    // Еще один вариант использования let - это введение локальных переменных с ограниченной областью видимости для улучшения читабельности кода.
    // Чтобы определить новую переменную для контекстного объекта, укажите ее имя в качестве аргумента лямбды, чтобы ее можно было использовать вместо ключевого слова it.
    val modifiedFirstItem = numbers.first().let {
            firstItem -> println("Первый элемент в списке: '$firstItem'")
            if (firstItem.length >= 5) firstItem else "!$firstItem!"
    }.toUpperCase()
    println("Первый элемент списка после изменений: '$modifiedFirstItem'")

    // with
    // Не является функцией-расширением. Контекстный объект передается в качестве аргумента,
    // а внутри лямбда-выражения он доступен как получатель (this). Возвращаемое значение - результат выполнения лямбды.

    // Функцию with рекомендуется использовать для вызова функций контекстного объекта без предоставления результата лямбды.
    // В коде with может читаться как " с этим объектом, сделайте следующее. "
    with(numbers) {
        println("'with' вызывает с аргументом $this")
        println("Список содержит $size элементов")
        // результат выполнения лямбды нам не нужен
    }

    // Другой вариант использования with - введение вспомогательного объекта, свойства или функции которые будут использоваться для вычисления значения.
    val firstAndLast = with(numbers) {
        "Первый элемент списка - ${first()}," +
                " последний элемент списка - ${last()}"
    }
    println(firstAndLast)

    // run
    //Контекстный объект доступен в качестве получателя (this). Возвращаемое значение - результат выполнения лямбды.
    //run делает то же самое, что и with, но вызывается как let - как функция расширения контекстного объекта.
    //run удобен, когда лямбда содержит и инициализацию объекта, и вычисление возвращаемого значения.
    val service = MultiportService("https://example.kotlinlang.org", 80)

    val result = service.run {
        this.port = 8080
        this.query(prepareRequest() + " порт - $port")
    }

    // аналогичный код с использованием функции let():
    val letResult = service.let {
        it.port = 8080
        it.query(it.prepareRequest() + " порт - ${it.port}")
    }

    println(result)
    println(letResult)

    // Помимо вызова run для объекта-получателя, вы можете использовать его как функцию без расширения.
    // В этом случае run позволяет выполнить блок из нескольких операторов там, где это требуется.
    val hexNumberRegex = run {
        val digits = "0-9"
        val hexDigits = "KotlinRu.A-Fa-f"
        val sign = "+-"

        Regex("[$sign]?[$digits$hexDigits]+")
    }

    for (match in hexNumberRegex.findAll("+1234 -FFFF not-a-number")) {
        println(match.value)
    }

    // apply
    // Контекстный объект доступен в качестве получателя (this). Возвращаемое значение - контекстный объект.
    // Используйте apply для такого блока кода, который не возвращает значение и в основном работает с членами объекта-получателя.
    // Типичный способ использования функции apply - настройка объекта-получателя.
    // Это все равно что мы скажем “примени перечисленные настройки к объекту.”
    val adamTwo = PersonZ("Adam").apply {
        age = 32
        city = "London"
    }
    println(adamTwo)

    // also
    // Контекстный объект доступен в качестве аргумента (it). Возвращаемое значение - контекстный объект.
    // also хорош для выполнения таких действий, которые принимают контекстный объект в качестве аргумента.
    // То есть, эту функции следует использовать, когда требуется ссылка именно на объект, а не на его свойства и функции.
    // Либо, когда вы хотите, чтобы была доступна ссылка на this из внешней области видимости.
    // Когда вы видите в коде also, то это можно прочитать как "а также с объектом нужно сделать следующее."
    numbers
        .also { println("Элементы списка перед добавлением нового: $it") }
        .add("four")

    // takeIf и takeUnless
    //Помимо функций области видимости, стандартная библиотека содержит функции takeIf и takeUnless. Эти функции позволяют встроить проверку состояния объекта в цепочке вызовов.
    //При вызове takeIf для объекта с предикатом этот объект будет возвращен, если он соответствует предикату. В противном случае возвращается null. В свою очередь, takeUnless возвращает объект, если он не соответствует предикату, и null, если соответствует. Объект доступен как лямбда-аргумент (it).
    val number = Random.nextInt(100)

    val evenOrNull = number.takeIf { it % 2 == 0 }
    val oddOrNull = number.takeUnless { it % 2 == 0 }
    println("четный: $evenOrNull, нечетный: $oddOrNull")

    // ри добавлении в цепочку вызовов других функций после takeIf и takeUnless, не забудьте выполнить проверку на null или используйте оператор безопасного вызова (?.), потому что их возвращаемое значение имеет тип nullable.
    val strX = "Hello"
    val caps = strX.takeIf { it.isNotEmpty() }?.toUpperCase()
    //val caps = str.takeIf { it.isNotEmpty() }.toUpperCase() //compilation error
    println(caps)

    fun displaySubstringPosition(input: String, sub: String) {

        // вызовите takeIf для объекта, а затем вызовите let с оператором безопасного вызова (?). Для объектов, которые не соответствуют предикату, takeIf возвращает null, а let не вызывается.
        input.indexOf(sub).takeIf { it >= 0 }?.let {
            println("Подстрока $sub находится в $input.")
            println("Начинается с индекса $it.")
        }
    }

    displaySubstringPosition("010000011", "11")
    displaySubstringPosition("010000011", "12")

    var size = 0

    // it - контекст
    size = "Hello".let {
        println(it)
        it.length
    }

    size = "Hello".also {
        println(it)
    }.length

    // this - объект приемник
    size = "Hello".run {
        println(this)
        this.length
    }

    size = "Hello".apply {
        println(this)
    }.length

}

class MultiportService(var url: String, var port: Int) {
    fun prepareRequest(): String = "Запрос по умолчанию"
    fun query(request: String): String = "Результат запроса: '$request'"
}

fun getRandomInt(): Int = Random.nextInt(100).also {
    println("Метод из пакета сгенерировал значение $it")
}

data class PersonZ(var name: String, var age: Int = 33, var city: String = "Nazareth") {
    fun moveTo(newCity: String) { city = newCity }
    fun incrementAge() { age++ }
}