package InterfaceAbstrData

// Enums или перечисления представляют тип данных, который позволяет определить набор логически связанных констант.
// Для определения перечисления применяются ключевые слова enum class. Например, определим перечисление:

enum class Day{
    // Внутри перечисления определяются константы. В данном случае это названия семи дней недели.
    // Каждая константа фактически представляет объект данного перечисления.
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

// Классы перечислений как и обычные классы также могут иметь конструктор.
// Кроме того, для констант перечисления также может вызываться конструктор для их инициализации.
enum class DayX(val value: Int){        //  у класса перечисления через конструктор определяется свойство value.
    MONDAY(1), TUESDAY(2), WEDNESDAY(3),
    THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(100500); // Но если класс перечисления содержит свойства или функции, то константы должны быть отделены точкой с запятой.
    // Они могут определять также свойства и функции.
    fun getDuration(dayX: DayX): Int{
        return value - dayX.value;
    }
}

// Классы перечислений могут применять интерфейсы.
// Для этого для каждой константы определяется анонимный класс, который содержат все реализуемые свойства и функции:
interface Printable{
    fun printName()
}
enum class DayTime: Printable{
    DAY{
        override fun printName(){
            println("День")
        }
    },
    NIGHT{
        override fun printName(){
            println("Ночь")
        }
    }
}

fun main(args: Array<String>) {

    val day: Day = Day.FRIDAY
    println(day)            // FRIDAY
    println(Day.MONDAY)     // MONDAY
    println(Day.TUESDAY)

    val dayX: DayX = DayX.FRIDAY
    println(dayX.value)        // 5
    println(DayX.MONDAY.value) // 1

    val day1: DayX = DayX.FRIDAY
    val day2: DayX = DayX.MONDAY
    println(day1.getDuration(day2))

    // Все перечисления обладают двумя встроенными свойствами:
    // name: возвращает название константы в виде строки
    // ordinal: возвращает название порядковый номер константы
    println(day1.name)        // FRIDAY
    println(day1.ordinal)     // 4

    // Кроме того, в Kotlin нам доступны вспомогательные функции:
    // valueOf(value: String): возвращает объект перечисления по названию константы
    // values(): возвращает массив констант текущего перечисления
    for(day in DayX.values())
        println(day)

    println(DayX.valueOf("FRIDAY"))

    // Анонимные классы и реализация интерфейсов
    DayTime.DAY.printName()     // День
    DayTime.NIGHT.printName()   // Ночь

}