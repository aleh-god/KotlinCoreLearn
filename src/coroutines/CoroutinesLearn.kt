package coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    runCoroutinesOnSingleThread()
}

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
val singleThread = newSingleThreadContext("Custom Thread")

suspend fun runCoroutinesOnSingleThread() = withContext(singleThread) {

    launch {
        val job = launch{
            for(i in 1..5){
                println(i)
                delay(400L)
            }
        }
        println("Start")
        for(i in 10..15) {
            println(i)
            delay(400L)
        }
        job.join() // ожидаем завершения корутины
        println("End")
    }

    println("Поток функции main: ${Thread.currentThread().name}")
}
