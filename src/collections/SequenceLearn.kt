package com.learnxinyminutes.kotlin.collections

fun main(args: Array<String>) {
// Создает последовательность, которая возвращает все элементы из этого итератора. Последовательность повторяется только один раз.
    println("Creates a sequence that returns all elements from this iterator.")
    val array = arrayOf(1, 2, 3)

// create a sequence with a function, returning an iterator
    val sequence = Sequence { array.iterator() }
    println(sequence.joinToString()) // 1, 2, 3
    println(sequence.drop(1).joinToString()) // 2, 3

// create a sequence from an existing iterator
// can be iterated only once
    val sequence2 = array.iterator().asSequence()
    println(sequence2.joinToString()) // 1, 2, 3
// sequence2.drop(1).joinToString() // <- iterating sequence second time will fail
}