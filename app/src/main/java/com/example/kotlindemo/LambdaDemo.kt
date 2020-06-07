package com.example.kotlindemo

fun main() {
    var highLevel = HighLevel()
    highLevel.addTwoNum(3, 5, object : MyFirstInter {
        override fun sum(sum: Int) {
            println(sum)
        }
    })

//    var myLambda: (Int, Int) -> Unit = { s: Int, b: Int -> println(s + b) }
    var sum = 0
    val value = highLevel.addTwoNum(5, 7, { s, b -> sum = s + b })
    println("$value $sum")
    println(reverseNum(178))
}

fun reverseNum(num: Int): Int {
    var rev = 0
    var num1 = num
    while (num1 != 0) {
        val digit = num1 % 10
        rev = rev * 10 + digit
        num1 /= 10
    }

    return rev
}

class HighLevel {
    fun addTwoNum(a: Int, b: Int, action: MyFirstInter) {
        action.sum(a + b)
    }

    fun addTwoNum(a: Int, b: Int, action: (Int, Int) -> Unit): Int {
        action(a, b)
        return a + b
    }
}

interface MyFirstInter {
    fun sum(sum: Int)
}

