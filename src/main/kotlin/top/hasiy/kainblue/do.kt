package top.hasiy.kainblue

//https://mp.weixin.qq.com/s/qHaN8XSwJ1K2cp8a7Eru5g

fun printE() {
    println("E")
}

fun main() {
    if (true) println("A")
    if (true) {
        println("B")
    }
    if (true) {
        { println("C") }
    }

    { println("D") }

    printE()

    when {
        true -> {
            println("F")
        }
    }

    // A B F
    val date = arrayListOf<String>()

    1.rangeTo(10).reversed().forEach {
        date.add("$it@")
    }

    date.toArray()

    (date.toArray()).forEach {
        println(it)
    }


    var balance = -12

    when (balance > 0) {
        true -> {
            print(String.format("%d.", balance / 100))
        }

        false -> {
            balance = balance.inv() + 1
            print(String.format("-%d.", balance / 100))
        }
    }


    val qq = when (balance.toString().length >= 2) {
        true -> {
            balance.toString().substring(balance.toString().length - 2)
        }

        false -> {
            "0" + balance.toString()
        }
    }

    println(qq)


    when (1 == 1) {
        true -> {

        }

        else -> {}
    }
}