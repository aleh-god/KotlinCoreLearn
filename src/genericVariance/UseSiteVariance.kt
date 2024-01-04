package genericVariance

interface WritableGroup<in T> {
    fun insert(item: T): Unit
}
interface ReadableGroup<out T> {
    fun fetch(): T
}

interface Group<T> : ReadableGroup<T>, WritableGroup<T>

class GroupParentImpl : Group<Parent> {
    override fun fetch(): Parent {
        return Parent("Marley")
    }

    override fun insert(item: Parent) {
        println(item.family)
    }
}

class GroupChildImpl : Group<Child> {
    override fun fetch(): Child {
        return Child("Bob", "Marley")
    }

    override fun insert(item: Child) {
        println("${item.family} + ${item.name}")
    }
}

// Kotlin дает нам возможность эффективно разделить интерфейсы без необходимости писать эти интерфейсы самим.
// Kotlin генерирует под капотом дополнительные функции:
// fun fetch(): Any?
// fun insert(item: Nothing)
// которые не могут быть выполнены, но помогают сохранить выполнение принципа ПБЛ.
interface OriginalGroup<T> {
    fun insert(item: T): Unit
    fun fetch(): T
}

class OriginalGroupParentImpl : OriginalGroup<Parent> {
    override fun insert(item: Parent) {
        println(item.family)
    }

    override fun fetch(): Parent {
        return Parent("Marley")
    }
}

class OriginalGroupChildImpl : OriginalGroup<Child> {
    override fun insert(item: Child) {
        println("${item.family} + ${item.name}")
    }

    override fun fetch(): Child {
        return Child("Bob", "Marley")
    }
}

fun main() {

    fun readX(group: OriginalGroup<Parent>) = println(group.fetch())
    fun writeX(group: OriginalGroup<Parent>) = group.insert(Child("Bob", "Marley"))

    val originalGroupChildImpl = OriginalGroupChildImpl()
    // Ошибка! Мы не можем подставить Group<Child> как Group<Parent> в аргументы метода
    //    readX(originalGroupChildImpl)
    //    writeX(originalGroupChildImpl)

    val originalGroupParentImpl = OriginalGroupParentImpl()
    readX(originalGroupParentImpl)
    writeX(originalGroupParentImpl)

    fun read(group: ReadableGroup<Parent>) = println(group.fetch())
    fun write(group: WritableGroup<Parent>) = group.insert(Child("Bob", "Marley"))

    val groupChildImpl = GroupChildImpl()
    // Ошибка! Мы не можем подставить Group<Child> как Group<Parent> в аргументы метода
    //    write(groupChildImpl)
    // Но разделение на интерфейсы ReadableGroup<T>, WritableGroup<T> уже позволяют нам подставить в определенный метод
    read(groupChildImpl)

    val groupParentImpl = GroupParentImpl()
    read(groupParentImpl)
    write(groupParentImpl)

    // Kotlin дает нам возможность эффективно разделить интерфейсы без необходимости писать эти интерфейсы самим.
    // Мы помещаем variance annotations (out and in) в аргументы — в тот момент кода, где мы используем generic,
    // поэтому мы называем это вариантностью использования-сайта use-site variance.

    // Однако в том месте, где мы используем Group, возможно, мы будем взаимодействовать с ним только одним из этих способов.
    // Например, вы можете только produce предмет. Или только consume.
    // Мы можем сообщить об этом намерении компилятору, добавив in или out аннотацию, например:
    fun read(group: OriginalGroup<out Parent>) = println(group.fetch())
    fun write(group: OriginalGroup<in Parent>) = group.insert(Parent("Marly"))

    read(originalGroupChildImpl)
    //    write(originalGroupChildImpl)
    read(originalGroupParentImpl)
    write(originalGroupParentImpl)

    // В этой функции у нас есть параметр с именем group, тип которого не, Group<T> а скорее проекция Group<T>.
    // Поскольку мы объявили аргумент типа как out, сеттеры (или, если уж на то пошло, любая функция, которая принимает этот параметр типа в качестве аргумента) удаляются из проекции.
    // Мы пообещали компилятору, что не будем использовать сеттер в этой функции.
    // Создавая проекцию, мы теряем некоторые функциональные возможности этого типа.
    fun readChild(group: OriginalGroup<out Child>) = println(group.fetch())
    fun writeChild(group: OriginalGroup<in Child>) = group.insert(Child("Bob", "Marley"))

    readChild(originalGroupChildImpl)
    writeChild(originalGroupChildImpl)
    //    readChild(originalGroupParentImpl)
    writeChild(originalGroupParentImpl)
}
