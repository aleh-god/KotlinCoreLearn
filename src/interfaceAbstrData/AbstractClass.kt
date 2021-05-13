package interfaceAbstrData

// Абстрактный класс, как и обычный, может иметь свойства, функции, конструкторы,
// но создать его объект напрямую вызвав его конструктор мы не можем.
abstract class HumanAbstract(val name: String){
    // Абстрактные классы могут иметь абстрактные методы. Это такие функции, которые определяются с ключевым словом
    // abstract и не содержат реализацию, то есть у них нет тела.
    // При этом абстрактные методы можно определить только в абстрактных классах:
    abstract fun hello()
}

// Такой класс мы можем только унаследовать:
class PersonAbstract(name: String): HumanAbstract(name) {
    // Если класс наследуется от абстрактного класса, то он должен реализовать все его абстрактные методы.
    override fun hello(){               // применяется аннотация override
        println("My name is $name")
    }
}

// мы можем определить абстрактный класс фигуры и затем от него унаследовать все остальные классы фигур:
abstract class Figure {
    // абстрактный метод для получения периметра
    abstract fun perimeter(): Float

    // абстрактный метод для получения площади
    abstract fun area(): Float
}
// производный класс прямоугольника
class Rectangle(val width: Float, val height: Float) : Figure()
{
    // переопределение получения периметра
    override fun perimeter(): Float{
        return width * 2 + height * 2;
    }
    // переопрелеление получения площади
    override fun area(): Float{
        return width * height;
    }
}

fun main(args: Array<String>) {

    val kate: PersonAbstract = PersonAbstract("Kate")
    val slim: HumanAbstract = PersonAbstract("Slim Shady")
    kate.hello()    // My name is Kate
    slim.hello()    // My name is Slim Shady
}