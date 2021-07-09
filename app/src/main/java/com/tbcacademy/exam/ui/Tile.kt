package com.tbcacademy.exam.ui

class Tile(
    private var number:Int? = null
) {
    fun number():Int{
        return number!!
    }
}