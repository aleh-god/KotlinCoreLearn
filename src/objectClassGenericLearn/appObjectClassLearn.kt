package objectClassGenericLearn

fun main(args: Array<String>){ // Функция KotlinRu.collections.main в Kotlin не помещается в отдельных класс, а всегда определяется вне какого-либо класса.

    // Представлением объекта является класс.
    // Класс фактически представляет определение объекта.
    // А объект является конкретным воплощением класса.
    var bob: Person

    // Для создания объекта класса необходимо вызвать конструктор данного класса.
    // Конструктор фактически представляет функцию, которая называется по имени класса и которая выполняет инициализацию объекта.
    // По умолчанию для класса компилятор генерирует пустой конструктор, который мы можем использовать:
    val alice: Person = Person("alice")

    // В Kotlin классы могут содержать ряд компонентов:
    //конструкторы и инициализаторы
    //функции
    //свойства
    //вложенные классы
    //объявления объектов

    // Свойства

    val tom = Person("Tom")  // создаем объект
    println("tom.name = ${tom.name}")       // Tom
    println("tom.age = ${tom.age}")        // 30

    bob = Person("Bob", 25)  // создаем объект

    println("bob.name = ${bob.name}")       // Bob
    println("bob.age = ${bob.age}")        // 25

    // Установка значения свойства
    println("Установка значения свойства bob.age")
    bob.age = 40

    // получение значения свойства:
    val personName : String = bob.name
    println("получение значения bob.name = $personName")       // Bob

    println("!!!Test Getter and Setter!!!")
    var test : GetSetLearn = GetSetLearn()

    println("test.name = ${test.name}")
    println(test.info)
    println(test.whoIsSuper)

    test.howOld()

    println("mutable.age = ${test.ageVar}")
    println("mutable.name =${test.nameVar}")

    test.ageVar = 30
    test.nameVar = "djon"

    println("mutable.NewAge = ${test.ageVar}")
    println("mutable.NewName =${test.nameVar}")

    println("вызываем сеттер mutable.NewAge = -8")
    test.ageVar = -8                                    // вызываем сеттер
    println("mutable.NewAge = ${test.ageVar}")          // Значение свойства age не изменилось. Так как новое значение -8 не входит в диапазон от 0 до 110.

    // конструкторы и инициализаторы

    // Для создания объекта tom применяется первичный конструктор, который принимает один параметр.
    val xom: Person = Person("xom")

    // Для создания объекта bob применяется вторичный конструктор с двумя параметрами.
    val xob: Person = Person("xob", 45)

    println("Name: ${xom.name}  Age: ${xom.age}")
    println("Name: ${xob.name}  Age: ${xob.age}")

    // Функции в классах. member functions или функции-члены класса

    // Для обращения к функциям класса необходимо использовать имя объекта, после которого идет название функции и в скобках значения для параметров этой функции:
    bob.sayHello()          // Hello
    bob.go("the shop")      // Bob goes to the shop
    bob.go("the cinema")    // Bob goes to the cinema

    println(bob.getInfo())  // Name: Bob  Age: 23

    println("!!!Access Modifier!!!")
    var am : AccessModifierLearn = AccessModifierLearn("Jesus", 33)
    var lesson : ObjectClassLearn = AccessModifierLearn("Jesus", 33)

    var x: Int = am.age
    var teXt: String = am.name
    println("Read public age, private setter = $x")
    println("am.age = 40 Error: the setter is private")

    am.showResult()
    x = am.age
    println("Change public age via private setter in public functional = $x")

    am.display()

    println("!!!Nested and inner classes!!!")
    // Для обращения к вложенному классу надо использовать имя внешнего класса.
    val nested = NestedClassLearn.NestedClass()
    nested.changeId()
    nested.call()

    NestedClassLearn.NestedClass().changeId()
    NestedClassLearn.NestedClass().call()

    // Если мы хотим использовать объект вложенного класса, то необходимо создать объект внешнего класса:
    val inner = NestedClassLearn().InnerClass()
    inner.changeId()
    inner.call()

    inner.changeId()
    inner.call()

    inner.changeId()
    inner.call()

    NestedClassLearn().InnerClass().changeId()
    NestedClassLearn().InnerClass().call()

}