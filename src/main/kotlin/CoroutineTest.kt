import kotlinx.coroutines.*
import java.util.*


private val lock = Object()
private val any = Any()
private var items = 10
private var maxItems = 101

fun <T> produce(function: () -> Unit) = synchronized(lock) {
    while (items >= maxItems) {
        lock.wait()
    }
    Thread.sleep(Random().nextInt(100).toLong())
    items++
    println("Produced, count is $items: ${Thread.currentThread()}")
    lock.notifyAll()
}

fun consume() = synchronized(lock) {
    while (items <= 0) {
        lock.wait()
    }
    Thread.sleep(Random().nextInt(100).toLong())
    items--
    println("Consumed, count is $items: ${Thread.currentThread()}")
    lock.notifyAll()
}

suspend fun main() {
    println("1")
    GlobalScope.launch {
        println("2")
        async(Dispatchers.IO) {
            println("3")
            delay(1000)
            println("4")
        }.join().let {
            println("5")
        }
        println("6")
    }
    println("7")
    GlobalScope.async {
        println("8")
        withContext(Dispatchers.IO) {
            println("9")
            delay(1000)
            println("10")
        }.let {
            println("11")
        }
        println("12")
    }.join()
    println("13")

//    1
//    7
//    2
//    8
//    9
//    3
//    4
//    10
//    11
//    12
//    5
//    6
//    13
}
