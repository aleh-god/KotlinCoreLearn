package patterns

private enum class UserStateClassicWay {

    // Если мы добавим новое состояние, то нам придется добавить новую ветку для каждого выражения when класса User
    Anonymous,
    Unverified,
    Authenticated,
}

// Вместо группировки кода по функциям, лучше группировать по стейтам - это упрощает контроль изменений
private class UserClassicWay(
    var email: String? = null,
    var state: UserStateClassicWay = UserStateClassicWay.Anonymous,
) {

    fun signUp(email: String) {
        when (state) {
            UserStateClassicWay.Anonymous -> {
                println("Signing up with $email")
                this.email = email
                state = UserStateClassicWay.Unverified
            }

            UserStateClassicWay.Unverified -> println("You are already signed up")
            UserStateClassicWay.Authenticated -> println("You are already signed up and authenticated")
        }
    }

    fun verifyEmail(token: String) {
        when (state) {
            UserStateClassicWay.Anonymous -> println("You must sign up")
            UserStateClassicWay.Unverified -> {
                println("Verifying with $token")
                this.email = email
                state = UserStateClassicWay.Authenticated
            }

            UserStateClassicWay.Authenticated -> println("You are already verified")
        }
    }

    fun viewContent() {
        when (state) {
            UserStateClassicWay.Anonymous -> println("Content for Anonymous")
            UserStateClassicWay.Unverified -> println("Content for Unverified")
            UserStateClassicWay.Authenticated -> println("Content for Authenticated")
        }
    }
}

// Применим state pattern и изменим код
// В state pattern это State
private interface UserState {

    fun signUp(email: String, user: User)
    fun verifyEmail(token: String, user: User)
    fun viewContent()
}

// В state pattern это Context
private class User(
    var email: String? = null,
    var state: UserState = Anonymous,
) {
    fun signUp(email: String) = state.signUp(email = email, user = this)
    fun verifyEmail(token: String) = state.verifyEmail(token = token, user = this)
    fun viewContent() = state.viewContent()
}

// В state pattern это Concrete State
// В этой реализации есть много повторяющегося кода, который можно убрать средствами Kotlin
private object Anonymous : UserState {

    override fun signUp(email: String, user: User) {
        println("Signing up with $email")
        user.email = email
        user.state = Unverified
    }

    override fun verifyEmail(token: String, user: User) = println("You must sign up")
    override fun viewContent() = println("Content for Anonymous")
}

private object Unverified : UserState {

    override fun signUp(email: String, user: User) = println("You are already signed up")
    override fun verifyEmail(token: String, user: User) {
        println("Verifying with $token")
        user.state = Authenticated
    }

    override fun viewContent() = println("Content for Unverified")
}

private object Authenticated : UserState {

    override fun signUp(email: String, user: User) = println("You are already signed up and authenticated")
    override fun verifyEmail(token: String, user: User) = println("You are already verified")
    override fun viewContent() = println("Content for Authenticated")
}

// Kotlin подход для state pattern
private class UserKotlinWay(
    var email: String? = null,
    var state: UserStateKotlinWay = UserStateKotlinWay.Anonymous,
)

private enum class UserStateKotlinWay(

    val signUp: (String, UserKotlinWay) -> Unit,
    val verifyEmail: (String, UserKotlinWay) -> Unit,
    val viewContent: () -> Unit,
) {

    Anonymous(
        signUp = { email, user ->
            println("Signing up with $email")
            user.email = email
            user.state = UserStateKotlinWay.Unverified
        },
        verifyEmail = { _, _ -> println("You must sign up") },
        viewContent = { println("Content for Anonymous") },

        ),

    Unverified(
        signUp = { _, _ -> println("You are already signed up") },
        verifyEmail = { token, user ->
            println("Verifying with $token")
            user.state = UserStateKotlinWay.Authenticated
        },
        viewContent = { println("Content for Unverified") },
    ),

    Authenticated(
        signUp = { _, _ -> println("You are already signed up and authenticated") },
        verifyEmail = { _, _ -> println("You are already verified") },
        viewContent = { println("Content for Authenticated") },
    ),
}

// Kotlin receiver подход для state pattern 2
private class UserReceiverWay(
    var email: String? = null,
    var state: UserStateReceiverWay = UserStateReceiverWay.Anonymous,
)

private enum class UserStateReceiverWay(

    val signUp: UserReceiverWay.(String) -> Unit,
    val verifyEmail: UserReceiverWay.(String) -> Unit,
    val viewContent: () -> Unit,
) {

    Anonymous(
        // Если параметр для лямбды один, то это it
        signUp = {
            println("Signing up with $it")
            // Мы убрали email, user -> и это позволяет нам избавиться от this.
            email = email
            state = UserStateReceiverWay.Unverified
        },
        // Если параметр для лямбды один, то его можно опустить - это it
        verifyEmail = { println("You must sign up") },
        viewContent = { println("Content for Anonymous") },

        ),

    Unverified(
        signUp = { println("You are already signed up") },
        // Можно писать в одну строку с использованием точки с запятой
        verifyEmail = { println("Verifying with $it"); state = UserStateReceiverWay.Authenticated },
        viewContent = { println("Content for Unverified") },
    ),

    Authenticated(
        signUp = { println("You are already signed up and authenticated") },
        verifyEmail = { println("You are already verified") },
        viewContent = { println("Content for Authenticated") },
    ),
}
