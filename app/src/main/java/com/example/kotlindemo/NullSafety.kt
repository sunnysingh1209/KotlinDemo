package com.example.kotlindemo

fun main() {
    var name: String? = null
    //1. Safe Call (?.)
    // use it when u don't mind of getting null values
    // simply returns name length if value is not null otherwise return null
    print(name?.length)

    // 2. Safet call with let
    // it executes the block only if name is not numm
    name?.let {
        print(name.length)
    }

    // 3. Elvis operator
//    var len = if (name != null){
//        name.length
//    }else{
//        -1
//    }

    var len = name?.length ?: -1
    print(len)

    //4. Non - null assertion operator (!!)
    // if u are sure the value is not null
    // otherwise shows nullpointerException

//    print(name!!.length)

}