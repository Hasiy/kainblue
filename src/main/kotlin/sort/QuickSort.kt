package sort


/**
 * @Author: hasiy
 * @Date: 2020/4/2 - 10 : 44
 * @LastEditors: zhuxiaoyao
 * @LastEditTime: 2020/4/2 - 10 : 44
 * @Description: kainblue
 * @Email: hasiy.jj@gmail.com
 */
// 其实在 Quick Sort 一个很重要的部分就是一个分隔操作（partition），
// 以第一个数为界，将整个数组分成 2 个部分，
// 然后依次递归这个过程，如果当前数组的大小小于等于 1 的话，
// 那么就没必要继续进行分隔了。这就是二路快排的思路简述。
//      每次递归：
//     如果数组或集合长度小于等于 1：
//         返回这个数组或集合
//     否则：
//         以第一个元素为界，将所有元素分成两部分；
//         对两个部分继续递归；
//         拼接两个部分以及第一个元素；
//         返回拼接结果；

fun quickSort(list: List<Int>): List<Int> {
    return if (list.size <= 1) list
    else {
        list.takeLast(list.lastIndex)
            .partition { it < list[0] }
            .run { quickSort(first) + list[0] + quickSort(second) }
    }
}


// 快排排序
fun <Int : Comparable<Int>> Iterable<Int>.quickSort(): MutableList<Int> {
    return toMutableList().apply {

    }
}


fun sort(array: MutableList<Int>): MutableList<Int> {
    if (array.size == 0 || array.size == 1) return array
    val flag = array[0]
    //删除基准元素，如果不删除的话，基准值也会进入左边篮子中，而且永远是第一个位置，第二次开始，所有元素都小于4，就会进入死循环。
    array.removeAt(0)
    val leftArray = mutableListOf<Int>()
    val rightArray = mutableListOf<Int>()
    //站队，小于等于基准值的放到左边篮子里，大于基准值放到右边篮子里
    array.forEach {
        if (it <= flag) {
            leftArray.add(it)
        } else {
            rightArray.add(it)
        }
    }
    //递归排序左右两侧数据
    val sortedLeftArray = sort(leftArray)
    val sortedRightArray = sort(rightArray)

    //排序完成后,组装
    val sortedArray = mutableListOf<Int>()
    sortedLeftArray.forEach {
        sortedArray.add(it)
    }
    sortedArray.add(flag)
    sortedRightArray.forEach {
        sortedArray.add(it)
    }

    return sortedArray
}