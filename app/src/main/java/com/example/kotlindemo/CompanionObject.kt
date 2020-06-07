package com.example.kotlindemo

fun main() {
    Customer.show1()
    D.show()

}
fun display() :String{
    return "Sunny Singh"
}

object Customer : Ab() {
    var name = "Sunny"
    fun show() {
    }

    override fun show1() {
        super.show1()
    }
}

class D {
    companion object {
        var name = ""

        @JvmStatic    // use for java interoperability i.e call below methid in java
        fun show(): String {
            return ""
        }
    }
}

open class Ab {
    private var name1: String = ""
    open fun show1() {
        println("Ab")
    }
}