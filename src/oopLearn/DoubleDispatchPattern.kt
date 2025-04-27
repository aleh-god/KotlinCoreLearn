package oopLearn

internal interface Dog {

    val name: String

    // Какую функцию использовать при вызове называется - диспетчеризацией (Dispatch)
    // Kotlin, как язык OOP, использует диспетчеризацию - runtime on the receiver
    // Но для аргументов использует - compile time
    // Поддержка one actual receiver and one actual argument - that's two types - double dispatch
    // Поддержка actual runtime type for receiver and arguments - multiple dispatch
    fun reactTo(person: Person) = println("Dog-Person:$name is reacting to ${person.name}")
}

internal interface Person {
    val name: String
}

internal object Owner : Person {
    override val name: String = "Jesus"
}

internal class Enemy(override val name: String) : Person

internal class Wolf(override val name: String) : Dog {

    fun reactTo(owner: Owner) = println("Wolf-Owner: $name is reacting to ${owner.name}")
    fun reactTo(enemy: Enemy) = println("Wolf-Enemy: $name is reacting to ${enemy.name}")
}

internal class Robodog(override val name: String) : Dog {

    fun reactTo(owner: Owner) = println("Robodog-Owner: $name is reacting to ${owner.name}")
    fun reactTo(enemy: Enemy) = println("Robodog-Enemy: $name is reacting to ${enemy.name}")
}

internal class Little(override val name: String) : Dog {

    override fun reactTo(person: Person) = println("Little-Person: $name is reacting to ${person.name}")
}

// Double Dispatch Pattern
internal interface DogDD {

    val name: String

    fun reactTo(person: OwnerDD)
    fun reactTo(person: EnemyDD)
}

internal class WolfDD(override val name: String) : DogDD {

    override fun reactTo(owner: OwnerDD) = println("WolfDD-OwnerDD: $name is reacting to ${owner.name}")
    override fun reactTo(enemy: EnemyDD) = println("WolfDD-EnemyDD: $name is reacting to ${enemy.name}")
}

internal class RobodogDD(override val name: String) : DogDD {

    override fun reactTo(owner: OwnerDD) = println("RobodogDD-OwnerDD: $name is reacting to ${owner.name}")
    override fun reactTo(enemy: EnemyDD) = println("RobodogDD-EnemyDD: $name is reacting to ${enemy.name}")
}

internal class LittleDD(override val name: String) : DogDD {

    override fun reactTo(owner: OwnerDD) = commonReactTo(owner)
    override fun reactTo(enemy: EnemyDD) = commonReactTo(enemy)

    private fun commonReactTo(person: PersonDD) = println("LittleDD-CommonPersonDD: $name is reacting to ${person.name}")
}

internal interface PersonDD {
    val name: String

    fun meet(dog: DogDD)
}

internal object OwnerDD : PersonDD {
    override val name: String = "Jesus"

    override fun meet(dog: DogDD) = dog.reactTo(this)
}

internal class EnemyDD(override val name: String) : PersonDD {

    override fun meet(dog: DogDD) = dog.reactTo(this)
}

// Double Dispatch Pattern with Kotlin way
/*
internal sealed interface PersonKW {
    val name: String

    fun meet(dog: DogKW)
}

internal class WolfKW(override val name: String) : DogDD {

    override fun reactTo(person: PersonKW) = when (person) {
        is OwnerKW -> println("WolfKW-OwnerKW: $name is reacting to ${owner.name}")
        is EnemyKW -> println("WolfKW-EnemyKW: $name is reacting to ${owner.name}")
        else -> {}
    }
}
 */

fun main() {
    val dogs: List<Dog> = listOf(Wolf("Biowolf"), Robodog("RESSI-01"), Little("Tyavka"))
    val people: List<Person> = listOf(Owner, Enemy("Devil"))

    Robodog("RESSI-01").reactTo(Owner)

    for (dog in dogs) {
        for (person in people) {
            /*
            Компилятор не имеет представления о реализациях типов. Для него это Dog и Person.
            dog это receiver - и он является актуальным типом из-за диспетчеризации.
            person это argument - и он является обобщеным типом
             */
            dog.reactTo(person)
            // Dog-Person:Biowolf is reacting to Jesus
            // Dog-Person:Biowolf is reacting to Devil
            // Dog-Person:RESSI-01 is reacting to Jesus
            // Dog-Person:RESSI-01 is reacting to Devil
            // Little-Person: Tyavka is reacting to Jesus
            // Little-Person: Tyavka is reacting to Devil
        }
    }

    println("\nDouble Dispatch Pattern")
    val dogsDD: List<DogDD> = listOf(WolfDD("Biowolf"), RobodogDD("RESSI-01"), LittleDD("Tyavka"))
    val peopleDD: List<PersonDD> = listOf(OwnerDD, EnemyDD("Devil"))

    for (dogDD in dogsDD) {
        for (personDD in peopleDD) {
            personDD.meet(dogDD)
            // WolfDD-OwnerDD: Biowolf is reacting to Jesus
            // WolfDD-EnemyDD: Biowolf is reacting to Devil
            // RobodogDD-OwnerDD: RESSI-01 is reacting to Jesus
            // RobodogDD-EnemyDD: RESSI-01 is reacting to Devil
            // LittleDD-CommonPersonDD: Tyavka is reacting to Jesus
            // LittleDD-CommonPersonDD: Tyavka is reacting to Devil
        }
    }
}
