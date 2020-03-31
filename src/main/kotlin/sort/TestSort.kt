package sort

import java.util.*


fun main() {
    val array = List<Int>(10000) {
        java.util.Random().nextInt(1000)
    }
    println("原始数组: $array ")
    val time1 = System.currentTimeMillis()
    println("插入排序: ${array.insertSorted()}")
    val time2 = System.currentTimeMillis()
    println("time: ${time2 - time1}")
    println("冒泡排序: ${array.bubbleSorted()}")
    val time3 = System.currentTimeMillis()
    println("time: ${time3 - time2}")
    println("选择排序: ${array.selectSorted()}")
    val time4 = System.currentTimeMillis()
    println("time: ${time4 - time3}")
    println("希尔排序: ${array.shellSorted()}")
    val time5 = System.currentTimeMillis()
    println("time: ${time5 - time4}")
}


@Throws(Exception::class)
fun testSortAscendingDescending() {
    val countryList: ArrayList<String> = ArrayList<String>()
    countryList.add("France")
    countryList.add("USA")
    countryList.add("India")
    countryList.add("Spain")
    countryList.add("England")


    
}