package com.example.kotlindemo

fun main() {

    var dogObj = Dog()
    dogObj.name = "Dog"
    dogObj.eat()

    var cat1 = Cat("Sunny", 11)
    var cat2 = Cat("sunny", 11)

    if (cat1.name == cat2.name) {
        println("Equal")
    } else {
        println("Not equal")
    }
    var copy = cat1.copy()
//    var copy = cat1.copy(name = "Singh")
    println(copy.toString())
}

open class Animal {
    var name: String = ""
    open fun eat() {
        println(" Eat")
    }
}

class Dog : Animal() {

    override fun eat() {
        super.eat()
        println("Dog Eat")
    }
}


data class Cat(var name: String) {
    var id: Int = 0

    constructor(name: String, id: Int) : this(name) {
        this.id = id
    }

    override fun toString(): String {
//        return super.toString()
        return name + id
    }
}