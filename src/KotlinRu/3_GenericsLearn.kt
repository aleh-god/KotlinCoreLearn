package com.learnxinyminutes.kotlin.KotlinRu

fun main(args: Array<String>) {

    // Для того, чтобы создать объект такого класса, необходимо предоставить тип в качестве аргумента:
    val box: Box<Int> = Box<Int>(1)
    val boxDouble: Box<Double> = Box<Double>(5.4)


    // Обобщённые ограничения - Набор всех возможных типов, которые могут быть переданы в качестве параметра
    // Самый распространённый тип ограничений - верхняя граница, которая соответствует ключевому слову extends из Java:
    fun <T : Comparable<T>> sort(list: List<T>) {
        // Тип, указанный после двоеточия, является верхней границей
    }

}

class Box<T>(t: T) {
    var value = t
}

// Допустим, у нас есть обобщённый интерфейс Source<T>, у которого нет методов, которые принимают T в качестве аргумента.
// Только методы, возвращающие T:
interface Source<T> {
    fun nextT(): T
}

// вариантность на уровне объявления: мы можем объявить типовой параметр T класса Source таким образом,
// чтобы удостовериться, что он только возвращается (производится) членами Source<T>, и никогда не потребляется.
// Чтобы сделать это, нам необходимо использовать модификатор out
abstract class SourceX<out T> {
    abstract fun nextT(): T
}

// Общее правило таково: когда параметр T класса С объявлен как out, он может использоваться только в out-местах в членах C.
// Но зато C<Base> может быть родителем C<Derived>, и это будет безопасно.
fun demo(strs: SourceX<String>) {
    val objects: SourceX<Any> = strs // Всё в порядке, т.к. T — out-параметр
    // ...
}

// В дополнении к out, Kotlin предоставляет дополнительную модификатор in.
// Он делает параметризованный тип контравариантным: он может только потребляться, но не может производиться.
// Comparable является хорошим примером такого класса:
abstract class Comparable<in T> {
    abstract fun compareTo(other: T): Int
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 имеет тип Double, расширяющий Number
    // Таким образом, мы можем присвоить значение x переменной типа Comparable<Double>
    val y: Comparable<Double> = x // OK!
}

/*
По умолчанию (если не указана явно) верхняя граница — Any?. В угловых скобках может быть указана только одна верхняя граница. Для указания нескольких верхних границ нужно использовать отдельное условие where:
fun <T> cloneWhenGreater(list: List<T>, threshold: T): List<T>
        where T : Comparable,
              T : Cloneable {
    return list.filter { it > threshold }.map { it.clone() }
}
 */