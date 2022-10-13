package top.hasiy.kainblue


/**
 * @Author: hasiy
 * @Date: 2019/11/11 - 14 : 23
 * @LastEditors: zhuxiaoyao
 * @LastEditTime: 2019/11/11 - 14 : 23
 * @Description: kainblue
 * @Email: hasiy.jj@gmail.com
 * 螺旋矩阵
 * https://www.kancloud.cn/digest/tac-programalgrithm/211807
 */

private var a: Array<Array<Int>> = Array(10) { Array(10) { 0 } }

@Suppress("DEPRECATED_IDENTITY_EQUALS")
fun spiralMatrix(n: Int) {
    var m = 1
    var j = 0
    for (i in 0..(n / 2)) {
        while (j < n - i) {
            if (a[i][j] === 0)
                a[i][j] = m++
            j++
        }

        j = i + 1
        while (j < n - i) {
            if (a[j][n - 1 - i] === 0) {
                a[j][n - 1 - i] = m++
            }
            j++
        }

        j = n - i - 1
        while (j > i) {
            if (a[n - i - 1][j] === 0)
                a[n - i - 1][j] = m++
            j--
        }

        j = n - i - 1
        while (j > i) {
            if (a[j][i] === 0)
                a[j][i] = m++
            j--
        }
    }

    if (n % 2 == 1)
        a[n / 2][n / 2] = m;

}

fun main() {
    println("请输入螺旋矩阵维数：")
    val dimension: Int = readLine()!!.toInt()
    spiralMatrix(dimension)
    println("打印螺旋矩阵：")

    a.forEach { it ->
        it.forEach {
            if (it != 0) {
                val showNumber = String.format(" %3d ", it)
                print(showNumber)
            }
        }
        println()
    }

}