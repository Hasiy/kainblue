package top.hasiy.kainblue

suspend fun main(){

    // 生成斐波那契数列
    // 用协程的迭代器
    val fibonacci = sequence {
        yield(1L)
        var current = 1L
        var next = 1L
        while (true) {
            yield(next)
            next += current
            current = next - current
        }
    }

    fibonacci.take(20).forEach(::println)

    val sequence = sequence {
        yield(1)
        yield(2)
        yield(3)
        yield(4)
        yield(5)
        yieldAll(listOf(1,2,3,4,5,6,7))
    }

    sequence.take(20).forEach(::println)

}
