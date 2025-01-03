package com.learnxinyminutes.kotlin.coroutinesLearn

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce

fun main() = runBlocking {
    println("Каналы позволяют передавать потоки данных.")
    mainM()
    println("Чтобы указать, что в канале больше нет данных, его можно закрыть с помощью метода close().")
    mainN()
    println("функция produce() представляет построитель корутины, который создает корутину, в которой передаются данные в канал.")
    mainV()

}

suspend fun mainM() = coroutineScope{

    // Определим простейший канал, через который будем передавать числа типа Int:
    val channel = Channel<Int>()

    launch {
        for (n in 1..5) {
            // отправляем данные через канал
            // Особенностью этого метода является то, что мы можем его запустить только в корутине.
                println("send: $n")
            delay(2000)
            channel.send(n)
        }
    }

    // получаем данные из канала
    repeat(5) {
        val number = channel.receive()
        println("reeive: $number")
    }
    println("End")
}

suspend fun mainN() = coroutineScope{

    val channel = Channel<String>()
    launch {
        val users = listOf("Tom", "Bob", "Sam")
        for (user in users) {
            println("Sending $user")
            channel.send(user)
        }
        channel.close()  // Закрытие канала
    }

    // получив сигнал о закрытии канала, данный цикл получит все ранее посланные объекты до закрытия и завершит выполнение:
    for(user in channel) {  // Получаем данные из канала
        println(user)
    }
    println("End")
}

suspend fun mainV() = coroutineScope{

    val users = getUsers()
    users.consumeEach { user -> println(user) }
    println("End")
}

// функция produce() представляет построитель корутины, который создает корутину, в которой передаются данные в канал.
// Функция должна возвращать объект ReceiveChannel, типизированный типом передаваемых данных (в данном случае передаем значения типа String).
fun CoroutineScope.getUsers(): ReceiveChannel<String> = produce{
    val users = listOf("Tom", "Bob", "Sam")
    for (user in users) {
        send(user)
    }
}
