package genericVariance

interface Box<T> {

    fun consume(item: T)

    fun produce(): T
}

// Подтип должен принимать по крайней мере тот же диапазон типов, который объявлен в его супертипе.
// Подтип должен возвращать не более того диапазона типов, который объявлен в его супертипе.

class ParentBox : Box<Parent> {
    override fun consume(item: Parent): Unit { // Возвращают один тип, принимает Parent
        println("${item.family}")
    }

    override fun produce(): Parent { // Возвращают Parent
        return Parent("Marley")
    }
}

class ChildBox : Box<Child> {
    override fun consume(item: Child): Unit { // Возвращают один тип, принимает только Child, а это нарушение принципа ПБЛ
        println("${item.name} + ${item.family}")
    }

    override fun produce(): Child { // Возвращают не Parent, но хотя бы Child
        return Child("Bob", "Marley")
    }
}

class PhotoShelf() {
    fun shelf(box: ParentBox) {
        println("Photo: ${box.produce().family}")
    }
}

fun main() {
    val parentBox = ParentBox()
    val childBox = ChildBox()

    val photoShelf = PhotoShelf()
    photoShelf.shelf(parentBox)
    // photoShelf.shelf(childBox) // Ошибка! Принцип подстановки не работает
}
