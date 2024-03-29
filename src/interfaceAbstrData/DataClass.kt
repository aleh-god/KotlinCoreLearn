package interfaceAbstrData

// Иногда классы бывают необходимы только для хранения некоторых данных. В Kotlin такие классы называются data-классы.
// При этом чтобы класс определить как data-класс, он должен соответствовать ряду условий:

// Первичный конструктор должен иметь как минимум один параметр
// Все параметры первичного конструктора должны предваряться ключевыми словами val или var, то есть определять свойства
// Свойства, которые определяются вне первичного конструктора, не используются в функциях equals и hashCode
// Класс не должен определяться с модификаторами data, abstract, sealed или inner.

data class PersonData(val name: String, val age: Int) // Определяются с модификатором data:

// При компиляции такого класса компилятор автоматически добавляет в класс функции:
// equals(): сравнивает два объекта на равенство
// hashCode(): возвращает хеш-код объекта
// toString(): возвращает строковое представление объекта
// copy(): копирует данные объекта в другой объект
// Причем при выполнении действий эти функции учитывают все свойства, которые определены в первичном конструкторе.

fun main(args: Array<String>) {

    // Но вообще в ряде ситуаций рекомендуется определять свойства через val, то есть делать их неизменяемыми,
    // поскольку на их основании вычисляет хеш-код, который используется в качестве ключа объекта в такой коллекции как HashMap.

    val alice: PersonData = PersonData("Alice", 24)
    println(alice.toString())           // collections.Person@2a18f23c если обычный class
                                        // collections.Person(name=Alice, age=24) если data class

    // Опять же компилятор генерирует функцию копирования по умолчанию, которую мы можем использовать.
    val kate = alice.copy()  // copy() копирование данных:
    println("Сравниваем объекты по содержанию: ${alice == kate}")   // equals() - сравнивает объекты, а не ссылки на объекты
    println("Сравниваем ссылки на объекты: ${alice === kate}")   // сравнивает ссылки, а не объекты

    // Если мы хотим, чтобы некоторые данные у объкта отличались, то мы их можем указать в функции copy в виде именованных арументов, как в случае со свойством name в примере выше.
    val jon = alice.copy(name = "Jon")
    println(alice.toString())   // collections.Person(name=Alice, age=24)
    println(jon.toString())    // collections.Person(name=Kate, age=24)


}