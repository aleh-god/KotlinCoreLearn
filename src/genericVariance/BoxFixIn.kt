package genericVariance

// Подтип Box<Parent> должен принимать по крайней мере тот же набор типов, что и его супертип Box<Child>.
// Подтип Box<Parent> должен возвращать не более того же набора типов, что и его супертип Box<Child>.

interface ConsumerBox<T> {
    // Удалили функцию, которая нарушает принцип ПБЛ
    // fun produce(): T
    fun consume(item: T)
}

interface BoxFixIn<in T> {
    // in декларирует что в классе не будет методов возвращающих Т как результат
    // А раз нет, то BoxFix<Child> будет родителем к BoxFix<Parent>
    fun consume(item: T)
}

class ParentBoxFixIn : BoxFixIn<Parent> {
    override fun consume(item: Parent) {
        println(item.family)
    }
}

class ChildFixIn : BoxFixIn<Child> {
    override fun consume(item: Child) {
        println("${item.family} + ${item.name}")
    }
}

class PhotoShelfFixIn {
    fun shelf(box: BoxFixIn<Child>) {
        box.consume(Child("Bob", "Marley"))
    }
}

fun main() {
    val parentBox = ParentBoxFixIn()
    val childBox = ChildFixIn()
    val box: BoxFixIn<Child> = parentBox

    val photoShelf = PhotoShelfFixIn()
    photoShelf.shelf(childBox)
    photoShelf.shelf(parentBox)
    photoShelf.shelf(box)
}
