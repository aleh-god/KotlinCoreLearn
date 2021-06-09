package nullableExceptionSmartCast

fun main(args: Array<String>){
    // Ключевое слово null представляет специальный литерал, который указывает, что переменная не имеет как такового значения.
    // То есть у нее по сути отсутствует значение.
    val n = null
    println(n)  // null

    // Мы можем присвоить значение null только переменной, которая представляет тип Nullable. Чтобы превратить обычный тип в тип nullable, достаточно поставить после названия типа вопросительный знак:
    // val n : Int = null  //! ошибка, Int не допускает значение null
    var age : Int? = null // норм, Int? допускает значение null
    age = 34              // Int? допускает null и числа
    var nameX : String? = null
    nameX = "Tom"        // String? допускает null и строки

    // Nullable типы имеют ряд ограничений:
    //Значения nullable-типов нельзя присвоить напрямую переменным, которые не допускают значения null
    //У объектов nullable-типов нельзя вызвать напрямую те же методы, которые есть у обычных типов
    //Нельзя передавать значения nullable-типов в качестве аргумента в функцию, где требуется конкретное значение, которое не может представлять null

    // Оператор ?: который позволяет предоставить альтернативное значение, если присваиваемое значение равно null:

    var name : String?  = "Tom"
    // val firstName: String = name    // Ошибка! Для компилятора неизвестно, каким значением инициализирована переменная name.

    age = 23                        // Int? допускает значение null
    val myAge: Int = age ?: 0       // если age равно null, то присваивается число 0

    val firstName: String = name ?: "Undefined" // если name = null, то присваивается "Undefined"

    // То есть это все равно, если бы мы написали:
    var nameAlter : String?  = "Tom"
    val firstNameAlter: String

    if (nameAlter!=null) {
        firstNameAlter = nameAlter
    } else firstNameAlter = "Undefined"

    // ООператор ?. позволяет объединить проверку значения объекта на null и выполнение функции этого объекта.

    val length: Int? = name?.length

    // Если переменная name вдруг равна null, то переменная length получит значение null.
    // Если переменная name содержит строку, то возвращается длина этой строки.
    // По сути выражение val length: Int? = name?.length эквивалентно следующему коду:
    val lengthAlter: Int?
    if(nameAlter != null)
        lengthAlter = nameAlter.length
    else
        lengthAlter = null

    // Также в данном случае мы могли совместить оба выше рассмотренных оператора:
    var nameY : String?  = "Tom"
    val lengthY: Int = nameY?.length ?:0

    // Оператор !! (not-null assertion operator) принимает один операнд. Если операнд равен null, то генерируется
    // исключение kotlin.KotlinNullPointerException. Если операнд не равен null, то возвращается его значение.
    var nameZ : String?  = "Tom"
    val id: String = nameZ!!
    println("$id and $nameZ")

    // Поскольку данный оператор возвращает объект, который не представляет nullable-тип,
    // то после применения оператора мы можем обратиться к методам и свойствам этого объекта:
    val nameS : String?  = null
    val lengthS :Int = nameS!!.length

}