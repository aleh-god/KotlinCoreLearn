import CoreLearn.CoreLearn
import CoreLearn.Operations
import CoreLearn.Values

fun main(args: Array<String>){
    println("Hello Kotlin")
    var lesson: CoreLearn

    lesson = Values()
    lesson = Operations()

    lesson.showResult()

}