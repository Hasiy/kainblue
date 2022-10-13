package top.hasiy.kainblue

/**
 * @Author: hasiy
 * @Date: 2019/11/19 - 10 : 10
 * @LastEditors: zhuxiaoyao
 * @LastEditTime: 2019/11/19 - 10 : 10
 * @Description: kainblue
 * @Email: hasiy.jj@gmail.com
 */

//作为中间类型，实现链式链接
sealed class BooleanExt

object Otherwise : BooleanExt()
object TransferData : BooleanExt()

fun Boolean.yes(block: () -> Unit): BooleanExt = when {
    this -> {
        block.invoke()
        TransferData//由于返回值是BooleanExt，所以此处也需要返回一个BooleanExt对象或其子类对象，故暂且定义TransferData object继承BooleanExt
    }
    else -> {//此处为else，那么需要链接起来，所以需要返回一个BooleanExt对象或其子类对象，故定义Otherwise object继承BooleanExt
        Otherwise
    }
}

//为了链接起otherwise方法操作所以需要写一个BooleanExt类的扩展
fun BooleanExt.otherwise(block: () -> Unit) = when (this) {
    is Otherwise -> block.invoke()//判断此时子类，如果是Otherwise子类执行block
    else -> Unit//不是，则直接返回一个Unit即可
}


fun main() {
    val numberList: List<Int> = listOf(1, 2, 3)
    //使用定义好的扩展
    (numberList.size == 3).yes {
        println("true")
    }.otherwise {
        println("false")
    }
}