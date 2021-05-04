package InterfaceAbstrData

/*
Kotlin позволяет переопределять функции, определенные в базовом классе. Чтобы функции базового класа можно было
переопределить, к ним применяется аннотация open. При переопределении в производном классе к этим функциям применяется
аннотация override:
 */

open class PersonOverride(val name: String){

    // Чтобы сделать свойство доступным для переопределения в базовом классе оно также объявляется с аннотацией open:
    open val fullInfo: String
        get() = "Name: $name"

    open fun display(){
        println("Name: $name")
    }
}

open class EmployeeOverride(name: String, val company: String): PersonOverride(name){

    // Свойства переопределяются также как и функции с помощью ключевого слова override.
    override val fullInfo: String
        get() = "Name: $name Company: $company"

    override fun display() {
        println("Name: $name Company: $company")
    }
}

// переопределить функции можно по всей иерархии наследования. Например, у нас может быть класс Manager, унаследованный от Employee:
class Manager(private val position: String, company: String, name: String): EmployeeOverride(company, name){

    override val fullInfo: String
        // С помощью ключевого слова super в производном классе можно обращаться к реализации из базового класса.
        get() = "${super.fullInfo} Position: $position"

    // Иногда бывает необходимо запретить дальнейшее переопределение функции в классах-наследниках. Для этого применяется ключевое слово final:
    final override fun display() {
        super.display() // с помощью вызова super.display() вызывается реализация функции display из базового класса.
        println(" Position: $position")
    }
}

open class Video {
    // переопределяемая функция из базового класса имеет то же имя, что и функция из применяемого интерфейса:
    open fun play() { println("Play video") }
}

interface AudioPlayable {
    // переопределяемая функция из интерфейса имеет то же имя, что и функция из применяемого базового класса:
    fun play() { println("Play audio") }
}

class MediaPlayer() : Video(), AudioPlayable {
    // Здесь класс Video и интерфейс AudioPlayable определяют функцию play.
    // Функцию play обязательно надо переопределить
    override fun play() {
        super<Video>.play()         // вызываем Video.play()
        super<AudioPlayable>.play() // вызываем AudioPlayable.play()
    }
}

fun main(args: Array<String>) {

    val alice: PersonOverride = PersonOverride("Alice")
    alice.display()
    val kate: PersonOverride = EmployeeOverride(name="Kate", company="Google")
    kate.display()
    val liza: PersonOverride = Manager(name="Liza", company="Apple", position="Manager")
    liza.display()
}