package objectClassGenericLearn

fun main(args: Array<String>) {

    // Вариантность, ковариантность и контравариантность
    // Вариантность описывает, как обобщенные типы, типизированные классами из одной иерархии наследования, соотносятся друг с другом.
    // Все Коллекции изначально инвариантны. Для управлением вариантностью придуманы "маски" wildcard

    // Ковариантость. Ограничение по верху
    // Запись wildcard для выполнения ковариантности <? extends T> - ветви от единственного потомка. Можем считывать, но не можем изменять.
    // producers - выдают из контейнера-коллекции, изпользуется для чтения

    // Контрвариантость. Ограничение по низу
    // Запись wildcard для выполнения контрвариантности <? super T> - все потомки скатываются в воронку к единственному классу super. Можем изменять, но не можем прочитать.
    // customers - прячем в контейнер-коллекцию, изпользуется для записи

    // Инвариантность
    // Запись wildcard для выполнения инвариантности <?> что есть короткая запись варианта <? super Object>


    // Инвариантность
    // Инвариантность предполагает, что, если у нас есть классы KotlinRu.A и KotlinRu.B, то класс D<KotlinRu.A> не является ни базовым, ни производным классом для D<KotlinRu.B>.

    var bank1: Bank<AccountX> = Bank()
    var bank2: Bank<DepositX> = Bank()

    // bank1 = bank2   // так нельзя сделать
    // bank2 = bank1   // так нельзя

    /*
    Ковариантость
    Ковариантость предполагает, что, если у нас есть классы KotlinRu.A и KotlinRu.B, то класс D<KotlinRu.A> является производным классом для D<KotlinRu.B>,
    если KotlinRu.A является производным классом для KotlinRu.B.

    В данном случае интерфейс IBank является ковариантным, так как его параметр определен со словом out: interface IBank<out T>.
    Вообще не случайно используется именно слово out. Оно указывает, что параметр представляет тип, который должен быть возвращаемым типом.
    То есть мы можем определить функцию, которая возвращает объект типа T, как в данном случае функция produce:
    fun produce(sum: Int): T
    В то же время тип T не следует использовать в качестве типа входных параметров функции.
    То есть параметр ковариантного типа определяет тип взвращаемых значений.
     */

    var bank1X: IBank<AccountX> = AccountBank()
    var bank2X: IBank<DepositX> = DepositBank()

    var dep : DepositX = bank2X.produce(200)
    var acc : AccountX = bank1X.produce(100)

    bank1X = bank2X   // IBank<Deposit> производный от IBank<Account>
    // bank2X = bank1X   // Так нельзя. Сначало надо закомментировать bank1X = bank2X


    /*
    Контравариантность
    Контравариантость предполагает, что, если у нас есть классы KotlinRu.A, производный от KotlinRu.B,
    то класс D<KotlinRu.A> является супертипом для D<KotlinRu.B>, если D определен как контравариантный.
    То есть имеет место инверсия: с одной стороны, KotlinRu.A производный от KotlinRu.B, но с другой стороны, D<KotlinRu.B> является производным от D<KotlinRu.A>.

    Для определения обобщенного типа как контравариантного параметр обобщения определяется с ключевым словом in
     */
    var bank1Z: BankZ<AccountX> = BankZ()
    var bank2Z: BankZ<DepositX> = BankZ()

    var accZ = DepositX(300)
    bank1Z.consume(acc, 400)
    println(acc.sum)

    // bank1Z = bank2Z    // так нельзя
    bank2Z = bank1Z   // Bank<Account> производный от Bank<Deposit>
}

//Инвариантность
class Bank<T>

open class AccountX(_sum: Int){
    var sum = _sum
}

class DepositX(sum: Int) : AccountX(sum)

// Ковариантость
// параметр ковариантного типа определяет тип взвращаемых значений
interface IBank<out T>{         // слово out указывает, что параметр представляет тип, который должен быть возвращаемым типом.
    fun produce(sum: Int        // В то же время тип T не следует использовать в качестве типа входных параметров функции.
    ): T                        // мы можем определить функцию, которая возвращает объект типа T
}

class DepositBank: IBank<DepositX>{

    override fun produce(sum: Int): DepositX{
        return DepositX(sum)
    }
}

class AccountBank: IBank<AccountX>{

    override fun produce(sum: Int): AccountX {
        return AccountX(sum)
    }
}

// Контравариантность
// Для определения обобщенного типа как контравариантного параметр обобщения определяется с ключевым словом in:
// Слово in указывает, что тип, который будет передаваться через параметр T, должным быть входным,
// то есть представлять тип входных параметров функции.
class BankZ<in T : AccountX>{
    fun consume(acc: T, sum: Int){
        acc.sum += sum
    }
}
