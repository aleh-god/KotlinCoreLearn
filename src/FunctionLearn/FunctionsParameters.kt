package FunctionLearn

class FunctionsParameters: FunctionLearn {
    /*
     * fun имяФункции (параметры) : ВозвращаемыйТип {выполняемые инструкции}
     */
    override fun showResult() {

    }

    fun factorialVoid(n: Int) {
        var result = 1
        for (d in 1..n) {
            result *= d
        }
        println("Factorial of $n is equal to $result")
    }

    // Аргументы по умолчанию
    // В данном случае функция displayUser имеет три параметра для передачи имени, возраста и должности.
    // Для первого параметр name значение по умолчанию не установлено, поэтому для него значение по-прежнему обязательно передавать значение.
    // Два последующих - age и position являются необязательными, и для них установлено значение по умолчанию.
    // Если для этих параметров не передаются значения, тогда параметры используют значения по умолчанию.
    fun displayUser(name: String, age: Int = 18, position: String="unemployed"){
        println("Name: $name   Age: $age  Position: $position")
    }

    // Переменное количество параметров. Vararg
    // Функция может принимать переменное количество параметров одного типа

    // При вызове функции мы можем ей передать любое количество строк
    fun printStrings(vararg strings: String){
        for(str in strings)
            println(str)
    }

    fun sum(vararg numbers: Int){
        var result=0
        for(n in numbers)
            result += n
        println("Сумма чисел равна $result")
    }

    // Если функция принимает несколько параметров, то обычно vararg-параметр является последним.
    fun printUserGroup(count:Int, vararg users: String){
        println("Count: $count")
        for(user in users)
            println(user)
    }
    // Если после vararg-параметра идут еще какие-нибудь параметры, то при вызове функции значения этим параметрам передаются через именованные аргументы:
    fun printUserGroup2(group: String, vararg users: String, count:Int){
        println("Group: $group")
        println("Count: $count")
        for(user in users)
            println(user)
    }

    // Возвращение результата. Оператор return

    fun factorial(n: Int) : Int{ // функция возвращает значение типа Int

        var result = 1;
        for(d in 1..n){
            result *= d
        }
        return result       //  возвращение значения
    }

    // Если функция не возвращает какого-либо результата, то фактически неявно она возвращает значение типа Unit. Этот тип аналогичен типу void
    fun hello() : Unit{
        println("Hello")
    }
    // Формально мы даже можем присвоить результат такой функции переменной:
    // val d = hello()

    // Если функция возвращает значение Unit, мы также можем использовать оператор return для возврата из функции:
    fun factorialChek(n: Int){

        if(n < 1){
            println("Incorrect input parameter")
            return // с помощью оператора return осуществляется выход из функции, и последующие инструкции не выполняются.
        }
        var result = 1;
        for(d in 1..n){
            result *= d
        }
        println("Factorial of $n is equal $result")
    }

    // Unit имеет смысл. Напр с помощью этого типа можно делать коллбэки
    fun getData(data: String,
                onDataGet: (String) -> Unit) { // переменная onDataGet, имеет функциональный тип. так описываются переменные содержащие ссылку на функцию.
        onDataGet(data) // Вызов функции, через переменную. к переменной можно обращаться как к функции за счет перегрузки операторов.
    }
    // Вызов метода в приложении: fun getData("some data") { str -> print(str) }

    // Этот код можно было написать так:
    // getData("some data", {str -> print(str)})
    // Или с помощью анонимной функции:
    // getData("some data", fun (str: String) : Unit { print(str)})
}