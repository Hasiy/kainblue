package top.hasiy.kainblue.sort

import java.util.*


fun main() {
    val array = List(10000) {
        Random().nextInt(999999)
    }.apply {
        Random().nextInt(1000)
    }.apply {
        Random().nextInt(99999)
    }

    println("原始数组: $array ")
    val time1 = System.currentTimeMillis()
    println("插入排序: ${array.insertSorted()}")
    val time2 = System.currentTimeMillis()
    println("time1: ${time2 - time1}")
    println("冒泡排序: ${array.bubbleSorted()}")
    val time3 = System.currentTimeMillis()
    println("time2: ${time3 - time2}")
    println("选择排序: ${array.selectSorted()}")
    val time4 = System.currentTimeMillis()
    println("time3: ${time4 - time3}")
    println("希尔排序: ${array.shellSorted()}")
    val time5 = System.currentTimeMillis()
    println("time4: ${time5 - time4}")
    println("快速排序: ${quickSort(array)}")
    val time6 = System.currentTimeMillis()
    println("time5: ${time6 - time5}")

}