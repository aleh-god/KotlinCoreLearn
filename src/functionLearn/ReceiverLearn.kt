package com.learnxinyminutes.kotlin.functionLearn

import java.lang.StringBuilder

fun main() {

    println("In Kotlin it is possible to add a method (called member function in Kotlin) to an existing class. This is called an extension function.")
    extensionFunction()

    println("It is also possible to access a member function from a class inside a function literal. This is called a function literal with receiver.")
    functionLiteralWithReceiver()
}

/*
Extension function
Say you want to add a new member function to the StringBuilder class, e.g. appendMonkey() that appends the string “monkey” to a string. Because StringBuilder is in the Java SDK, we cannot modify it. In Kotlin we can define an extension function that extends an existing class like StringBuilder with a new member function.

We define the appendMonkey() extension function as follows:
 */

fun extensionFunction() {
    var sb = StringBuilder()

    sb.append("Hello extension function ")
    sb.appendMonkey()

    println(sb.toString())

    sb = appendMonkeyClassIsArgument(sb)

    println(sb.toString())
}

// fun StringBuilder.appendMonkey(): StringBuilder = append("monkey")
fun StringBuilder.appendMonkey(): StringBuilder {
    this.append("monkey")
    return this
}

fun appendMonkeyClassIsArgument(stringB: StringBuilder): StringBuilder {
    stringB.append(" monkeyIsArgument")
    return stringB
}

/*
Function literal with receiver
Closely related to the extension function is the function literal with receiver. There are two types of function literals:
1. lambda
2. anonymous function

Where with extension functions you can add a new member function to an existing class, with a function literal with receiver you can access the member functions of an existing class inside the lambda block (inside the curly braces {}).

Let's create a function literal with receiver that adds the string "monkey" to a string using the StringBuilder class.

Here, inside the block of the lambda we can access the append() member function because we explicitly stated the return type to be StringBuilder.() -> StringBuilder.
This return type basically says:
"return a function that takes no arguments and returns a StringBuilder object, and give this function access to the StringBuilder member functions"
 */
fun functionLiteralWithReceiver() {
    val lambdaAppendMonkey: StringBuilder.() -> StringBuilder = { this.append("monkey") }

    var sb = StringBuilder()
    sb.append("Hello lambda ")
    sb = lambdaAppendMonkey(sb)

    println(sb.toString())
}
