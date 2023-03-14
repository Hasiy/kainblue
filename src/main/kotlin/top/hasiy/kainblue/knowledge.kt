package top.hasiy.kainblue

fun main() {
    open class Shape
    class Rectangle : Shape()

    fun Shape.getName() = "Shape"
    fun Rectangle.getName() = "Rectangle"

    fun printClassName(s: Shape) {
        println(s.getName())
    }

    printClassName(Rectangle())

    class Example {
        fun printFunctionType() {
            println("Class method")
        }
    }

    fun Example.printFunctionType() {
        println("Extension function")
    }
    Example().printFunctionType()

    BaseCaller().call(Base())
    DerivedCaller().call(Base())
    DerivedCaller().call(Derived())

    println(System.currentTimeMillis())
}

open class Base {}

class Derived : Base() {}

open class BaseCaller {
    open fun Base.printFunctionInfo() {
        println("BaseCaller 中的扩展函数")
    }

    open fun Derived.printFunctionInfo() {
        println("派生扩展函数在 BaseCaller")
    }

    fun call(b: Base) {
        b.printFunctionInfo() // 调用扩展函数
    }
}

class DerivedCaller : BaseCaller() {
    override fun Base.printFunctionInfo() {
        println("Base extension function in DerivedCaller")
    }

    override fun Derived.printFunctionInfo() {
        println("DerivedCaller 中的派生扩展函数")
    }
}


fun Any?.toString(): String {
    if (this == null) return "null"
    return toString()
}