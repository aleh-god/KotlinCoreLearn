package kotlinru.objectlearn

import com.learnxinyminutes.kotlin.KotlinRu.ObjectLearn.Counter

fun anonymObjects() {

    val publicMain = "I am publicMain"

    // Анонимные объекты
    object {
    }

    // Можно присвоит свойству и использовать члены объекта
    val adHoc = object {
        var x: Int = 0
        var y: Int = 0
        val info = publicMain         // Код внутри объявленного объекта может обращаться к переменным за скобками

        fun printInfo() {
            println(info)
            println(publicMain)       // Код внутри объявленного объекта может обращаться к переменным за скобками
        }
    }
    print(adHoc.x + adHoc.y)
    adHoc.printInfo()

    // Обращение к внешним объектам по имени этого объекта
    Counter.increment()
    println(Counter.currentCount())
}
