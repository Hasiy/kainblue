package top.hasiy.kainblue


/**
 * @Author: hasiy
 * @Date: 2019/11/19 - 10 : 55
 * @LastEditors: zhuxiaoyao
 * @LastEditTime: 2019/11/19 - 10 : 55
 * @Description: kainblue
 * @Email: hasiy.jj@gmail.com
 */
sealed class BooleanExt1<out T>//定义成协变

object Otherwise1 : BooleanExt1<Nothing>()//Nothing是所有类型的子类型，协变的类继承关系和泛型参数类型继承关系一致

class TransferData1<T>(val data: T) : BooleanExt1<T>()//data只涉及到了只读的操作

//声明成inline函数
inline fun <T> Boolean.yes(block: () -> T): BooleanExt1<T> = when {
    this -> {
        TransferData1(block.invoke())
    }
    else -> Otherwise1
}

inline fun <T> BooleanExt1<T>.otherwise(block: () -> T): T = when (this) {
    is Otherwise1 ->
        block()
    is TransferData1 ->
        this.data
}

fun main(args: Array<String>) {
    val numberList: List<Int> = listOf(1, 2, 3)
    //使用定义好的扩展
    (numberList.size == 2).yes {
        println("true")
    }.otherwise {
        println("false")
    }
}