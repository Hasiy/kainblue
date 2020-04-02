package sort

/*
 * @Author: Hasiy
 * @Date: 2020/3/31 - 10 : 00
 * @Description: institute-Android
 * @Email: Zhuxs@venpoo.com
 */

fun <T> MutableList<T>.swap(indexI: Int, indexJ: Int) {
    val temp = get(indexI)
    set(indexI, get(indexJ))
    set(indexJ, temp)
}

//冒泡
fun <T : Comparable<T>> Iterable<T>.bubbleSorted(): MutableList<T> {
    return toMutableList().apply {
        for (i in 0 until size - 1) {
            var flag = false
            for (j in size - 1 downTo i + 1) {
                if (get(j - 1).compareTo(get(j)) == 1) {
                    swap(j - 1, j)
                    flag = true
                }
            }
            if (!flag) return@apply
        }
    }
}

//直接插入
fun <T : Comparable<T>> Iterable<T>.insertSorted(): MutableList<T> {
    return toMutableList().apply {
        for (i in 1 until size) {
            if (get(i).compareTo(get(i - 1)) == -1) {
                val min = get(i)
                var j = i - 1
                while (j >= 0 && min.compareTo(get(j)) == -1) {
                    set(j + 1, get(j))
                    --j
                }
                set(j + 1, min)
            }
        }
    }
}

// 选择
fun <T : Comparable<T>> Iterable<T>.selectSorted(): MutableList<T> {
    return toMutableList().apply {
        for (i in 0 until size - 1) {
            var min = i
            for (j in i + 1 until size) {
                if (get(j).compareTo(get(min)) == -1) {
                    min = j
                }
            }
            if (min != i) swap(i, min)
        }
    }
}

// 希尔
fun <T : Comparable<T>> Iterable<T>.shellSorted(): MutableList<T> {
    return toMutableList().apply {
        var dk = (size - 1) / 2
        while (dk >= 1) {
            for (i in dk until size) {
                if (get(i).compareTo(get(i - dk)) == -1) {
                    val min = get(i)
                    var j = i - dk
                    while (j >= 0 && min.compareTo(get(j)) == -1) {
                        set(j + dk, get(j))
                        j -= dk
                    }
                    set(j + dk, min)
                }
            }
            dk /= 2
        }
    }
}