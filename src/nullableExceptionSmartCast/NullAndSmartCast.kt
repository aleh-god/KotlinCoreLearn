package nullableExceptionSmartCast

fun main(args: Array<String>){
    var age: Int? = null           // Int? допускает значение null
    age = 23                       // Котлин приводит Int? к соотвествующему типу Int через СмартКаст
    val myAge: Int = age            // Поэтому здесь нет ошибки

    var ageX: Int? = 23             // Int? допускает значение null
    // val myAgeX: Int = ageX          // Здесь выдает ошибку о несоответствии типов Int и Int?
}
