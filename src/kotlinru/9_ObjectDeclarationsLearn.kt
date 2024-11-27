package kotlinru

fun main(args: Array<String>) {
    val publicMain = "I am publicMain"

    // Objects in Kotlin
    // Отдельные сущности, сами по себе. Как если бы написали класс, создали синглтон и использовали


    println(SimpleSingleton.answer)
    println(SimpleSingleton.greet("world"))

    Counter.increment()
    println(Counter.currentCount())
    // println(KotlinRu.Counter.count) // this will fail to compile

    val strings = listOf("Hello", "World")
    val sortedStrings = strings.sortedWith(ReverseStringComparator)
    println(sortedStrings)

    // Companion objects всегда объявляется внутри другого класса.
    // Хотя у него может быть имя, оно не обязательно, и в этом случае оно автоматически получает имя Companion:

    println(OuterClass.publicCompanion)
    // assertEquals("You can't see me", KotlinRu.OuterClass.secretCompanion) // Cannot access 'secret'
    val outerClass = OuterClass()
    outerClass.getSecretValue()
    outerClass.takeInfoFromCompanion()
    // outerClass.takeInfoFromClass() // unresolved reference

    // Анонимные объекты
    // Внутри метода, не нужно имя. Отдельно от классов не могут существовать.
    object {
    }

    // Можно присвоит свойству и использовать члены объекта
    val adHoc = object {
        var x: Int = 0
        var y: Int = 0
        // Код внутри объявленного объекта может обращаться к переменным за скобками
        val info = publicMain
        fun printInfo() {
            println(info)
        }
    }
    print(adHoc.x + adHoc.y)
    adHoc.printInfo()

}

// Иногда нам необходимо получить и использовать экземпляр некоторого класса без лишнего кода
object SimpleSingleton {
    val answer = 42;
    fun greet(name: String) = "Hello, $name!"
}

open class A(x: Int) {
    val y: Int = x
    private val xxx = "secret class value"
}

interface B {
    fun printInfo()
}

// Объект может наследовать классы и реализовывать интерфейсы
// Конструкторы супер-классов должны быть использованы
object PlusAB : A(1), B {

    override fun printInfo() {
        println("class KotlinRu.A to $y")
        // println(xxx) // Нет доступа к закрытому полю
    }
}

object Counter {
    // Objects also offer full support for visibility modifiers
    private var count: Int = 0

    fun currentCount() = count

    fun increment() {
        ++count
    }
}

object ReverseStringComparator : Comparator<String> {
    override fun compare(o1: String, o2: String) = o1.reversed().compareTo(o2.reversed())
}

class OuterClass {
    private val secretClass = "I am secretClass"
    val publicClass = "I am publicClass"

    // Можем использовать все поля companion object
    fun takeInfoFromCompanion() {
        println(secretCompanion)
        println(publicCompanion)
        takeInfoFromClass()
    }

    fun getSecretValue() = secretCompanion

    companion object {
        // const - значение свойства уже известно на этапе компиляции. Актуально для объектов.
        private const val secretCompanion = "You can't see me"
        const val publicCompanion = "You can see me"
        @JvmStatic // Не может быть в классах, только в объектах - для генерации корректного static кода для Java
        val staticField = 42

        // Без объекта класса доступа к полям нет
        fun takeInfoFromClass() {
//             println(secretClass) // unresolved reference
            // println(publicClass) // unresolved reference
            print("I am companion fun")
        }
    }
}

class StaticClass {
    companion object {
        @JvmStatic
        val staticField = 42
    }

    private val key = "private_key"

    fun getWord() = InnerClass().word

    inner class InnerClass() {

        val word = "CAN_NOT_use_private_word"

        fun getKey() = StaticClass().key
    }
}
