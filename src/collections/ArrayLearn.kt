package collections

// Array в Kotlin самый настоящий объект, ссылочный тип, как и остальные
fun main(args: Array<String>) {
    val arrayInit = Array<Int>(10) { 0 } // Создание массива, инициализация через размер и шаблон заполнения
    val arrayNew: Array<Int> = arrayOf(1,2,3,4)
    val arrayNull: Array<String?> = arrayOfNulls(10)

    // Специальные массивы из примитивов для Джавы
    val arrayInt = intArrayOf(1,2,3)

    println("array size = ${arrayNew.size}")
    println("array is Empty = ${arrayNew.isEmpty()}")
    var result: Int = arrayNew[2]
    result = arrayNew.get(2)
    arrayNew[1] = result

    val array = Array(5) { i -> i * i }
    array.forEach { print(" $it") }

}
