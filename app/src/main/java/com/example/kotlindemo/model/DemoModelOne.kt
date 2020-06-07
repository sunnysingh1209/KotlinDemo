package com.example.kotlindemo.model

import java.util.*

class DemoModelOne : Observable() {

    var name: String = ""
        set(value) {
            field = value
            setChangedAndNotify("name")
        }

    var email: String = ""
        set(value) {
            field = value
            setChangedAndNotify("email")
        }

    private fun setChangedAndNotify(field: Any) {
        setChanged()
        notifyObservers(field)
    }
}