package InterfaceAbstrData

// Для применения интерфейса после имени класса ставится двоеточие, за которым следует название интерфейса.
// При применении интерфейса класс должен реализовать все его абстрактные методы и свойства, а также может
// предоставить свою реализацию для тех свойств и методов, которые уже имеют реализацию по умолчанию.

class Aircraft : Movable{
    // При реализации функций перед ними ставится ключевое слово override.
    override fun move() =
        println("Самолет летит")

    override fun stop() = println("Приземление")

}

class Car(mod: String, num: String) : Movable, Info{

    override val number: String         // реализация свойств интерфейса Info
    override val model: String
    init{
        number = num
        model = mod
    }

    override fun move(){
        println("Машина едет")
    }

    // Функцию stop класс Car может не реализовать, так как она уже содержит реализацию по умолчанию.
}

fun main(args: Array<String>) {

    var m: Movable = Car("Tesla", "2345SDG")
    m.move()
    m.stop()
    m = Aircraft()
    m.move()
    m.stop()

    var info: Info = Car("Tesla", "2345SDG")
    println(info.model)
    println(info.number)
}