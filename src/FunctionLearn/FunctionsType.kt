package FunctionLearn

class FunctionsType : FunctionLearn {

    override fun showResult() {

    }

    // Однострочные функции
    // Однострочные функции (single expression function) используют сокращенный синтаксис определения функции в виде одного выражения.
    // Эта форма позволяет опустить возвращаемый тип и оператор return.
    //fun имя_функции (параметры_функции) = тело_функции

    fun simpleDouble(x: Int) = x * x

    // Такие функции более лаконичны, более читабельны, но также опционально можно и указывать возвращаемый тип явно:
    fun double(x: Int) : Int = x * x

    // Локальные функции

    // Одни функции могут быть определены внутри других функций. Внутренние или вложенные функции еще называют локальными.
    //Локальные функции могут определять действия, которые используются только в рамках какой-то конкретной функции и нигде больше не применяются.

    //Например, функция принимает на вход основание и высоту двух треугольников и должна вычислить, больше ли площадь первого треугольника, чем второго:
    fun isFirstGreater(base1: Double, height1: Double, base2: Double, height2: Double): Boolean{
        //Для промежуточных вычислений - вычисления площади каждого отдельного треугольника в функции isFirstGreater определена вспомогательная функция square.
        // Больше в программе эта функция нигде не используется, поэтому ее можно сделать локальной.
        fun square(base: Double, height: Double) = base * height / 2

        return square(base1, height1) > square(base2, height2)
    }

    // Перегрузка функций (function overloading) представляет определение нескольких функций с одним и тем же именем, но с различными параметрами.
    // Параметры перегруженных функций ДОЛЖНЫ отличаться по количеству, типу или по порядку в списке параметров.

    fun add(a: Int, b: Int) : Int{
        return a + b
    }
    fun add(a: Double, b: Double) : Double{
        return a + b
    }
    fun add(a: Int, b: Int, c: Int) : Int{
        return a + b + c
    }
    fun add(a: Int, b: Double) : Double{
        return a + b
    }
    fun add(a: Double, b: Int) : Double{
        return a + b
    }

    // Анонимные функции
    fun operation(x: Int, y: Int, op: (Int, Int) ->Int){

        val result = op(x, y)
        println(result)
    }

}