package com.learnxinyminutes.kotlin.collections

fun main(args: Array<String>) {
    // Sort vs Sorted;
    // Reverse vs Reversed;
    // Shuffle vs Shuffled

    // The default function that is used across both read-only and mutable collection list is the past-participle function i.e. sorted(), reversed(), and shuffled().
    // They will produce a new list of the intended result.
    val list = listOf(2, 3, 1, 4, 5, 6)
    val sorted = list.sorted()
    val reversed = list.reversed()
    val shuffled = list.shuffled()
    println(sorted)    // [1, 2, 3, 4, 5, 6]
    println(reversed)  // [6, 5, 4, 1, 3, 2]
    println(shuffled)  // [3, 4, 5, 1, 6, 2]
    println(list)      // [2, 3, 1, 4, 5, 6]

    //You’ll notice that the original word functions i.e. sort(), reverse(), and shuffle() is not available to list.
    // In order to use the original word function, you’ll have to convert it into a mutableList. The reason is simple.
    // The original word function will mutate the original list instead of creating a new list from it.
    // It doesn’t output a new list.
// SORT
    var listX = mutableListOf(2, 3, 1, 4, 5, 6)
    listX.sort()
    println(listX)      // [1, 2, 3, 4, 5, 6]
// REVERSE
    listX = mutableListOf(2, 3, 1, 4, 5, 6)
    listX.reverse()
    println(listX)      // [6, 5, 4, 1, 3, 2]
// SHUFFLE
    listX = mutableListOf(2, 3, 1, 4, 5, 6)
    listX.shuffle()
    println(listX)      // [3, 4, 5, 1, 6, 2]
}