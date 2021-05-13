package interfaceAbstrData

open class PersonPrimary(val name: String)          // первичный конструктор базового класса

class EmployeePrimary(val company: String, name: String): PersonPrimary(name)
// При наследовании необходимо инициализировать базовый класс.
// Если производный класс имеет первичный конструктор, то вместе с ним должен вызываться конструктор базового класса.