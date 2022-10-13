package top.hasiy.kainblue

import kotlinx.coroutines.runBlocking
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.experimental.xor

fun main() = runBlocking {

//    val resourceList: List<top.hasiy.kainblue.DynamicResourceBean> = listOf(
//        top.hasiy.kainblue.DynamicResourceBean("123", "321", 1, 100, 100, "1", "mp4", 0),
//        top.hasiy.kainblue.DynamicResourceBean("234", "432", 0, 140, 180, "12", "jpeg", 1),
//        top.hasiy.kainblue.DynamicResourceBean("345", "543", 0, 200, 300, "123", "", 2)
//    )
//
//    val prepareList: MutableList<top.hasiy.kainblue.DynamicResourceBean> = mutableListOf()
//    resourceList.asFlow()
//        .onEach {
//            val ext = when (it.ext == "") {
//                true -> "JPG"
//                false -> it.ext
//            }
//            val currTime = System.currentTimeMillis()
//            val randomsInt = (currTime / Random(100).nextInt(100) and 0x1FFFF).toInt()
//            val fileName = String.format("id_${currTime}_${randomsInt}.$ext")
//            it.name = fileName //上传名字 非源文件名
//            it.ext = ext
//        }.catch {
//            println("error:${this}")
//        }.onStart {
//            println("onStart")
//        }.onCompletion {
//            println("Done")
//            resourceList.forEach {
//                println("resourceList:${Gson().toJson(it)}")
//            }
////            this.emitAll(channelFlow {
////                awaitClose {
////                    println("Done:awaitClose")
////                }
////            })
//        }.collect() {
//            prepareList.add(it)
//            println("collect:${Gson().toJson(it)}")
//        }
//
//    //.fold() {
//    //        }
//
//    val channel = channelFlow {
//        for (i in 0..10) {
//            send(i)
////            trySend(i)
////            trySendBlocking(i)
//        }
//        actor<Int> {
//
//        }
//        async {
//
//        }
//        awaitClose {
//
//        }
//        select {
//            onTimeout(100) {
//                println("timeOut")
//            }
//        }
//    }.flowOn(Dispatchers.Default)
//        .onEach {
//            println("onEach:$it")
//        }.onStart {
//            println("start")
//        }.onCompletion {
//            println("done")
//        }.onEmpty {
//            println("onEmpty")
//        }.catch {
//            println("catch")
//        }.collectIndexed { index, value ->
//            println("index:$index value:$value ")
//            //.collect() {
//            //    }
//        }
//
//    channel.runCatching {
//
//    }
//
//    callbackFlow<Int> {
//        offer(1)
//        send(2)
//        sendBlocking(3)
////        select {
////        }
//        awaitClose {
//
//        }
//
//    }.onCompletion {
//        println("Done1")
//    }
////        .flowOn(Dispatchers.Default)
//        .collectIndexed { index, value ->
//            println("index:$index,val:$value")
//        }
//
//    val consumer1 = top.hasiy.kainblue.produce<Int> {
//
//    }
//
//    val a = actor<Int> {
//
//    }
//
//
//    val device = 1
//    if (device == 10 || device == 11) {
//        println("????")
//    }

//    val producerScope = ProducerScope<Int> {
//
//    }
//
//    var during = 0
//    during = during or (top.hasiy.kainblue.unsignedByte(0x00 shl 24))
//    during = during or (top.hasiy.kainblue.unsignedByte(0x02 shl 16))
//    during = during or (top.hasiy.kainblue.unsignedByte(0x6d  shl 8))
//    during = during or top.hasiy.kainblue.unsignedByte(0x01)
//    val d = 60.0.div(during)
//    val rpm = d * 1000
//    val sportSpeed = rpm.div(2.68).div(3.6)//km/h
//
//    println("rpm:$rpm ,speed:$sportSpeed")
//
    println(ZoneId.systemDefault())
    println(utc2Local("2021-08-04T05:43:35.315Z"))

    // AB 01 02 09 01 48


}

private fun calculateCheckCode(bytes: ByteArray): Byte {
    //计算校验码 所有字节的补码 取反+1
    var lrc: Byte = 0x00
    for (element in bytes) {
        lrc = (lrc + element and 0xFF).toByte()
    }
    val check = dexToHex((lrc xor 0xFF.toByte()) + 1 and 0xFF)
    return hexToByte(check)
}

/**
 * 将10进制数转对应的16进制的字符串显示
 *
 * @param i 要进行转换显示的十进制数
 * @return 对应的十六进制数
 */
fun dexToHex(i: Int): String {
    var i = i
    val builder = StringBuilder()
    if (i == 0) {
        builder.append("00")
    } else {
        while (i > 0) {
            when (i % 16) {
                10 -> builder.insert(0, 'A')
                11 -> builder.insert(0, 'B')
                12 -> builder.insert(0, 'C')
                13 -> builder.insert(0, 'D')
                14 -> builder.insert(0, 'E')
                15 -> builder.insert(0, 'F')
                else -> builder.insert(0, i % 16)
            }
            i /= 16
        }
    }
    if (builder.length == 1) {
        builder.insert(0, "0")
    }
    return builder.toString()
}

fun hexToByte(inHex: String): Byte {
    return inHex.toInt(16).toByte()
}

private val ISO_OFFSET_DATE_TIME: DateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
private val yyyyMMddHHmmss: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")

fun utc2Local(utc: String, pattern: String = ""): String {
    return when (pattern == "") {
        true -> {
            if (utc.last() != 'z' && utc.indexOf("+") != -1) {
                // "2021-05-13T08:31:29.741111+0000"  -->  "2021-05-13T08:31:29.741111+00:00"
                val stringBuilder1 = StringBuffer(utc)
                stringBuilder1.insert(utc.indexOf("+") + 3, ":")
                yyyyMMddHHmmss.format(ZonedDateTime.parse(stringBuilder1.toString(), ISO_OFFSET_DATE_TIME))
            } else {
                yyyyMMddHHmmss.format(
                    ZonedDateTime.parse(utc, ISO_OFFSET_DATE_TIME).withZoneSameInstant(ZoneId.systemDefault())
                )
            }
        }
        false -> {
            DateTimeFormatter.ofPattern(pattern)
                .format(ZonedDateTime.parse(utc, ISO_OFFSET_DATE_TIME).withZoneSameInstant(ZoneId.systemDefault()))
        }
    }
}


class DynamicResourceBean(
    var thumbnail_url: String = "", // 缩略图
    var resource_url: String = "", // 原图地址
    var resource_type: Int = 0, // 动态类型 0图片，1视频
    var width: Int = 0,
    var height: Int = 0,
    var name: String? = null,  //上传名字 非源文件名
    var ext: String? = null,   // 扩展名
    var id: Int = 0 //
)

//internal fun getIncline(v: Byte): Int {
//    val value = top.hasiy.kainblue.unsignedByte(v)
//    //坡度最小值（可能为负，用第一个bit表示正负，0为正，1为负）
//    return if (value and 0x80 > 0) {
//        // 1xxx  负
//        (128 - value)
//    } else {
//        // 0xxx 正
//        value
//    }
//}

fun unsignedByte(signedByte: Int) = if (signedByte >= 0) signedByte.toInt() else signedByte.toInt() + 256



