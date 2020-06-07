package com.example.kotlindemo

fun main() {
    var name = "Sunny"
    println(name.length)
    var classAObj = A("Sunny")
//    classAObj.name = "Sunny"
    classAObj.className = "MCA"
    classAObj.showName()
    Student.abc
}

abstract class Student {
    abstract var name: String
    abstract var className: String
    var abcd = ""
    open fun abc(str: String) {

    }

    companion object {
        var abc = ""

    }

    constructor() {
        println("Hello")
    }
}

class A : Student {

    override var name: String

    override var className: String = "" // use of getter and setter is optional
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var str: String = ""

    init {
        str = "Sukudfg"
        print("$str++++")
    }

    constructor(name: String) : super() {
        this.name = name
    }

    fun showName() {
        println("Name $name and className " + className)
    }

    override fun abc(str: String) {
        super.abc(str)
    }

}
