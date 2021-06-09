package objectClassGenericLearn
/*
В Kotlin есть следующие модификаторы видимости:

* private: классы, объекты, интерфейсы, а также функции и свойства, определенные вне класса, с этим модификатором видны только в том файле, в котором они определены. Члены класса с этим модификатором видны только в рамках своего класса
* protected: члены класса с этим модификатором видны в классе, в котором они определены, и в классах-наследниках
* internal: классы, объекты, интерфейсы, функции, свойства, конструкторы с этим модификатором видны в любой части модуля, в котором они определены. Модуль представляет набор файлов Kotlin, скомпилированных вместе в одну структурную единицу. Это может быть модуль IntelliJ IDEA или проект Maven
* public: классы, функции, свойства, объекты, интерфейсы с этим модификатором видны в любой части программы. (При этом если функции или классы с этим модификатором определены в другом пакете их все равно нужно импортировать)

Если модификатор видимости явным образом не указан, то применяется модификатор public
Для установки уровня видимости модификатор ставится перед ключевыми словами class/var/val/fun/set в самом начале определения класса/свойства/переменной/функции/сеттера.
 */

class AccessModifierLearn(_name: String,
                          _age: Int,
                          private val secret: String = "Null"                 // Если свойства устанавливаются через конструктор, то в конструкторе у свойств также можно указать модификатор видимости:
                          ) : ObjectClassLearn {

    var name: String = _name        // свойство публичное и видно везде

    var age: Int = _age             // свойство публичное и видно везде
        public get() = field        // Избыточный код, getter автоматически публичный, как и свойство
        private set                 // сеттер доступен только внутри этого класса
                                    // am.age = 40 Error: the setter is private

    private val x: Int = 100          // свойство закрытое и невидимо для других классов, но можно использовать внутри класса
    //    public get() = 100          // Не имеет смысла, в val переменная и get() связаны

    private var id: Int = 1         // свойство закрытое и невидимо для других классов, но можно использовать внутри класса
        get() = field + 2
        set(value){
            if((value>0) and (value <110))
                field = value
        }

    fun display(){   // функция видна всем
        println("Id: $id Name: $name Secret: $secret")
    }

    internal fun displayX(){   // функция видна в текущем модуле
        println("Id: $id Name: $name")
    }

    override fun showResult() {
        this.age = x               // Используем приватный сеттер и приватное свойство для изменения открытой для чтения переменной
    }
}