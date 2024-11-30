package oopLearn

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

fun main() {
    val user: User = User.Authenticated("Jesus")
    onScreenLoaderGood(user)
}

@Suppress("unused")
internal fun onScreenLoaderBad(user: User) {
    if (user is User.Authenticated) {
        // there is smart cast
        user.greet()
    } else {
        // there is no smart cast, need a second if operator
        if (user is User.Anonymous) {
            user.promptToSign()
        }
    }
}

internal fun onScreenLoaderGood(user: User) {
    if (user.isAuthenticated()) {
        user.greet()
    } else {
        user.promptToSign()
    }
}

// Effects                      Conditions
// returns(true) implies (this@User is Authenticated)
//          false           x !is Type
//          null            x == null
//      returnNotNull()     x != null

internal sealed class User() {

    // When this function
    @OptIn(ExperimentalContracts::class)
    fun isAuthenticated(): Boolean {
        // returns true it implies that this user is Authenticated
        // returns false it implies that this user is Anonymous
        contract {
            returns(true) implies (this@User is Authenticated)
            returns(false) implies (this@User is Anonymous)
        }
        return this is Authenticated
    }

    class Authenticated(val userName: String) : User() {

        fun greet() = println("Welcome^ $userName")
    }

    class Anonymous() : User() {

        fun promptToSign() = println("Please sign in.")
    }
}