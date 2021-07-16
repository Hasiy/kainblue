import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select
import java.util.*

fun main() = runBlocking {

    val resourceList: List<DynamicResourceBean> = listOf(
        DynamicResourceBean("123", "321", 1, 100, 100, "1", "mp4", 0),
        DynamicResourceBean("234", "432", 0, 140, 180, "12", "jpeg", 1),
        DynamicResourceBean("345", "543", 0, 200, 300, "123", "", 2)
    )

    val prepareList: MutableList<DynamicResourceBean> = mutableListOf()
    resourceList.asFlow()
        .onEach {
            val ext = when (it.ext == "") {
                true -> "JPG"
                false -> it.ext
            }
            val currTime = System.currentTimeMillis()
            val randomsInt = (currTime / Random(100).nextInt(100) and 0x1FFFF).toInt()
            val fileName = String.format("id_${currTime}_${randomsInt}.$ext")
            it.name = fileName //上传名字 非源文件名
            it.ext = ext
        }.catch {
            println("error:${this}")
        }.onStart {
            println("onStart")
        }.onCompletion {
            println("Done")
            prepareList.forEach {
                println("prepare:${Gson().toJson(it)}")
            }
//            this.emitAll(channelFlow {
//                awaitClose {
//                    println("Done:awaitClose")
//                }
//            })
        }.collect() {
            prepareList.add(it)
            println("collect:${Gson().toJson(it)}")
        }

    val channel = channelFlow {
        for (i in 0..10) {
            send(i)
//            trySend(i)
//            trySendBlocking(i)
        }
        actor<Int> {

        }
        async {

        }
        awaitClose {

        }
        select {
            onTimeout(100) {
                println("timeOut")
            }
        }
    }.flowOn(Dispatchers.Default)
        .onEach {
            println("onEach:$it")
        }.onStart {
            println("start")
        }.onCompletion {
            println("done")
        }.onEmpty {
            println("onEmpty")
        }.catch {
            println("catch")
        }.collectIndexed { index, value ->
            println("index:$index value:$value ")
            //.collect() {
            //    }
        }

    channel.runCatching {

    }

    callbackFlow<Int> {
        offer(1)
        send(2)
        sendBlocking(3)
//        select {
//        }
        awaitClose {

        }

    }.onCompletion {
        println("Done1")
    }
//        .flowOn(Dispatchers.Default)
        .collectIndexed { index, value ->
            println("index:$index,val:$value")
        }

    val consumer1 = produce<Int> {

    }

    val a = actor<Int> {

    }

//    val producerScope = ProducerScope<Int> {
//
//    }
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

internal fun getIncline(v: Byte): Int {
    val value = unsignedByte(v)
    //坡度最小值（可能为负，用第一个bit表示正负，0为正，1为负）
    return if (value and 0x80 > 0) {
        // 1xxx  负
        (128 - value)
    } else {
        // 0xxx 正
        value
    }
}

fun unsignedByte(signedByte: Byte) = if (signedByte >= 0) signedByte.toInt() else signedByte.toInt() + 256



