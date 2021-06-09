import coreLearn.*

fun main(args: Array<String>){
    println("Hello Kotlin")

    var lesson: CoreLearn

    lesson = Values()
    lesson.showResult()
    lesson = Operations()
    lesson = BooleanExp()
    lesson = ConditionalConstructs()
    lesson = CycleAndSequence()
    lesson = ArrayLearn()

    lesson.showResult()

}