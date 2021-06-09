package com.learnxinyminutes.kotlin.functionLearn

fun main(args: Array<String>){
    val name: String? = "Jesus"

    // .run это экс-функция для поля name (ресивер), тело функции (Анонимная, выход - Юнит, единственный параметр это лямбда, котору можно вынести за скобки) указано в {}
    name?.run { print("This is $this") } // this - область видимости класса-ресивера к которому вызвана функция
    // T - теперь аргумент
    name?.let { print("This is $it") } // it - область видимости класса-ресивера к которому вызвана функция
    // T - возвращаемый результат
    val safeName = name
        ?.apply { print("This is $this") }
        ?: "" // это продолжение, ведь apply возвращает T
    print(safeName.length)
    // Теперь рессивер в аргумете
        ?.also { print("This is $it") }
        ?: "" // это продолжение, ведь also возвращает T
    print(safeName.length)


}