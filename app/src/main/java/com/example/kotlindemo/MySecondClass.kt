package com.example.kotlindemo

fun main() {
    var myList = mutableListOf<Int>(23, 4, 4, 5, 6, 7, 7)
    myList.add(22)

    for (i in myList.indices) {
        println("$i and ${myList.get(i)}")
    }

    var newList = myList.filter { Int -> Int < 10 }.map { it * it }
    println(newList)
    var personList: List<Person> =
        listOf<Person>(Person(11, "Sunny"), Person(29, "Arun"), Person(90, "Karan"))
    var filter =
        personList.filter { person -> person.name.startsWith("S") || person.name.endsWith("n") }
            .map { it.name }
    print(filter)

}

class Person(var id: Int, var name: String) {

}