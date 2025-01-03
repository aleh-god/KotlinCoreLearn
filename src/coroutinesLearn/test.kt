package com.learnxinyminutes.kotlin.coroutinesLearn

import kotlinx.coroutines.*

suspend fun main() = coroutineScope{
    println("Program has started")

    val message: Deferred<String> = async{ getMessageT()}        // async запускает отдельную корутину, которая выполняется параллельно с остальными корутинами

    for (i in 1..3) {
        delay(100)
        println("main: delay ${i * 50}")
    }

    println("message: ${message.await()}")      // Для получения результата из объекта Deferred применяется функция await()
    println("Program has finished")
}

suspend fun getMessageT() : String{
    for (i in 1..5) {
        delay(100)
        println("getMessage: delay ${i * 50}")
    }
    return "Hello"
}
