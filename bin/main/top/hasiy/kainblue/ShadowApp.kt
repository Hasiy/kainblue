package com.leqi.institute.view.activity

object ShadowApp {
    internal var x: Int = 0
    @JvmStatic
    fun main(args: Array<String>) {
        x = 5
        println("x = $x")
        val x: Int
        x = 10
        println("x = $x")
        println("ShadowApp.x = " + ShadowApp.x)
    }
}