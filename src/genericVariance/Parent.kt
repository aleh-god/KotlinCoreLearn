package genericVariance

open class Parent(val family: String)

class Child(val name: String, family: String) : Parent(family = family)