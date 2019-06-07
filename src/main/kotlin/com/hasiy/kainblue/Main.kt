package com.hasiy.kainblue

import com.alibaba.fastjson.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.IntStream
import java.util.Arrays



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
    System.out.println("sum=${sum::class}")

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
    println("date:${date::class}")

    // 1 .. 10
    val num = 1.rangeTo(10)
    println("num$num")
    println("num${num::class}")

    val num1 =1..10
    println("num1$num1")
    // 遍历 1 到 10, 包括 1 和 10.
    // num.contains(index) --> java
    // index in num
    for (index in num) {
        println("index:$index")
    }

    val calendar = Calendar.getInstance() // 代表今天.
    println("calendar$calendar")
    calendar.add(Calendar.DATE, 10)       // 代表 10 天后.
    println("calendar$calendar")

    //一元前缀操作符
    data class Point(val x: Int, val y: Int)

    operator fun Point.unaryMinus() = Point(-x, -y)
    val point = Point(10, 20)
    println(point)
    println(-point)

    var m = 10
    m++
    println("m:$m")
    m--
    println("m:$m")
    println("m++:${m++}")
    println("m:$m")
    println("m--:${m--}")
    println("m:$m")
    println("++m:${++m}")
    println("m:$m")
    println("--m:${--m}")
    println("m:$m")
//    编译器自动支持与普通数字类型的前缀、后缀自增运算符相同的语义。例如后缀运算会先返回变量的值，然后才执行 ++ 操作。

//    这在 Any 中被定义。Java 的 a.equals(b) 相当于 Koltin 的 a == b，Java 的 a == b 相当于 Kotlin 的 a === b（同一性检查）。
//    要自定义 == 操作符其实就是覆写 equals 方法。Kotlin 中 === 不可被重载。
    val a = 10
    val b = 20
    a.inc()
    b.unaryPlus() //原数
    b.unaryMinus()// 负的原数
    a.inv()  //~a
    a.and(b) //a&b
    a.or(b)  //a|b
    a.xor(b) // a^b
    a.shl(b) //a<<b
    a.shr(b) //a>>b
    a.ushr(b) //a>>>b

    //用于检查对象是否是某个类的实例。
    //instanceof--> java
    // is --> kotlin

    val intArray :IntArray =intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    for (i in intArray) print("intArray:$i   ")
    println("intArray:${intArray::class.java}")

    // [1,1,1]
    val asc1:Array<Int> = Array(3) { 1}
    for (z in asc1) print("asc1:$z   ")
    println()

    val intList : Array<Int> = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    for (j in intList) print("intList:$j   ")
    println("intList:${intList::class.java}")

    // Creates an Array<String> with values ["0", "1", "4", "9", "16"]
    val asc = Array(5) { i -> (i * i).toString() }
    asc.forEach { println(it) }

    //int[3][4]
    val ab = Array(3) { IntArray(4) }

}


