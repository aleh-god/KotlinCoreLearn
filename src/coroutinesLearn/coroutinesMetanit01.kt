package com.learnxinyminutes.kotlin.coroutinesLearn

import kotlinx.coroutines.*

fun main() = runBlocking{
    println("пример, который не использует корутины")
    mainA()
    println("вынесем продолжительную работу - в корутину")
    mainB()
    println("Вынесение кода корутин в отдельную функцию")
    mainC()
    println("Корутина может выполняться только в определенной области корутины (coroutine scope)")
    mainE()
    println("Запуск нескольких корутин")
    mainF()
    println("Вложенные корутины")
    mainD()
}

suspend fun mainA(){

    // Сначала рассмотрим пример, который не использует корутины:
    for(i in 0..5){
        delay(400L)
        println(i)
    }

    println("Hello Coroutines")
}

suspend fun mainB() = coroutineScope{
    launch{
        for(i in 0..5){
            delay(400L)
            println(i)
        }
    }
    println("Hello Coroutines")
}

suspend fun mainC()= coroutineScope{
    launch{ doWork() }      // Сама корутина создается также с помощью функции launch(), которая вызывает функцию doWork().

    println("Hello Coroutines")
}

suspend fun doWork(){
    for(i in 0..5){
        println(i)
        delay(400L)
    }
}

suspend fun mainE(){

    doWorkE()

    println("Hello Coroutines")
}

suspend fun doWorkE()= coroutineScope{
    launch{
        for(i in 0..5){
            println(i)
            delay(400L)
        }
    }
}

// Функция coroutineScope(), которая создает область корутин, будет ожидать завершения всех определенных в этой области корутин.
suspend fun mainF()= coroutineScope{

    launch{
        for(i in 0..5){
            delay(400L)
            println(i)
        }
    }
    launch{
        for(i in 6..10){
            delay(400L)
            println(i)
        }
    }

    println("Функция coroutineScope(), которая создает область корутин, будет ожидать завершения всех определенных в этой области корутин.")
}

// И подобным образом внешние корутины определяют область для вложенных корутин и управляют их жизненным циклом.
suspend fun mainD() = coroutineScope{

    println("Start of Main")
    launch{
        println("Start Outer coroutine")
        launch{
            println("Work Inner coroutine")
            delay(400L)
        }
        println("End Outer coroutine")
    }

    println("End of Main")
}