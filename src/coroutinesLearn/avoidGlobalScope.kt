package com.learnxinyminutes.kotlin.coroutinesLearn

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

// Структурированный параллелизм в Kotlin Coroutines требует, чтобы разработчики всегда запускали сопрограммы в контексте CoroutineScope или явно указывали область действия
fun main() {

    println("Thread один на все корутины и .sleep - тоже. Работа выполняется последовательно.")
    var time = measureTimeMillis {
        // Runs new coroutine and blocks current thread interruptibly until its completion. This function should not be used from coroutine.
        runBlocking {
            for (i in 1..2) {
                launch {
                    work(i)
                }
            }
        }
    }
    println("Done in $time ms")
    // Где параллелизм? Его нет - здесь запуск унаследовал диспетчер сопрограмм из области, представленной конструктором сопрограмм runBlocking,
    // который ограничивает выполнение одним потоком, поэтому обе задачи выполняются последовательно в основном потоке.

    // Чтобы получить одновременное выполнение в фоновых потоках и завершить нашу работу за секунду, мы можем запускать сопрограммы с помощью Dispatchers.Default:
    println("Используется Dispatchers.Default. Работа выполняется параллельно.")
    time = measureTimeMillis {
        runBlocking {
            for (i in 3..4) {
                // To get concurrent execution in background threads and complete our work in a second we can launch coroutines with Dispatchers.Default:
                // Запуск (Dispatchers.Default) создает дочерние сопрограммы в области runBlocking, поэтому runBlocking ожидает их завершения автоматически.
                launch(Dispatchers.Default) {
                    work(i)
                }
            }
        }
    }
    println("Done in $time ms")

    println("Используется GlobalScope.launch. Работа выполняется параллельно, но за результатом никто (основной код) не следит.")
    time = measureTimeMillis {
        runBlocking {
            for (i in 5..6) {
                // Однако GlobalScope.launch создает глобальные сопрограммы. Теперь ответственность за отслеживание их жизни лежит на разработчике.
                // Мы можем «исправить» подход с помощью GlobalScope, вручную отслеживая запущенные сопрограммы и ожидая их завершения с помощью join:
                GlobalScope.launch {
                    workGlobal(i)
                }
            }
        }
    }
    println("Done in $time ms")     // It completes without ever printing Work XXX done once!


    println("We can “fix” an approach with GlobalScope by manually keeping track of the launched coroutines and waiting for their completion using .join()")
    time = measureTimeMillis {
        runBlocking {
            val jobs = mutableListOf<Job>()
            for (i in 7..8) {
                jobs += GlobalScope.launch {
                    workGlobal(i)
                }
            }
            jobs.forEach { it.join() }
        }
    }
    println("Done in $time ms")

    println("Использование suspend fun. Не блокирует вызывающего")
    time = measureTimeMillis {
        runBlocking {
            for (i in 9..10) {
                launch {
                    // Теперь эта приостанавливающая версия работы не блокирует вызывающего, поэтому мы можем использовать
                    // ее внутри обычного вызова запуска и получить желаемый параллелизм:
                    workSuspend(i)
                }
            }
        }
    }
    // Он завершается за секунду и больше не зависит от деталей реализации работы, пока работа не блокирует вызывающего.
    println("Done in $time ms")

}

fun work(i: Int) {
    Thread.sleep(1000)
    println("Work $i done")
}

fun workGlobal(i: Int) {
    Thread.sleep(1000)
    println("Global Work $i done")
}

// Когда мы создаем suspend функцию, мы должны позаботиться о том, чтобы она выполнялась асинхронно в другом потоке или вернула результат сразу.
// Если же мы напишем в suspend функции код, блокирующий поток, то слово suspend нам тут никак не поможет.
// Такая suspend функция просто заблокирует поток, в котором выполняется корутина.
// Здесь рабочая функция блокирует поток на секунду, но ее можно преобразовать в функцию приостановки с помощью withContext,
// инкапсулируя соответствующий диспетчер для ее выполнения:
suspend fun workSuspend(i: Int) = withContext(Dispatchers.Default) {
    Thread.sleep(1000)
    println("Work $i done")
}