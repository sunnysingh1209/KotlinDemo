package com.example.kotlindemo

fun main() {
    println(MyFirstInterface.nam)
    var button = Button()
    button.eat()
    button.display()
}

interface MyFirstInterface {
    var name: String// abstract by default OR CAN'T INITIALIZE

    fun eat()
    private fun show() {
        println("MyFirst Show")
    }

    fun display() {
        show()
    }

    companion object { // use companion object as static
        var nam: String = "Static"
    }
}

interface MySecondInterface {

    fun eat() {
        println("MySecond Eat")
    }

    fun display() {
        println("Second display")
    }
}

class Button : MyFirstInterface, MySecondInterface {

    override fun eat() {
        super.eat()
        println("Button Eat")
    }

    override var name: String = ""

    override fun display() {
        super<MySecondInterface>.display()
        super<MyFirstInterface>.display()
    }
}
