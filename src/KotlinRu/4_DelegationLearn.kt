package com.learnxinyminutes.kotlin.KotlinRu

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/*
Делегирование (англ. Delegation) — основной шаблон проектирования, в котором объект внешне выражает некоторое поведение,
но в реальности передаёт ответственность за выполнение этого поведения связанному объекту.

Ключевое слово by в оглавлении Derived, находящееся после типа делегируемого класса,
говорит о том, что объект b типа Base будет храниться внутри экземпляра Derived,
и компилятор сгенерирует у Derived соответствующие методы из Base, которые при вызове будут переданы объекту b
 */
fun main(args: Array<String>) {

    // Делегирование функций
    val b = BaseImpl(10)
    DerivedX(b).print() // prints 10

    println()

    // Делегированные свойства

    // Когда мы читаем значение свойства p, вызывается метод getValue() класса Delegate,
    // причем первым параметром ей передается тот объект, у которого запрашивается свойство p,
    // а вторым — объект-описание самого свойства p (у него можно, в частности, узнать имя свойства).
    val e = ExampleX()
    println(e.p)

    // Стандартные делегаты
    //Стандартная библиотека Kotlin предоставляет несколько полезных видов делегатов:

    //Ленивые свойства (lazy properties)
    // lazy() это функция, которая принимает лямбду и возвращает экземпляр класса Lazy<T>, который служит делегатом для реализации ленивого свойства: первый вызов get() запускает лямбда-выражение, переданное lazy() в качестве аргумента, и запоминает полученное значение, а последующие вызовы просто возвращают вычисленное значение.
    val lazyValue: String by lazy {
        println("computed!")
        "Hello"
    }

    println(lazyValue) // computed! Hello
    println(lazyValue) // Hello

    // Observable свойства
    //Функция Delegates.observable() принимает два аргумента: начальное значение свойства и обработчик (лямбда), который вызывается при изменении свойства. У обработчика три параметра: описание свойства, которое изменяется, старое значение и новое значение.
    val user = User()
    user.name = "first"     // <no name> -> first
    user.name = "second"    // first -> second

    // Хранение свойств в ассоциативном списке

    // конструктор принимает ассоциативный список
    val userMap = UserMap(mapOf(
        "name" to "John Doe",
        "age"  to 25
    ))

    // Делегированные свойства берут значения из этого ассоциативного списка (по строковым ключам)
    println(userMap.name) // Prints "John Doe"
    println(userMap.age)  // Prints 25

    /*
    Локальные делегированные свойства
    Переменная memoizedFoo будет вычислена только при первом обращении к ней.

    fun example(computeFoo: () -> Foo) {
        val memoizedFoo by lazy(computeFoo)

        if (someCondition && memoizedFoo.isValid()) {  // Если условие someCondition будет ложно, значение переменной не будет вычислено вовсе.
            memoizedFoo.doSomething()
        }
    }

     */

}

interface BaseX {
    fun print()
}

class BaseImpl(val x: Int) : BaseX {
    override fun print() { print(x) }
}

// компилятор сгенерирует у Derived соответствующие методы из Base, которые при вызове будут переданы объекту b
class DerivedX(b: BaseX) : BaseX by b

class ExampleX {
    var p: String by DelegateX() // Выражение после by — делегат: обращения (get(), set()) к свойству будут обрабатываться этим выражением.
}

// Делегат не обязан реализовывать какой-то интерфейс, достаточно, чтобы у него были методы getValue() и setValue() с определённой сигнатурой:
class DelegateX {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, спасибо за делегирование мне '${property.name}'!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value было присвоено значению '${property.name} в $thisRef.'")
    }
}

class User {
    // Принимает два аргумента: начальное значение свойства и обработчик (лямбда)
    var name: String by Delegates.observable("<no name>") {
        // У обработчика три параметра: описание свойства, которое изменяется, старое значение и новое значение.
            prop, old, new ->
        println("$old -> $new")
    }
}

// Хранение свойств в ассоциативном списке
class UserMap(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int     by map
}