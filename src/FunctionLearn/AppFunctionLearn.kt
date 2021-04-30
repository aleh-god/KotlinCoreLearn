package functionLearn

fun main(args: Array<String>){
    println("Hello FunctionLearn")

    var lesson: FunctionLearn

    lesson = FunctionsParameters()

    // Аргументы по умолчанию
    lesson.displayUser("Tom", 23, "Manager")
    lesson.displayUser("Alice", 21)
    lesson.displayUser("Kate")

    // используя именованные аргументы, мы можем переопределить порядок их передачи параметрам
    lesson.displayUser(name="Tom", position="Manager", age=28)
    lesson.displayUser(age=21, name="Alice")
    lesson.displayUser("Kate", position="Middle Developer")

    // Переменное количество параметров. Vararg

    // При вызове функции мы можем ей передать любое количество строк.
    lesson.printStrings("Kotlin", "JavaScript", "Java", "C#", "C++")
    lesson.sum(1, 2, 3, 4, 5, 6, 7, 8, 9)

    // Если функция принимает несколько параметров, то обычно vararg-параметр является последним.
    lesson.printUserGroup(3, "Tom", "Bob", "Alice")

    // Если после vararg-параметра идут еще какие-нибудь параметры, то при вызове функции значения этим параметрам передаются через именованные аргументы:
    lesson.printUserGroup2("KT-091", "Tom", "Bob", "Alice", count=3)

    // Оператор * (spread operator) (не стоит путать со знаком умножения) позволяет передать параметру в качестве значения элементы из массива:
    val users = arrayOf("Tom", "Bob", "Alice")
    lesson.printUserGroup2("MO-011", *users, count=3)

    // Возвращение результата. Оператор return
    lesson.factorialVoid(5)
    val a = lesson.factorial(4)

    // Каллбэк с помощью Unit
    lesson.getData("Callback fun") {
            str -> println(str) // лямбда вынесена из аргументов функции, в отдельный блок
    }

    // Этот код можно было написать так:
    lesson.getData("Callback fun short", {str -> println(str)})

    // Или с помощью анонимной функции:
    lesson.getData("Callback anon fun", fun (str: String) : Unit { println(str)})

    lesson = FunctionsType()

    // Однострочные функции

    var result : Int = lesson.simpleDouble(5)
    result = lesson.double(6)

    // Локальные функции

    val aGreater = lesson.isFirstGreater(10.0, 10.0, 20.0, 20.0)
    val bGreater = lesson.isFirstGreater(20.0, 20.0, 10.0, 10.0)
    println("a=$aGreater  b=$bGreater")

    // Перегрузка функций

    val aOverload = lesson.add(1, 2)
    val bOverload = lesson.add(1.5, 2.5)
    val cOverload = lesson.add(1, 2, 3)
    val dOverload = lesson.add(2, 1.5)
    val eOverload = lesson.add(1.5, 2)

    lesson = LambdaExpressions()
    lesson.showResult()

    // Передача лямбда-выражения в функцию

    val add = {x:Int, y: Int -> x+y}
    val multiply = {x:Int, y: Int -> x*y}

    // В самой функции action вызываем эту операцию, передавая ей два числа, и полученный результат выводим на консоль.
    //При вызове функции action мы можем передать для ее третьего параметра лямбда-выражение, которое соответствует этому параметру по типу:
    lesson.highOrderFunction(5, 3, add)
    lesson.highOrderFunction(5, 3, multiply)
    lesson.highOrderFunction(5, 3, {x: Int, y: Int -> x -y})

    // Возвращение функции из функции

    // переменная action хранит результат функции selectAction.
    // Так как selectAction возвращает лямбда-выражение, то и переменная action будет хранить определенное лямбда-выражение.
    var action = lesson.selectAction(1)
    // через переменную action можно вызвать это лямбда-выражение.
    println(action(8,5))    // 13

    action = lesson.selectAction(2)
    println(action(8,5))    // 3

    // Анонимные функции выглядят как обычные за тем исключением, что они не имеют имени.
    // Анонимная функция может иметь одно выражение:
    fun(x: Int, y: Int): Int = x - y // При этом оператор return не используется.

    // Либо может представлять блок кода:
    fun(x: Int, y: Int): Int {
        return x + y
    }

    lesson = FunctionsType()
    // Функция operation принимает три параметра.
    // Первые два параметра - числа, а третий параметр - функция тип (Int, Int) -> Int), которая выполняет некоторые действия над этими числами.
    // Передача анонимной функции в данном случае аналогична передачи лямбда-выражения
    lesson.operation(9,5, fun(x: Int, y: Int): Int { return x + y })   // 14
    lesson.operation(9,5, fun(x: Int, y: Int): Int = x - y)            // 4

    //lesson.showResult()

}