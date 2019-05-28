package com.hasiy.kainblue

import com.alibaba.fastjson.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.IntStream

fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
    return { X -> f(g(X)) }
}

fun isOdd(x: Int) = x % 2 != 0
fun length(s: String) = s.length
fun main() {

    val oddLength = compose(::isOdd, ::length)
    val strings = listOf("a", "ab", "abc")
    val result = strings.filter(oddLength)
    println("result:$result")

    val sum = IntStream.range(0, 1000).parallel().map { n -> n * n }.sum()
    System.out.println("sum=$sum")

    val nonNulls: List<String> = listOfNotNull(null, "a", "b", "c")
    (nonNulls as ArrayList).addAll(arrayOf("x", "y"))
    nonNulls.remove("x")
    println("nonNulls:$nonNulls")

    val map = mapOf("a" to 1, "b" to 2, "c" to 3)
    println("map:$map")

    val linkedHashMap: LinkedHashMap<String, String> =
        linkedMapOf("red" to "#FF0000", "azure" to "#F0FFFF", "white" to "#FFFFFF")
    println("linkedHashMap:$linkedHashMap")

    val sortedMap: SortedMap<Int, String> = sortedMapOf(4 to "d", 1 to "a", 3 to "c", 2 to "b")
    println("sortedMap::$sortedMap")

    val jsonObject = JSONObject()
    jsonObject["name"] = "name"
    jsonObject["phone"] = "phone"
    jsonObject["address"] = "address"
    val composingInfo = jsonObject.toString()
    val composingInfoJson = JSONObject.parseObject(composingInfo)
    val name = composingInfoJson["name"].toString()
    println("name:$name")

    println("Date():${Date()}")
    val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
    println("date:$date")


}
