package top.hasiy.kainblue


/*
 * @Author: hasiy
 * @Date: 2019/6/19 - 09 : 28
 * @LastEditors: zhuxiaoyao
 * @LastEditTime: 2019-09-11 10:15:47
 * @Description: kainblue
 * @Email: hasiy.jj@gmail.com
 */

object SingletonKotlin {
    @Volatile  //添加关键字volatile 防止JVM编译器的指令重排
    private var instance: SingletonKotlin? = null  //单例对象

    //静态工厂方法
    fun getInstance(): SingletonKotlin? {
        if (instance == null) {      //双重检测机制
            synchronized(SingletonKotlin::class.java) {
                //同步锁
                if (instance == null) {     //双重检测机制
                    instance = SingletonKotlin
                }
            }
        }
        return instance
    }

}//私有构造函数


//静态内部类写法
class Config private constructor(){
    companion object{
        fun getInstance = Helper.instance
    }
    private object Helper{
        val instance = Config()
    }
}
