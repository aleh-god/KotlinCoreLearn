package com.learnxinyminutes.kotlin.objectClassGenericLearn

import kotlin.system.exitProcess

fun main() {
    fun1<String>()
    fun2("str")
    funStr2()
    funStr(fun3())
}

inline fun <reified T: Any> fun1() {
    println("fun1 ${T::class.simpleName}")
}

inline fun <reified T: Any> fun2(t: T) {
    println("fun2 $t ${T::class.simpleName}")
}

inline fun <reified T: Any> fun3(): T {
    if(T::class == String::class) println("fun3 ${T::class.simpleName}")
    exitProcess(0)
}

inline fun <reified T: Any> T.fun4() {
    println("fun4 ${T::class.simpleName}")
}

fun funStr(str: String) {
    println("str = $str")
}

fun funStr2() {
    String.fun4()
}