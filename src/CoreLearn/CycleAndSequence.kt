package CoreLearn

class CycleAndSequence: CoreLearn {
    override fun showResult() {
        // Циклы представляют вид управляющих конструкций, которые позволяют в зависимости от определенных условий выполнять некоторое действие множество раз.
        // for(переменная in поледовательность){ выполняемые инструкции }
        // Цикл for пробегается по всем элементам коллекции. В этом плане цикл for в Kotlin эквивалентен циклу for-each в ряде других языков программирования. Его формальная форма выглядит следующим образом:
        for (n in 1..9) {
            print("${n * n} \t") // ${} - шаблон вывода на консоль
        }

        // Цикл while повторяет определенные действия пока истинно некоторое условие:
        var i = 10
        while (i > 0) {
            println(i * i)
            i--;
        }
        // другая форма цикла while - do..while блок do выполнится хотя бы один раз
        i = -1
        do {
            println(i * i)
            i--;
        } while (i > 0)

        // Операторы continue и break
        for (n in 1..8) {
            if (n == 5) continue; // Цикл перейдет к обработке следующего элемента в массиве
            println(n * n)
        }

        for (n in 1..5) {
            if (n == 5) break; // будет выполнен выход из цикла. Цикл полностью завершится.
            println(n * n)
        }

        // Последовательность представляет набор значений или диапазон. Для создания последовательности применяется оператор ..:
        var rangeInt = 1..5    // последовательность [1, 2, 3, 4, 5]
        var range = 5 downTo 1    // 5 4 3 2 1
        range = 1..10 step 2           // 1 3 5 7 9
        range = 10 downTo 1 step 3     // 10 7 4 1

        // Еще одна функция until позволяет не включать верхнюю границу в саму последовательность:
        range = 1 until 9          // 1 2 3 4 5 6 7 8
        range = 1 until 9 step 2   // 1 3 5 7

        var rangeAlphabet = "a".."d"

        // С помощью специальных операторов можно проверить наличие или отсутствие элементов в последовательности:
        //in: возвращает true, если объект имеется в последовательности
        //!in: возвращает true, если объект отсутствует в последовательности
        range = 1..5

        var isInRange = 5 in range
        println(isInRange)      // true

        isInRange = 86 in range
        println(isInRange)      // false

        var isNotInRange = 6 !in range
        println(isNotInRange)   // true

        isNotInRange = 3 !in range
        println(isNotInRange)   // false

        // С помощью цикла for можно перебирать последовательность:

        var range1 = 5 downTo 1
        for(c in range1) print(c)   // 54321
        println()

        for(c in 1..9) print(c)     // 123456789
        println()

        for(c in 1 until 9) print(c)    // 12345678
        println()

        for(c in 1..9 step 2) print(c)  // 13579
    }
}