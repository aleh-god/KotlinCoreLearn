package com.learnxinyminutes.kotlin.collections

fun main(args: Array<String>) {

    // interface Iterator<out T>
    // An iterator over a collection or another entity that can be represented as a sequence of elements.
    // сущность, которая может быть представлена как последовательность элементов.x
    // Allows to sequentially access the elements.

    // Functions
    // .hasNext - Returns true if the iteration has more elements.
    // .next - Returns the next element in the iteration.

    // Extension Functions
    // .asSequence - Creates a sequence that returns all elements from this iterator. The sequence is constrained to be iterated only once.
    val array = arrayOf(1, 2, 3)

    // create a sequence from an existing iterator
    val sequence2 = array.iterator().asSequence()
    println(sequence2.joinToString()) // 1, 2, 3     // can be iterated only once


    // .forEach - Performs the given operation on each element of this Iterator.
    // inline fun <T> Iterator<T>.forEach( operation: (T) -> Unit )
    val iteratorEach = (1..3).iterator()
    // skip an element
    if (iteratorEach.hasNext()) {
        iteratorEach.next()
    }
    // do something with the rest of elements
    iteratorEach.forEach {
        println("The element is $it")
    }

    // .iterator - Returns the given iterator itself. This allows to use an instance of iterator in a for loop.
    println("Returns the given iterator itself:")
    val mutableList = mutableListOf(1, 2, 3)
    // Returns the given iterator itself. This allows to use an instance of iterator in a for loop.
    val mutableIterator = mutableList.iterator()
    // iterator() extension is called here
    for (e in mutableIterator) {
        if (e % 2 == 0) {
            // we can remove items from the iterator without getting ConcurrentModificationException
            // because it's the same iterator that is iterated with for loop
            mutableIterator.remove()
        }
        println("The element is $e")
    }

    // .withIndex - Returns an Iterator that wraps each element produced by the original iterator into an data class IndexedValue<out T> containing the index of that element and the element itself.
    // Возвращает Iterator, который оборачивает каждый элемент, созданный исходным итератором, в data class IndexedValue(index: Int, value: T), содержащее индекс этого элемента и самого элемента.
    println("Returns the iterator.withIndex():")
    val iterator = ('a'..'c').iterator()
    for ((index, value) in iterator.withIndex()) {
        println("The element at $index is $value")
    }
}