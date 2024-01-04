package genericVariance

// Подтип Box<Child> должен принимать по крайней мере тот же набор типов, что и его супертип Box<Parent>.
// Подтип Box<Child> должен возвращать не более того же набора типов, что и его супертип Box<Parent>.

interface ProducerBox<T> {
    // Удалили функцию, которая нарушает принцип ПБЛ
    // fun consume(item: T)
    fun produce(): T
}

interface BoxFixOut<out T> {
    // out декларирует что в классе не будет методов принимающих Т как параметр функции
    // А раз нет, то BoxFix<Child> будет дочерним к BoxFix<Parent>
    fun produce(): T
}

class ParentBoxFixOut : BoxFixOut<Parent> {
    override fun produce(): Parent {
        return Parent("Marley")
    }
}

class ChildFixOut : BoxFixOut<Child> {
    override fun produce(): Child {
        return Child("Bob", "Marley")
    }
}

class PhotoShelfFixOut() {
    fun shelf(box: BoxFixOut<Parent>) {
        println("Photo: ${box.produce().family}")
    }
}

fun main() {
    val parentBox = ParentBoxFixOut()
    val childBox = ChildFixOut()

    val box: BoxFixOut<Parent> = childBox

    val photoShelf = PhotoShelfFixOut()
    photoShelf.shelf(box)
    photoShelf.shelf(childBox)
}
