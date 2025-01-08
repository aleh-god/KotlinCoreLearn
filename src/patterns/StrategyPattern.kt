package patterns

private interface FormField {

    val name: String
    val value: String
    fun isValid(): Boolean
}

// Добавление новой логики ведет к появлению дополнительных или дочерних классов
private class UserNameFieldClassicWay(override val value: String) : FormField {

    override val name: String = "username"
    override fun isValid(): Boolean = value.isNotEmpty()
}

// Классический принцип - Инкапсулируйте то, что меняется
// Dave принцип - Извлекайте то, что отличается
private class PasswordFieldClassicWay(override val value: String) : FormField {

    override val name: String = "password"
    override fun isValid(): Boolean = value.length >= 8
}

// Реализация Strategy Pattern
// Количество сущностей увеличилось, но зато теперь легко расширять типы валидации, тестировать и работает принцип S

// В pattern это Strategy
private interface Validator {

    fun isValid(value: String): Boolean
}

// В pattern это Concrete Strategy
private class UserNameValidator() : Validator {

    override fun isValid(value: String): Boolean = value.isNotEmpty()
}

// Валидаторы имеют только логику и не имеют состояний - их легко заменить функциями
private class PasswordValidator() : Validator {

    override fun isValid(value: String): Boolean = value.length >= 8
}

// В pattern это Context
private class StrategyFormField(
    val name: String,
    val value: String,
    private val validator: Validator,
) {

    fun isValid(): Boolean = validator.isValid(value)
}

// Реализация Strategy Pattern through Kotlin way
// fun interface можно заменить на функциональный тип (String) -> Boolean и написать typealias
private fun interface ValidatorKotlinWay {

    fun isValid(value: String): Boolean
}

private val UserNameValidatorKotlinWay = ValidatorKotlinWay { it.isNotEmpty() }
private val PasswordValidatorKotlinWay = ValidatorKotlinWay { it.length >= 8 }
private fun ValidatorKotlinWay.optional() = ValidatorKotlinWay {
    it.isEmpty() || this.isValid(it)
}

private class StrategyFormFieldValidatorKotlinWay(
    val name: String,
    val value: String,
    private val validator: ValidatorKotlinWay,
) {

    fun isValid(): Boolean = validator.isValid(value)
}

private fun main() {
    val nameField = StrategyFormFieldValidatorKotlinWay(
        name = "user_name",
        value = "Jesus",
        validator = UserNameValidatorKotlinWay.optional(),
    )
    println("Validation: ${nameField.name} for ${nameField.value} is ${nameField.isValid()}")
}
