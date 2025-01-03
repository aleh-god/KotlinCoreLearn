package com.learnxinyminutes.kotlin.coroutinesLearn

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

suspend fun main(){
    getUsers().collect { user -> println(user) }
}

fun getUsers(): Flow<String> = flow {
    val database = listOf("Tom", "Bob", "Sam")  // условная база данных
    var i = 1
    for (item in database){
        delay(400L) // имитация продолжительной работы
        println("Emit $i item")
        emit(item) // емитируем значение
        i++
    }
}

/*
fun simple(): Flow<Int> = flow { // flow builder
    for (i in 1..3) {
        delay(100) // pretend we are doing something useful here
        emit(i) // emit next value
    }
}

fun main() = runBlocking<Unit> {
    // Launch a concurrent coroutine to check if the main thread is blocked
    launch {
        for (k in 1..3) {
            println("I'm not blocked $k")
            delay(100)
        }
    }
    // Collect the flow
    simple().collect { value -> println(value) }
    simple().collectLatest { value -> println(value) }
}
 */