package top.hasiy.kainblue

// 计算周长和面积
fun main() {
    val rectangle = Rectangle(5.0, 2.0) // 不需要“new”关键字
    val triangle = Triangle(3.0, 4.0, 5.0)
    println("Area of rectangle is ${rectangle.calculateArea()}, its perimeter is ${rectangle.perimeter}")
    println("Area of triangle is ${triangle.calculateArea()}, its perimeter is ${triangle.perimeter}")
}

//计算周长
abstract class Shape(private val sides: List<Double>) {
    val perimeter: Double get() = sides.sum()
    abstract fun calculateArea(): Double
}

interface RectangleProperties {
    val isSquare: Boolean
}

//长方形
class Rectangle(
    private var height: Double,
    private var length: Double
) : Shape(listOf(height, length, height, length)), RectangleProperties {
    override val isSquare: Boolean get() = length == height  //是否为正方形
    override fun calculateArea(): Double = height * length
}

//三角形
class Triangle(
    private var sideA: Double,
    private var sideB: Double,
    private var sideC: Double
) : Shape(listOf(sideA, sideB, sideC)) {
    override fun calculateArea(): Double {
        val s = perimeter / 2
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC))
    }
}