package InterfaceAbstrData

interface Info{

    val model: String
        get() = "Undefined"         // Первое свойство имеет геттер, а это значит, что оно имеет реализацию по умолчанию.
                                    // При применеии интерфейса такое свойство необязательно реализовать.

    val number: String              // Второе свойство - number является абстрактным, оно не имеет ни геттера, ни сеттера,
                                    // то есть не имеет реализации по умолчанию, поэтому классы его обязаны реализовать.
}