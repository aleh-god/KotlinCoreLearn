package com.learnxinyminutes.kotlin.collections

fun main(args: Array<String>) {
    /*
    I’m grouping them into 5 categories as below.
    Creation — Functions that create a collection, e.g. listOf
    Convert — Functions that cast to the other type, e.g. asMap
    Change — Functions that transform the contents, e.g. map
    Choose — Functions that access one of the items e.g. get
    Conclude — Functions that generate something from the items, e.g. sum

    Relationship between the categories
    From the categories, it is obvious Creation is the first state as it creates the collections,
    Change and Convert is the intermediary state where they can transition between themselves,
    while the final state is either Conclude or Choose.
     */

    var relationship = listOf(1, 2, 3)   // Creation
                       .map { it * 2 }   // Change
                       .sum()            // Conclude



    /*
    Creation Category
    In this category, I further break them down into subcategories, to make finding them easier.

    Creation Compose — Instantiate new collection
    Creation Copy — replicate of collection
    Creation Catch — like try-catch to create otherwise


    // Creation Compose — instantiate new collection
    // These are the functions that help you instantiate the collections directly. They are all linked to the formal Kotlin documentation.

    var result: Iterable<Any>
    var resultMap: Iterable<Entry<K, V>>
// Empty Collection
    result = emptyList()
    result = emptySet()
    result = emptyMap()
// Read-only Collection
    listOf, mapOf, setOf
// Mutable Collection
    mutableListOf, mutableMapOf, mutableSetOf, arrayListOf
// Build Collection from mix sources
    buildList, buildMap, buildSet
// Linked Collection
    linkedMapOf, linkedSetOf (more in stackOverflow)
// Sorted Collection
    sortedMapOf, sortedSetOf (more in stackOverflow)
// Hash Collection
    hashMapOf, hashSetOf (more in stackOverflow)
// Programmatically create Collection
    List, MutableList, Iterable

     */
}