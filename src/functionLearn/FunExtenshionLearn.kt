package com.learnxinyminutes.kotlin.functionLearn

import com.learnxinyminutes.kotlin.callBackLearn.Button

class B {
    val str = "From B with love"
}

class A {
    private val str = "From A with love"
    fun B.doSomething() {
        println(str) // prints "From B with love"
        println(this@A.str) // prints "From A with love"
    }
}

fun main(args: Array<String>) {
    val a = A()
    val b = B()
    with (a) {
        b.doSomething()
    }

    fun <T, R> Collection<T>.fold(
        initial: R,
        combine: (acc: R, nextElement: T) -> R
    ): R {
        var accumulator: R = initial
        for (element: T in this) {
            accumulator = combine(accumulator, element)
        }
        return accumulator
    }

    val items = listOf(1, 2, 3, 4, 5)
    // Типы параметров в лямбде необязательны, если они могут быть выведены:
    val joinedToString = items.fold("Elements:", { acc, i -> acc + " " + i })
    // Ссылки на функции также могут использоваться для вызовов функций высшего порядка:
    // a * b	a.times(b)
    val product = items.fold(1, Int::times)

    println("joinedToString = $joinedToString")
    println("product = $product")

    // У функциональных типов может быть дополнительный тип - получатель (receiver), который указывается в объявлении перед точкой: тип A.(B) -> C описывает функции, которые могут быть вызваны для объекта-получателя A с параметром B и возвращаемым значением C. Литералы функций с объектом-приёмником часто используются вместе с этими типами.
    // Останавливаемые функции (suspending functions) принадлежат к особому виду функциональных типов, у которых в объявлении присутствует модификатор suspend, например, suspend () -> Unit или suspend A.(B) -> C.

    // Небуквальные (non-literal) значения функциональных типов с и без получателя являются взаимозаменяемыми,
    // таким образом получатель может заменить первый параметр, и наоборот.
    // Например, значение типа (A, B) -> C может быть передано или назначено там, где ожидается A.(B) -> C, и наоборот.

        val repeatFun: String.(Int) -> String = { times -> this.repeat(times) }
        val repeatFunComm: String.(Int) -> String = {
                times   //  .(Int)
                ->      // Сверху (аргумент), внизу используем в качестве параметра
                this.repeat(times)  // Функция выполняет с объектом String повторы в колличестве равным аргументу times: Int
        }
        val twoParameters: (String, Int) -> String = repeatFun // OK

        fun runTransformation(f: (String, Int) -> String): String {
            return f("hello", 3)
        }
        val result = runTransformation(repeatFun) // OK

        println("result = $result")

    // Если значение имеет тип получателя, то объект-приёмник должен быть передан в качестве первого аргумента. Другой способ вызвать значение функционального типа с получателем - это добавить его к объекту-приёмнику, как если бы это была функция-расширение: 1.foo(2),

        val stringPlus: (String, String) -> String = String::plus
        val intPlus: Int.(Int) -> Int = Int::plus

        println(stringPlus.invoke("<-", "->"))
        println(stringPlus("Hello, ", "world!"))

        println(intPlus.invoke(1, 1))
        println(intPlus(1, 2))
        println(2.intPlus(3)) // вызывается как функция-расширение

    // Функции-расширения

    //Для того, чтобы объявить функцию-расширение, нам нужно указать в качестве префикса расширяемый тип, то есть тип, который мы расширяем.
    fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
        val tmp = this[index1] // 'this' относится к списку
        this[index1] = this[index2]
        this[index2] = tmp
    }

}

// Для того, чтобы объявить функцию-расширение, нам нужно указать в качестве префикса расширяемый тип, то есть тип, который мы расширяем.
// Следующий пример добавляет функцию swap к MutableList<Int>:

fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' даёт ссылку на список
    this[index1] = this[index2]
    this[index2] = tmp
}

// Вы также можете присвоить функциональному типу альтернативное имя, используя псевдонимы типов:
typealias ClickHandler = (Button, ClickEvent) -> Unit

class ClickEvent {
}

// Внутри класса вы можете объявить расширение для другого класса.
class D {
    fun bar() { println("bar") }
}

class C {
    fun baz() { println("baz") }

    fun D.foo() {
        bar()   // вызывает D.bar
        baz()   // вызывает C.baz
        // В случае конфликта имён между членами классов диспетчера приёмников и приёмников расширения, приоритет имеет приёмник расширения.
        // Чтобы обратиться к члену класса диспетчера приёмников, можно использовать синтаксис this с квалификатором.
        toString()         // вызывает D.toString()
        this@C.toString()  // вызывает C.toString()
    }

    fun caller(d: D) {
        d.foo()   // вызов функции-расширения
    }
}
