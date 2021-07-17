package com.learnxinyminutes.kotlin.coroutinesLearn

import kotlinx.coroutines.*

fun main() = runBlocking{
    println("")

    println("метод join() позволяет ожидать, пока корутина не завершится")
    main001()
    println("Отложенное выполнение")
    main002()
    println("еще один построитель корутин - функция async. Эта функция применяется, когда надо получить из корутины некоторый результат.")
    main003()
    println("мы можем с помощью async запустить несколько корутин, которые будут выполняться параллельно:")
    main004()
    println("Отложенный запуск")
    main005()
    println("Если необходимо, чтобы корутина еще до метода await() начала выполняться, то можно вызвать метод start()")
    main006()
}

suspend fun main001() = coroutineScope{

    val job = launch{
        for(i in 1..5){
            println(i)
            delay(400L)
        }
    }

    println("Start")
    println("ожидаем завершения корутины")
    // Здесь корутина также запускается с помощью launch, однако благодаря методу join() полученного объекта Job метод
    // функция остановит выполнение и будет ожидать завершения корутины и только после ее завершения продолжит работу
    job.join() // ожидаем завершения корутины
    println("End")
}

suspend fun main002() = coroutineScope{

    // Для установки отложенного запуска в функцию launch() передается значение start = CoroutineStart.LAZY
    // корутина создана, но не запущена
    val job = launch(start = CoroutineStart.LAZY) {
        delay(200L)
        println("Coroutine has started")
    }

    delay(1000L)
    job.start() // непосредственно она запускается только при вызове метода job.start()
    println("Other actions in main method")
}

suspend fun main003() = coroutineScope{

    val message: Deferred<String> = async{ getMessage()}        // async запускает отдельную корутину, которая выполняется параллельно с остальными корутинами
    // Поскольку функция getMessage() возвращает объект типа String, то возвращаемый корутиной объект представляет тип Deferred<String>
    // (объект Deferred типизиуется возвращаемым типом функции, то есть в данном случае типом String).
    val text: String = message.await()
    println("message: ${message.await()}")      // Для получения результата из объекта Deferred применяется функция await()
    println("Program has finished")
}

suspend fun getMessage() : String{
    delay(500L)  // имитация продолжительной работы
    return "Hello"
}

suspend fun main004() = coroutineScope{

    var id = 0
    val deferredList = mutableListOf<Deferred<Int>>()
    val resultList = mutableListOf<Int>()

    for (i in 1..3) {
        deferredList.add(async { sum(i, 3 * i, i * i) })
    }

    for (i in 0..deferredList.lastIndex) {
        resultList += deferredList[i].await()
    }

    resultList.forEach { println("sum: $it") }

}

suspend fun sum(id: Int, a: Int, b: Int) : Int{
    for (i in 1..3) {
        delay(50)
        println("sum $id: delay ${i * 50}")
    }
    return a + b
}

suspend fun main005() = coroutineScope{

    // корутина создана, но не запущена
    // Только в данном случае корутина запускается не только при вызове метода start объекта Deferred (который усналедован от интерфейса Job), но также и с помощью метода await() при обращении к результу корутины. Например:
    val sum = async(start = CoroutineStart.LAZY){ sum(1, 2)}

    delay(1000L)
    println("Actions after the coroutine creation")
    println("sum: ${sum.await()}")   // запуск и выполнение корутины
}
fun sum(a: Int, b: Int) : Int{
    println("Coroutine has started")
    return a + b
}

suspend fun main006() = coroutineScope{

    // корутина создана, но не запущена
    val sum = async(start = CoroutineStart.LAZY){ sum(1,1, 2)}

    delay(1000L)
    println("Actions after the coroutine creation")
    sum.start()                      // запуск корутины
    println("sum: ${sum.await()}")   // получаем результат
}

