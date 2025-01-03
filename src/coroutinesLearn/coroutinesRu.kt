package com.learnxinyminutes.kotlin.coroutinesLearn

import kotlinx.coroutines.*

fun main() {

    println("coroutines")
    main01()
    main02()
    println("runBlocking")
    main03()
    main04()
    println("job.join")
    main05()
    println("Структурированный параллелизм")
    main06()
    println("Scope builder")
    main07()
    println("Извлечение функции")
    main08()
    println("Легковесные сопрограммы")
    main09()
    main10()
}

fun main01() {
    // В примере выше мы запускаем новую сопрограмму в GlobalScope. Это означает, что время жизни новой сопрограммы ограничено только временем жизни всего приложения.
    GlobalScope.launch { // запуск новой сопрограммы в фоне, с помощью билдера сопрограмм
        delay(1000L) // delay является функцией приостановки, которая не блокирует поток, а приостанавливает сопрограмму. Использовать её можно только из сопрограммы.
        println("World!") // вывод результата после задержки
    }
    println("Hello,") // пока сопрограмма проводит вычисления, основной поток продолжает свою работу
    Thread.sleep(2000L) // блокировка основного потока на 2 секунды, чтобы сопрограмма успела произвести вычисления
}

// runBlocking - Runs new coroutine and blocks current thread interruptibly until its completion. This function should not be used from coroutine.
fun main02() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello") // main coroutine continues while a previous one is delayed
}

fun main03() {
    GlobalScope.launch { // запуск новой сопрограммы в фоне
        delay(1000L)        // здесь используется только неблокирующая delay
        println("World!")
    }
    println("Hello,") // основной поток продолжает свою работу

    // Основной поток, вызывающий runBlocking, блокируется до завершения сопрограммы внутри runBlocking.
    runBlocking {     // но это выражение блокирует основной поток
        delay(2000L)  // здесь используется только неблокирующая delay
    }
}

// runBlocking работает как адаптер, который используется для запуска основной сопрограммы верхнего уровня.
// Мы явно указываем возвращаемый тип Unit, потому что правильно сформированная функция main в Kotlin должна возвращать Unit.
fun main04() = runBlocking<Unit> { // запуск основной сопрограммы
    GlobalScope.launch { // запуск новой сопрограммы в фоне
        delay(1000L)
        println("World!")
    }
    println("Hello,") // основная сопрограмма продолжает свою работу
    delay(2000L)      // задержка на 2 секунды
}

// Давайте явно подождем (неблокирующим способом), пока Job, запущенный в фоне, не будет выполнен.
fun main05() = runBlocking {
    val job = GlobalScope.launch { // запуск новой сопрограммы с сохранением ссылки на нее в Job
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    job.join() // ждем завершения вложенной сопрограммы
}

// В нашем примере есть функция main, которая превращается в сопрограмму с помощью билдера runBlocking.
fun main06() = runBlocking { // Каждый билдер, включая runBlocking, добавляет экземпляр CoroutineScope к области видимости своего блока с кодом.
    // this: CoroutineScope
    launch { // запуск сопрограммы в области видимости runBlocking
        delay(1000L)
        println("World!")
    }
    // внешняя сопрограмма (runBlocking в нашем примере) не завершится, пока не будут выполнены все сопрограммы, запущенные в ее области видимости.
    println("Hello,")
}

/*
runBlocking и coroutineScope могут выглядеть одинаково, потому что они обе ждут завершения всех операций внутри своего блока и завершения всех запущенных дочерних сопрограмм.
Основное отличие заключается в том, что метод runBlocking блокирует текущий поток, в то время как coroutineScope лишь приостанавливает работу,
высвобождая основной поток для других целей. Из-за этой разницы runBlocking является обычной функцией, а coroutineScope - функцией приостановки.
 */
fun main07() = runBlocking { // this: CoroutineScope
    launch {
        delay(200L)
        println("Task from runBlocking")
        // Обратите внимание, что сразу после сообщения "Task from coroutine scope" (во время ожидания выполнения вложенного launch)
        // выполняется и выдаётся "Task from runBlocking", хотя выполнение coroutineScope еще не завершилось.
    }

    coroutineScope { // Создание coroutine scope
        launch {
            delay(500L)
            println("Task from nested launch")
        }

        delay(100L)
        println("Task from coroutine scope") // Эта строка будет выведена перед вложенным launch
    }

    println("Coroutine scope is over") // Эта строка не будет выведена пока не выполнится вложенный launch
}

fun main08() = runBlocking {
    launch {
        // Извлечём блок кода внутри launch { ... } в отдельную функцию.
        doWorld()
    }
    println("Hello,")
}

// вы получите новую функцию приостановки с модификатором suspend.
suspend fun doWorld() {
    delay(1000L) // могут использовать другие функции приостановки (например, функцию delay) для приостановки выполнения сопрограммы.
    println("World!")
}

fun main09() = runBlocking {
    repeat(100) { // запуск большого количества сопрограмм. Executes the given function action specified number of times.
        launch {
            delay(5000L)
            print(".")
        }
    }
}

fun main10() = runBlocking {
    // Активные сопрограммы, запущенные в GlobalScope, не поддерживают "жизнь" процесса. В этом они похожи на демон-потоки.
    GlobalScope.launch {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")      // Два раза в секунду выводит сообщение "I'm sleeping"
            delay(500L)
        }
    }

    delay(1300L) // выход из программы, хотя сопрограмма не завершена
}