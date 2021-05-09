package ObjectClassGenericLearn

fun main(args: Array<String>) {

/*
Обобщения представляют технику, посредством которой методы и классы могут использовать объекты, типы которых на момент
определения классов и методов неизвестны. Обобщения позволяют определять шаблоны, в которые можно подставлять различные типы.

Обобщенные типы (generic types) представляют типы, в которых типы объектов параметризированы.
Например, чтобы уникально идентифицировать каждый объект определенного типа, мы можем определить свойство-идентификатор,
которое будет иметь у каждого объекта уникальное значение. Однако какой тип будет представлять данное свойство?
В зависимости от условий мы можем использовать числовые идентификаторы или строковые или каких-то других типов данных.
 */

    // При использовании типа Person необходимо его типизировать определенным типом,
    // то есть указать, какой тип будет передаваться через параметр T:
    var tom: PersonGeneric<Int> = PersonGeneric<Int>(367, "Tom")            // Здесь id число Int

    // Для типизации объекта после названия типа в угловых скобках указывается конкретный тип:
    var bob: PersonGeneric<String> = PersonGeneric<String>("A65", "Bob")    // Здесь id строка String

    println("Здесь id число Int - ${tom.id} - ${tom.name}")
    println("Здесь id строка String - ${bob.id} - ${bob.name}")

    // Если конструктор использует параметр T, то в принципе мы можем не указывать, каким типом типизируется объект
    // - данный тип будет выводиться из типа параметра конструктора:
    var tomX = PersonGeneric(367, "Tom")
    var bobX = PersonGeneric("A65", "Bob")

    // Можно одновременно использовать несколько параметров:
    var word1: Word<String, String> = Word("one", "один")
    var word2: Word<String, Int> = Word("two", 2)

    println("${word1.source} - ${word1.target}")    // one - один
    println("${word2.source} - ${word2.target}")    // two - 2

    // Функции, как и классы, могут быть обобщенными.

    // При использовании функции мы можем передавать в нее данные любых типов.
        var item1 = getItem<Int>(1,2,3)
        var item2 = getItem<String>("red", "green", "blue")

        println(item1)
        println(item2)

    // Ограничения обобщений
    // Ограничения обобщений (generic constraints) ограничивают набор типов, которые могут передаваться вместо параметра в обобщениях.
    // class NameClass<T : SuperClass>
    // параметр T обязательно должен представлять класс SuperClass или его наследников.
    // Благодаря данному ограничению мы можем использовать внутри класса NameClass все объекты типа T именно как объекты SuperClass и соответственно обращаться к их свойствам и методам.

    var acc1 = Account(2, 300)
    var acc2  = Account(745, 600)
    var t1 = Transaction(acc1, acc2, 200)
    var t2 = Transaction(acc1, acc2, 1200)
    t1.execute()
    t2.execute()

    // Для сложения двух чисел мы можем определить несколько функций - одну для сложения значений Int,
    // вторую - для сложения объектов Double и так далее.
    // А можно определить одну обобщенную функцию, которая будет ограниченна типом Number:
    fun <T: Number> sum(a: T, b: T): Double{
        return a.toDouble() + b.toDouble()
    }

}

// Обобщенные функции

//  Параметр также указывается в угловых скобках перед названием функции.
//  Функция принимает три параметра типа T и возвращает объект этого же типа.
fun <T> getItem(a: T, b: T, c: T): T {
    var n = random.nextInt(3)
    // в зависимости от сгенерированого числа возвращается то или иное значение.
    if (n==1) return a
    else if (n==2) return b
    else return  c
}
// В ней используется стандартный класс Random, который определен в Java и который представляет генератор случайных чисел.
val random = java.util.Random()

// Можно одновременно использовать несколько параметров:
class Word<K, V>(_source: K, _target: V) {
    val source: K = _source
    var target: V = _target
}

// Обобщение ограничено super классом Account. Все потомки и сам класс могут использовать Transaction
class Transaction<T : Account>(_from: T, _to: T, _sum: Int){

    val fromAcc = _from  // с какого счета перевод
    val toAcc = _to      // на какой счет перевод
    val sum = _sum      // сумма перевода
    fun execute(){
        if(fromAcc.sum >= sum){
            fromAcc.sum -= sum
            toAcc.sum += sum
            println("Transaction completed")
        }
        else{
            println("Transaction failed")
        }
    }
}

open class Account(_id: Int, _sum: Int){
    val id = _id
    var sum = _sum
}
class DepositAccount(_id: Int, _sum: Int) : Account(_id, _sum)