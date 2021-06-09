package com.learnxinyminutes.kotlin

import java.lang.IllegalArgumentException

fun main(args: Array<String>) {

    val l = listOf(1, -2, 3, -4, -5, 10, 15, 50, 100)
    var positives = l.filter { x -> x > 0 }
    println(positives)
    positives = l.filter { it < 0 }
    println(positives)

    val map = mutableMapOf("a" to 1, "b" to 20, "c" to 300)
    val res = map["b"]
    map["c"] = -300

    for ((k,v) in map) {
        println("key $k value $v")
    }

    val lazyProperties: String by lazy {
        // lazy() это функция, которая принимает лямбду и возвращает экземпляр класса Lazy<T>,
        // который служит делегатом для реализации ленивого свойства: первый вызов get() запускает лямбда-выражение,
        // переданное lazy() в качестве аргумента, и запоминает полученное значение, а последующие вызовы просто возвращают вычисленное значение.
        val str1 = "compute"
        val str2 = " the String"
        str1 + str2
    }
    println(lazyProperties)

    val b: DerivedAny = DerivedAny(11)
    val d: Derived = Derived(10)
    var x: Base
    x = b

    when(x) {
        is DerivedAny -> print("x is DeliveredAny")
        is Derived -> print("x is Delivered")
        is Base -> print("Ups")
    }

    fun theAnswer() = 42 // Сокращенная функция

    // when как сокращенная функция
    fun transform(color: String): Int = when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
    }

    val t = Turtle()
    // Вызываем методы через with()
    with(t) {
        pepDown()
        pepUp()
        for (i in 1..4) {
            forward(100.0)
            turn(50.0)
        }
    }

    // Рокировка значений
    var a = 1
    var c = 2
    a = c.also { c = a }

}

object Singleton {
    val check = "I am Singleton!"
}

data class Customer(val name: String, val email: String)

open class Base(p: Int)

class Derived(p: Int) : Base(p)

class DerivedAny(p: Int) : Base(p)

class Turtle {
    fun pepDown() {
        print("pepDown ")
    }
    fun pepUp() {
        print(" pepUp ")
    }
    fun turn(degrees: Double) {
        print(" $degrees ")
    }
    fun forward(pixels: Double) {
        print(" $pixels ")
    }
}