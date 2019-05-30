package com.hasiy.kainblue

import com.alibaba.fastjson.JSONObject
import com.sun.xml.internal.fastinfoset.util.StringArray
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
    System.out.println("sum=${sum::class.java}")

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
    println("date:${date::class.java}")

    // 1 .. 10
    val num = 1.rangeTo(10)
    println("num$num")
    println("num${num::class.java}")

    val num1 = 1..10
    println("num1$num1")
    // 遍历 1 到 10, 包括 1 和 10.
    // num.contains(index) --> java
    // index in num
    for (index in num) {
        print("index:$index  ")
    }
    println()


    for (x in 0..66 step 2) {
        print("$x  ")
    }
    println()
    for (x in 66 downTo 0 step 3) {
        print("$x  ")
    }
    println()


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

    val intArray: IntArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    for (i in intArray) print("intArray:$i   ")
    println("intArray:${intArray::class.java}")

    val charArray: CharArray = charArrayOf('1', '2', '3')
    for (i in charArray) print("charArray:$i   ")
    println("charArray:${charArray::class.java}")

    // [1,1,1]
    val asc1: Array<Int> = Array(3) { 1 }
    for (z in asc1) print("asc1:$z   ")
    println("asc1:${asc1::class.java}")

    val arrayInt: Array<Int> = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    for (j in arrayInt) print("intList:$j   ")
    println("intList:${arrayInt::class.java}")

    //int[3][4]
    val ab: Array<IntArray> = Array(3) { IntArray(4) }
    println("ab:${ab::class.java}")



    fun judgmentType(x: Any) {
        when (x) {
            is Int -> println(x + 1)
            is String -> println(x.length + 1)
            is IntArray -> println(x.sum())
        }
        println("x${x::class.java}")
    }

    judgmentType(10)
    judgmentType("10")
    judgmentType(intArray)

    val str = "10"
    println("str${str.toInt()}")
    println("str${str.toInt(2)}")  // todo radix 是啥意思
    println("str${str.toIntOrNull()}")
    println("str${str.toIntOrNull(16)}")

    fun getStringLength(obj: Any) {
        if (obj is String) {
            println("obj.length:${obj::class.java}")
            println("obj.length:${obj.length}")
        }
        println("obj.length:${obj::class.java}")
    }

    getStringLength(1)
    getStringLength("we")

    val array: Array<Int> = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
//    for 循环可以对任何提供迭代器（iterator）的对象进行遍历
    for (i in array.indices) {
        print("array[$i]${array[i]}  ")
    }
    println()

    var x = 10
    while (x > 0) {
        --x
        print("x$x  ")
    }
    println()

    var y = 0
    do {
        ++y
        print("y$y  ")
    } while (y < 10) // y的作用域包含此处
    println()

    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        print("$item   ")
    }
    println()


    val item = setOf("apple", "banana", "kiwifruit")
    when {
        "orange" in item -> println("juicy")
        "apple" in item -> println("apple is fine too")
    }

    // 使用 lambda 表达式来过滤（filter）与映射（map）集合：
    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits
        .filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach { println(it) }


}


