package Family
open class Father (val name: String = "FatherName")
class Son(name: String = "SunName"): Father(), Mother {
    override fun love() = "Some love message" }