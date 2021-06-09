package com.learnxinyminutes.kotlin.KotlinRu

import java.lang.Exception
import java.util.*

fun main(args: Array<String>) {

    // Умные приведения
    // с локальными val переменными - всегда;
    // с val свойствами - если поле имеет модификатор доступа private или internal, или проверка происходит в том же модуле, в котором объявлено это свойство. Умные приведения неприменимы к публичным свойствам или свойствам, которые имеют переопределённые getter'ы;
    // с локальными var переменными - если переменная не изменяется между проверкой и использованием и не захватывается лямбдой, которая её модифицирует;
    // с var свойствами - никогда (потому что переменная может быть изменена в любое время другим кодом).
    val objS = "I am string"
    printAny(objS)
    val objI = 5
    printAny(objI)
    actionAny(objI)
    actionAny(objS)

    try {
        action(5)
    }
    catch (e: Exception) {
        println("I catch e: Оператор приведения выбрасывает исключение, если приведение невозможно")
    }
    finally {
        println("optional finally block")
    }

    val list = arrayListOf<String>("One", "Two")
    handleStrings(list)

}

fun <T> printAny(input: T) {
    // Мы можем проверить принадлежит ли объект к какому-либо типу во время исполнения с помощью оператора is или его отрицания !is
    if (input is String) println(input)
    if (input !is String) println("Not a String, any action do:")

    // Есть возможность произвести проверку со "Звёздными" проекциями:
    if (input is List<*>) {
        input.forEach { println(it) } // Элементы типа `Any?`
    }


    // компилятор следит за is-проверками для неизменяемых значений и вставляет приведения автоматически
    when (input) {
        is Int -> println(input + 1)
        is String -> println(input.length + 1)
        is IntArray -> println(input.sum())
    }
}

fun <T> actionAny(input: T) {
    // Чтобы избежать исключения, вы можете использовать оператор безопасного приведения as?, который возвращает null в случае неудачи:
    val xOrNul: String? = input as? String
    if (xOrNul != null) println(xOrNul)
    else println("input no String object, return null")
}

// Оператор "небезопасного" приведения
// Обычно оператор приведения выбрасывает исключение, если приведение невозможно, поэтому мы называем его небезопасным.
fun <T> action(input: T) {
    // оператор приведения выбрасывает исключение, если приведение невозможно
    val xOrExc: String = input as String
    println(xOrExc)
    // null не может быть приведен к String, нам нужно указать nullable тип в правой части приведения:
    // val xOrNullOrExc: String? = input as String?
}

// когда у вас есть статически определенный тип аргумента, вы можете произвести is-проверку или приведение с не-обобщенной частью типа
fun handleStrings(list: List<String>) {
    if (list is ArrayList) {
        print("list приводится к `ArrayList<String>` за счет умного приведения")
    }
}