@file:JvmName("Home")

package com.example.kotlindemo

import com.example.kotlindemo.javafiles.MyFirstJavaFile

fun main() {
    var name: String
    name = "Sunny"
    println("Hello ${name.length}")
//    print(getName("Sunny"))
    printStar()
//    var num = 1.rangeTo(10)
//    print(num)
    whenEx()
    println(MyFirstJavaFile.getArea(3, 4))

    // pure Kotlin feature
    var demo = Demo()
    demo.nameParameter(bre = 10, hei = 2, len = 0)

    var a = 12;
    var b = 89

    println(a.greaterValue(b))
    println(a greaterValue b)

    var numArray = arrayOf(3, 4, "sunny")
    for (i in numArray) {
        print(i)
    }

}

fun printStar() {

    name@ for (i in 1..5) {
        for (j in 5 downTo 1) {
            print("*")
            if (i == 3) {
//                break@name
                break
            }
        }
        println()
    }
}

fun getName(name: String): String {
    return name
}

fun whenEx() {
    // use of when as expression

    var x = 10
    var str = when (x) {
        0, 1, 2, 3, 4 -> {
            "x value is 10"
        }
        !in 5..20 -> "x range is 5 to 10"
        else -> {
            "hello"
            "Sunny" // only last statment is assign to str just like if
        }
    }
    print(str)
}

@JvmOverloads
fun add2Num(a: Int, b: Int, h: Int = 10): Int = a + b

class Demo {

    fun nameParameter(len: Int, bre: Int, hei: Int = 10) {
        println("Length : " + len)
        println("Breadth : $bre")
        println("Height : " + hei)
    }

}

/*
* All infix functions are extension functions
* infix fn can take only one parameter
* they have prefix as infix
* */
infix fun Int.greaterValue(other: Int): Int {
    if (this > other)
        return this
    else
        return other

}