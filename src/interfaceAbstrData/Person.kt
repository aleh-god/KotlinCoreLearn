package interfaceAbstrData

/*
Наследование позволяет создавать классы, которые расширяют функциональность или изменяют поведение уже существующих классов.
В отношении наследования выделяются два ключевых компонента. Прежде всего это базовый класс (класс-родитель, родительский класс, суперкласс),
который определяет базовую функциональность. И производный класс (класс-наследник, подкласс), который наследует функциональность базового
класса и может расширять или модифицировать ее.

Также, стоит отметить, что все классы по умолчанию наследуются от класса Any, даже если класс Any явным образом не
указан в качестве базового. Поэтому любой класс уже по умолчанию будет иметь все свойства и функции, которые определены
в классе Any. Поэтому все классы по умолчанию уже будут иметь такие функции как equals, toString, hashcode.
 */

open class Person(val name: String) {
// Чтобы функциональность класса можно было унаследовать, необходимо определить для этого класса аннотацию open.
}

class Employee: Person{                         // Для определения производного класса после его имени ставится двоеточие, после которого идет название базового класса.

    var company: String="undefined"

    // Если производный класс не имеет явного первичного конструктора, то работаем через вторичный
    constructor(name: String, comp: String) :    // Сука, это не свойства! А параметры передаваемые в конструктор
            super(name){                        // Класс collections.Person через первичный конструктор устанавливает свойство name.
        company = comp
    }
}

fun main(args: Array<String>) {

    val alice: Person = Person("Alice")
    val perName : String = alice.name
    println("alice.name = ${alice.name}")

    val kate: Employee = Employee("Kate", "Google")
    // Причем поскольку объект Employee в то же время является и объектом класса collections.Person в силу отношения наследования, то мы можем переменной типа collections.Person передать объект Employee:
    val liza: Person = Employee("Liza", "Apple")
}