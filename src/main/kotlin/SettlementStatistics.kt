import com.google.gson.Gson
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.random.Random.Default.nextDouble

fun main() {
    val randomList: List<Double> = List(400) { nextDouble(0.0, 100.0) }
    randomList.forEach {
        print(" $it ")
    }
    println()
    abbreviatedData(randomList)
}

fun abbreviatedData(list: List<Double>) {
    val maxCount = 20
    val results = MutableList(maxCount) { 0.0 }
    var j = 0 // result的index
    var count: Double = 0.0 // 用于求平均值
    for (i in list.indices) {
        // 进入下一区间
        if (i >= (j + 1) * list.size / maxCount) {
            results[j] = get2Digits(results[j] / count)
            j += 1
            count = 0.0
        }
        results[j] += list[i] // 区间累计
        count += 1.0
        if (i == list.size - 1) { // 最后区间取平均
            results[j] = get2Digits(results[j] / count)
        }
    }

    val temp = Gson().toJson(results)
    print(" temp:$temp ")
}

fun get2Digits(number: Double): Double {
    val format = DecimalFormat("0.##")
    //未保留小数的舍弃规则，RoundingMode.FLOOR表示直接舍弃。
    format.roundingMode = RoundingMode.FLOOR
    return format.format(number).toDouble()
}