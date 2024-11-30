package styleGuide

fun main(args: Array<String>) {

    var bank1: IBank<Account> = AccountBank()
    var bank2: IBank<Deposit> = DepositBank()

    var dep : Deposit = bank2.produce(200)
    var acc : Account = bank1.produce(100)

    bank1 = bank2   // IBank<Deposit> производный от IBank<Account>
    bank2 = bank1   // так нельзя
}
interface IBank<out T>{
    fun produce(sum: Int): T
}

class DepositBank: IBank<Deposit>{

    override fun produce(sum: Int): Deposit{
        return Deposit(sum)
    }
}
class AccountBank: IBank<Account>{

    override fun produce(sum: Int): Account{
        return Account(sum)
    }
}

open class Account(_sum: Int){
    var sum = _sum
}
class Deposit(sum: Int): Account(sum)