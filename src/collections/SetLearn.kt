package collections

/*
Set<T> stores unique elements; their order is generally undefined. null elements are unique as well:
a Set can contain only one null. Two sets are equal if they have the same size, and for each element of a set there
is an equal element in the other set.
 */
fun main(args: Array<String>) {

    var numbers = setOf(1, 2, 3, 4)
    println("Number of elements: ${numbers.size}")
    if (numbers.contains(1)) println("1 is in the set")


    var numbersBackwards = setOf(4, 3, 2, 1)
    println("The sets are equal: ${numbers == numbersBackwards}")

/*
MutableSet is a Set with write operations from MutableCollection.
The default implementation of Set – LinkedHashSet – preserves the order of elements insertion.
Hence, the functions that rely on the order, such as first() or last(), return predictable results on such sets.
*/
    numbers = setOf(1, 2, 3, 4)  // LinkedHashSet is the default implementation
    numbersBackwards = setOf(4, 3, 2, 1)

    println(numbers.first() == numbersBackwards.first())
    println(numbers.first() == numbersBackwards.last())

/*
An alternative implementation – HashSet – says nothing about the elements order, so calling such functions on it
returns unpredictable results. However, HashSet requires less memory to store the same number of elements.
 */
}