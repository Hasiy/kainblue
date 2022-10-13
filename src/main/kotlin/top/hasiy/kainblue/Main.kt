package top.hasiy.kainblue

import com.alibaba.fastjson.JSONObject
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.Collectors


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

//    val sum = range(0, 1000).parallel().map { n -> n * n }.sum()
//    println("sum=$sum")
//    println("sum=${sum::class.java}")

    val nonNulls: List<String> = listOfNotNull(null, "a", "b", "c")
    (nonNulls as ArrayList).addAll(arrayOf("x", "y"))
    nonNulls.remove("x")
    println("nonNulls:$nonNulls")

    val map = mapOf("a" to 1, "b" to 2, "c" to 3)
    println("map:$map")

    // 有序的hashMap
    val linkedHashMap: LinkedHashMap<String, String> =
            linkedMapOf("red" to "#FF0000", "azure" to "#F0FFFF", "white" to "#FFFFFF")
    println("linkedHashMap:$linkedHashMap")

    //由Map 扩展的有序Map  (子 NavigableMap  TreeMap
    val sortedMap: SortedMap<Int, String> = sortedMapOf(4 to "d", 1 to "a", 3 to "c", 2 to "b")
    println("sortedMap:$sortedMap")

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

    // 1 .. 10  包括 1 和 10
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
    // 协程
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

    // Creates an Array<String> with values ["0", "1", "4", "9", "16"]
    val asc = Array(5) { i -> (i * i).toString() }
    asc.forEach { println(it) }

    //int[3][4]
    val ab: Array<IntArray> = Array(3) { IntArray(4) }
    println("ab:${ab::class.java}")

    val arr1 = arrayOf("1", 2, 3, 4)
    arr1.reverse() //反转元素
    arr1.forEach {
        println("反转元素 it:$it")
    }


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
            println("obj.top.hasiy.kainblue.length:${obj::class.java}")
            println("obj.top.hasiy.kainblue.length:${obj.length}")
        }
        println("obj.top.hasiy.kainblue.length:${obj::class.java}")
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

    var a1 = 1
    val s1 = "a is $a1"
    a1 = 2
    //  is 替换成 was
    val s2 = "${s1.replace("is", "was")}, but now is $a1"
    println(s2)

    //过滤 list
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val positives1 = list.filter { z -> z > 0 }
    println("positives1:$positives1")
    val positives2 = list.filter { it > 6 }
    println("positives2:$positives2")

    //遍历 map/pair型list
    for ((k, v) in map) {
        println("$k -> $v")
    }

    println("单例：${Resource.name}")

    //交换两个变量
    var z = 1
    var v = 2
    z = v.also { v = z }
    println(z)
    println(v)

    val object1 = SingleObject.instance
    object1.showMessage()

