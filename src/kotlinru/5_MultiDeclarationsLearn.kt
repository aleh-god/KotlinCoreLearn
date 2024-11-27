package com.learnxinyminutes.kotlin.KotlinRu

fun main(args: Array<String>) {
    val (resultX, statusX) = Result(5, "install")
    println(resultX)
    println(statusX)

    val (result, status) = multiFun(4, "prepare")
    println(result)
    println(status)

    val multiList = listOf<Result>(Result(0, "start"), Result(1, "begin"), Result(3, "end"))

    for ((a,b) in multiList) {
        println("$a to $b")
    }

    for (i in 1..4) print(i) // prints "1234"

    for (i in 4 downTo 1) print(i) // prints nothing

    for (i in 1..4 step 2) print(i) // prints "13"

    for (i in 4 downTo 1 step 2) print(i) // prints "42"


}

data class Result(val result: Int, val status: String)

fun multiFun(result: Int, status: String): Result {

    // вычисления

    return Result(result, status)
}
