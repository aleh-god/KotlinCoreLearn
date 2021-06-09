package coreLearn

class ArrayLearn: CoreLearn {
    override fun showResult() {
        // Массив представляет набор данных одного типа. В языке Kotlin массивы представлены типом Array.
        //При определении массива после типа Array в угловых скобках необходимо указать, объекты какого типа могут храниться в массиве.

        var numbersInit: Array<Int>

        // С помощью встроенной функции arrayOf() можно передать набор значений, которые будут составлять массив:
        var numbers: Array<Int> = arrayOf(1, 2, 3, 4, 5)

        // Также инициализировать массив можно через шаблон {}
        // Первый параметр указывает, сколько элементов будет в массиве.
        // Второй параметр представляет выражение, которое генерирует элементы массива.
        var numbersTemplate = Array(3, {5}) // [5, 5, 5]

        // Для упрощения создания массива в Kotlin определены дополнительные типы BooleanArray, ByteArray, ShortArray, IntArray, LongArray, CharArray, FloatArray и DoubleArray, которые позволяют создавать массивы для определенных типов.
        val numbersInt: IntArray = intArrayOf(1, 2, 3, 4, 5)
        val doublesDouble: DoubleArray = doubleArrayOf(2.4, 4.5, 1.2)

        var n = numbers[1]  // получаем второй элемент  n=2
        numbers[2] = 7      // переустанавливаем третий элемент

        // Можно проверить на вхождение
        println(4 in numbers)       // true
        println(2 !in numbers)      // false

        // Двухмерные массивы
        // Двухмерный массив - то есть такой массив, каждый элемент которого в свою очередь сам является массивом. Двухмерный массив еще можно представить в виде таблицы, где каждая строка - это отдельный массив, а ячейки строки - это элементы вложенного массива.

        val table = Array(3, { Array(3, {0}) })
        // Используя индексы, можно обращаться к подмассивам в подобном массиве, в том числе переустанавливать их значения:
        table[0] = arrayOf(1, 2, 3)
        table[1] = arrayOf(4, 5, 6)
        table[2] = arrayOf(7, 8, 9)

        // По первому индексу идет получение строки, а по второму индексу - столбца в рамках этой строки
        table[0][1] = 6  // второй элемент первой строки
        n = table[0][1]     // n = 6

        // Перебор массивов

        // Массивы можно помещать в цикл for
        println("цикл for")

        for(row in table){
            // Каждый из элементов двухмерного массива сам представляет массив
            for(cell in row){
                print("$cell \t")
            }
            println()
        }

        val phones: Array<String> = arrayOf("Galaxy S8", "iPhone X", "Motorola C350")

        for(phone in phones){
            println(phone)
        }

        println("функция .forEach")
        phones.forEach { cell -> print("$cell \t") }
    }
}