//    val object2 = SingleObjectJava.getInstance()
//    object2.showMessage()

    val persons = arrayListOf<Person>()
    val ids = ArrayList<Int>()//用来临时存储person的id

    persons.add(Person(1, "name1", 10))
    persons.add(Person(2, "name1", 11))
    persons.add(Person(3, "name1", 14))
    persons.add(Person(3, "name1", 12))
    persons.add(Person(4, "name1", 14))
    persons.add(Person(5, "name1", 13))

    // stream 流数据去重
    val personList = persons.stream().filter {
        val flag = !ids.contains(it.id)
        ids.add(it.id)
        flag
    }.collect(Collectors.toList<Person>())
    println(personList)


    // 这里举例一个语言自带的一个高阶函数filter,此函数的作用是过滤掉不满足条件的值。
    val arr = arrayOf(1, 3, 5, 7, 9)
    // 过滤掉数组中元素小于2的元素，取其第一个打印。这里的it就表示每一个元素。
    println("element:" + arr.filter { it < 5 }.component1())

    arr.filter { it < 5 }.component1().apply {
        println("element:$this")
    }


    // 注意：Any是kotlin中的超类，故而Student类也是继承自Any的。这里你可以换成Person类结果是相同的
    val listPerson: List<Any>
    val listStudent: List<Student> =
            listOf(Student(0, "张三", 12, "一班"), Student(1, "王五", 20, "二班"), Student(2, "桃子", 18, "三班"))
    listPerson = listStudent // 协变

    listPerson.forEach { println(it.toString()) }

    val testStr = "abc"
    val sum1 = testStr.sumBy { it.toInt() }
    // sumBy函数的源码  把字符串中的每一个字符转换为Int的值，用于累加，最后返回累加的值
    println(sum1)
    println(testStr.plus(personList.toString()))

    testDemo()

    //Java中，所有的控制结构都是语句，也就是控制结构都没有值
    // kotlin里除了循环（for、do和do/while）以外，大多数控制结构都是表达式(if/when等)
    // 表达式有值，并且能作为另一个表达式的一部分使用
    // 语句总是包围着它的代码块中的顶层元素，并且没有自己的值

    // 等同Java的三元运算符 a > b ? a : b;
    fun max1(a: Int, b: Int): Int {
        return if (a > b) a else b
    }

    fun max2(a: Int, b: Int) = if (a > b) a else b


    fun <T> joinToString(
            collection: Collection<T>,
            separator: String = ", ",
            prefix: String = "",
            postfix: String = ""
    ): String {
        val result = StringBuilder(prefix)
        for ((index, element) in collection.withIndex()) {
            if (index > 0) result.append(separator)
            result.append(element)
        }
        result.append(postfix)
        return result.toString()
    }
    println(joinToString(list, " - "))
    println(joinToString(list, " , ", "["))

    /*
    * 用扩展函数改造Tip3中的列表打印内容函数
    * */
    fun <T> Collection<T>.joinToString3(
            separator: String = ", ",
            prefix: String = "",
            postfix: String = ""
    ): String {
        val result1 = StringBuilder(prefix)
        for ((index, element) in withIndex()) {
            if (index > 0) result1.append(separator)
            result1.append(element)
        }
        result1.append(postfix)
        return result1.toString()
    }

    val list4 = listOf(2, 4, 0)
    println(list4.joinToString3("/"))


    val str11 = "test extension fun"
    println(str11.lastChar())
    println(str11.lastChar)

    val stringBuilder = StringBuilder("abc")
    println(stringBuilder.lastChar)

    val record = listOf<Double>()
    abbreviatedData(record)
    val csv = Gson().toJson(record)
    println()
    println("csv:$csv")

}

/*
* 通过with语句，将result参数作为参数，在内部this也可以省略掉
* */
fun alphabet(): String {
    val result = StringBuilder()
    return with(result) {
        append("START\n")
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("\nEND")
        toString()
    }
}


fun alphabet2(): String {
    return with(StringBuilder()) {
        append("START\n")
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("\nEND")
        toString()
    }
}


/*
 * 扩展函数
 * */
fun String.lastChar(): Char = this[this.length - 1]

/*
* 扩展属性 lastChar获取String的最后一个字符
* */
val String.lastChar: Char
    get() = get(length - 1)

/*
* 扩展属性 lastChar获取StringBuilder的最后一个字符
* */
var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        setCharAt(length - 1, value)
    }

//单例
object Resource {
    const val name = "Name"
}


public fun <T> Collection<T>.toMutableList(): MutableList<T> {
    return ArrayList(this)
}


private fun resultByOpt(num1: Int, num2: Int, result: (Int, Int) -> Int): Int {
    return result(num1, num2)
}


private fun testDemo() {
    //根据传入不同的Lambda表达式，实现了两个数的+、-、*、/
    val result1 = resultByOpt(1, 2) { num1, num2 ->
        num1 + num2
    }

    val result2 = resultByOpt(3, 4) { num1, num2 ->
        num1 - num2
    }

    val result3 = resultByOpt(5, 6) { num1, num2 ->
        num1 * num2
    }

    val result4 = resultByOpt(6, 3) { num1, num2 ->
        num1 / num2
    }

    val result5 = resultByOpt(12, 18) { num1, num2 ->
        (num1 % num2) * (num1 + num2)
    }


    println("result1 + = $result1")
    println("result2 - = $result2")
    println("result3 * = $result3")
    println("result4 / = $result4")
    println("result5 ？ = $result5")
}
