package com.example.kotlindemo

fun main() {

    var accessObj = Access()
    accessObj.className
    accessObj.rollNo

    var bObj = B()
    bObj.className
//    bObj.age (Only access in Derived class)
    bObj.rollNo
}

open class Access {
    private var name: Int = 0
    protected var age: Int = 0
    internal var className: String = ""
    var rollNo: Int = 0

}

class B : Access() {
    fun showAge(){
        // only name can not be accessed with in Subclass
        // internal is used with in same module
        print("$rollNo $className $age")
    }
}