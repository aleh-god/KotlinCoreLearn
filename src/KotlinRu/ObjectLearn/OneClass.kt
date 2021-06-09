package com.learnxinyminutes.kotlin.KotlinRu.ObjectLearn

class OneClass {
    private val xxx = "I am private properties OneClass."
    val info = "I am public properties OneClass."

    // object {    }            // Внутри класса не может быть анонимный объект
    val objLink = object { }    // Ссылку на анонимный класс можно присвоить свойству и использовать

    fun getX(input: String): String {
        object {    }           // Внутри метода может быть анонимный объект
        return "$xxx $input"
    }

    object Inner {
        private const val xInner = "I am private properties Inner."
        const val infoInner = "I am public properties Inner."

        fun printInfoObject() {
            println(xInner)
            // println(info)                        // Без объекта класса нет доступа к членам класса
            println(OneClass().info)
            println(OneClass().xxx)                 // Есть доступ к приватным полям класса, так как создан объект внутри этого класса
        }
    }

    fun printInfoOneClass() {
        println(Inner.infoInner)                    // Обращение к объекту через имя объекта
        // println(Inner.xInner)                    // Нет доступа к private членам объекта Inner объявленного в OneClass
        println(CompanionClass.infoCompanion)       // Обращение к компаньону через имя класса
        // println(CompanionClass.xCompanion)       // Нет доступа к private членам companion object объявленного в CompanionClass
    }

}

class CompanionClass {
    private val xCompanionClass = "I am private properties CompanionClass."
    val infoCompanionClass = "I am public properties CompanionClass."

    fun printInfoCompanionClass() {
        println(OneClass.Inner.infoInner)           // Доступ к членам объекта, через имя класса
        // println(OneClass.Inner.xInner)           // Нет доступа к private членам объекта Inner объявленного в OneClass
        println(Counter.info)                       // Доступ к членам объекта, через имя объекта
        println(infoCompanion)                      // Прямой доступ к членам companion object
        printInfoCompanion()
        println(xCompanion)                         // Есть доступ к приватным полям companion object

    }

    // Вместо имени объекта можно указать companion и ссылаться на имя класса
    companion object {
        private const val xCompanion = "I am private properties Companion."
        const val infoCompanion = "I am public properties Companion."

        fun printInfoCompanion() {
            println(infoCompanion)
            println(xCompanion)
            println(OneClass.Inner.infoInner)                // Обращение к объекту через имя класса, где он объявлен
            // println(infoCompanionClass)                   // Без объекта класса нет доступа к членам класса
            println(CompanionClass().infoCompanionClass)
            println(CompanionClass().xCompanionClass)       // К приватным полям класса доступ есть
        }
    }
}

// Объект вне классов. Короткая запись, вместо реализации патерна синглтон и его потоко-безопасного запуска
object Counter {
    // Objects also offer full support for visibility modifiers
    private var count: Int = 0
    const val info = "I am public object."

    fun currentCount() = count

    fun increment() {
        ++count
    }
}

