package objectClassGenericLearn

class PersonShortSyntax(private var name: String = "Jesus", var age: Int = 33)
{
    fun whoIsDaddy() = println("Who is daddy? $name a $age")
}