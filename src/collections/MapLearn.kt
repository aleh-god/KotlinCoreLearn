package collections

/*
Map<K, V> is not an inheritor of the Collection interface; however, it's a Kotlin collection type as well.
A Map stores key-value pairs (or entries ); keys are unique, but different keys can be paired with equal values.
The Map interface provides specific functions, such as access to value by key, searching keys and values, and so on.
 */

fun main(args: Array<String>) {

var numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)

println("All keys: ${numbersMap.keys}")
println("All values: ${numbersMap.values}")
if ("key2" in numbersMap) println("Value by key \"key2\": ${numbersMap["key2"]}")
if (1 in numbersMap.values) println("The value 1 is in the map")
if (numbersMap.containsValue(1)) println("The value 1 is in the map") // same as previous

/*
Two maps containing the equal pairs are equal regardless of the pair order.
*/

numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)
val anotherMap = mapOf("key2" to 2, "key1" to 1, "key4" to 1, "key3" to 3)

println("The maps are equal: ${numbersMap == anotherMap}")

/*
MutableMap is a Map with map write operations, for example, you can add a new key-value pair or update the value associated with the given key.
*/

numbersMap = mutableMapOf("one" to 1, "two" to 2)
numbersMap.put("three", 3)
numbersMap["one"] = 11

println(numbersMap)

/*

The default implementation of Map – LinkedHashMap – preserves the order of elements insertion when iterating the map.
In turn, an alternative implementation – HashMap – says nothing about the elements order.
 */
}