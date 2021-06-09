package objectClassGenericLearn

// Для определения пакета применяется ключевое слово package, после которого идет имя пакета:
//package account

// Встроенные пакеты

// Kotlin имеет ряд встроенных пакетов, которые подключаюся по умолчанию в любой файл на языке Kotlin:
//kotlin.*
//kotlin.annotation.*
//kotlin.collections.*
//kotlin.comparisons.*
//kotlin.io.*
//kotlin.ranges.*
//kotlin.sequences.*
//kotlin.text.*

class Person constructor(_name: String = "Jesus"){            // Конструктор имеет параметр _name, который представляет тип String
                                                              // Конструктор может иметь значение по умолчанию = "Jesus" для входящих параметров

    // Представлением объекта является класс.
    // Класс фактически представляет определение объекта.
    // А объект является конкретным воплощением класса.

    // Свойства
    var age: Int = 30
    val name : String   // Инициализация переменной в блоке init
    val skills: MutableList<String> = mutableListOf()
    private var id: Int = 0

    // Конструкторы

    // Для определения конструкторов применяется ключевое слово constructor.
    // Классы в Kotlin могут иметь один первичный конструктор (primary constructor) и один или несколько вторичных, перегруженных конструкторов (secondary constructor).

    // Первичный конструктор является частью заголовка класса и определяется сразу после имени класса:
    // class Person constructor(_name: String)
    // (_name: String) - это входящие параметры, с ними нужно что-то сделать
    // Если первичный конструктор не имеет никаких аннотаций или модификаторов доступа, как в данном случае, то ключевое слово constructor можно опустить:
    // class Person(val name: String, val age: Int) - Первичный конструктор сразу с описанием полей-свойств
    // (val name: String, val age: Int) - это уже описание полей-свойств класса

    // При этом первичный конструктор в отличие от функций не определяет никаких действий, он только может принимать данные извне через параметры.
    // Через параметры конструктора мы можем передать извне данные и использовать их для инициализации объекта.

    // Инициализатор для первичного конструктора
    init{
        if (_name.length > 1) name = _name        // Мы можем использовать данные полученные из первичного конструктора для инициализации свойств класса.
        else {
            name = "Jesus"
            println("Имя является символом, Христос Воскрес")
        }
        ++id                                      // Выполняем дополнительные инструкции
    }

    // Вторичные конструкторы определяются в теле класса. Это "перегруженные" варианты первичного конструктора
    // Если для класса определен первичный конструктор, то вторичный конструктор должен вызывать первичный с помощью ключевого слова this:
    constructor(_name: String, _age: Int) : this(_name){        // Через этот вызов необходимо передать значения для параметров первичного конструктора
        age = _age                                              // В самом вторичном конструкторе устанавливается значение свойства age.
    }

    // Еще один вторичный (перегруженный) конструктор
    constructor(_name: String, _age: Int, _skills: String) : this(_name){
        age = _age
        skills.add(_skills)
    }

    // Коструктор по умолчанию
    constructor() : this("Jesus")

    // member functions или функции-члены класса

    fun sayHello() = println("Hello") // Краткая форма. функция в одно действие, нет параметров, возвращает Unit

    fun go(location: String){
        println("$name goes to $location")
    }

    fun getInfo() : String{
        return "Name: $name  Age: $age"
    }

}