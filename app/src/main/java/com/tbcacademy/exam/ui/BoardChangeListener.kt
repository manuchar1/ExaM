package com.tbcacademy.exam.ui

interface BoardChangeListener {
    fun tileSlid(from: Place?,to: Place?,numOfMoves: Int)
    fun solved(numOfMoves: Int)

}