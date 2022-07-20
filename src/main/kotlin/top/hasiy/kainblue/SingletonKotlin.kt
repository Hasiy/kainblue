package top.hasiy.kainblue


/*
 * @Author: hasiy
 * @Date: 2019/6/19 - 09 : 28
 * @LastEditors: zhuxiaoyao
 * @LastEditTime: 2019-09-11 10:15:47
 * @Description: kainblue
 * @Email: hasiy.jj@gmail.com
 */

//双重校验写法
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


//懒加载写法
class Config {
    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Config()
        }
    }
}

//同步锁写法
class Loock {
    companion object {
        @Volatile
        private var instance: Loock? = null

        @Synchronized
        fun get(): Loock {
            if (null == instance) instance = Loock()
            return instance!!
        }
    }

    private var data1 = 0
    fun showMessage() {
        data1++
        println(this.hashCode())
        println("Hello World!data1:$data1 " + "currentThread: " + Thread.currentThread().id + " Time : " + System.currentTimeMillis())
    }

}

//静态内部类写法
class SingleObject private constructor() {
    // 让构造函数为 private，这样该类就不会被实例化
    private var data1 = 0
    fun showMessage() {
        data1++
        println("Hello World!data1:$data1 ")
        println(Thread.currentThread().id)
    }

    companion object {
        //创建 SingleObject 的一个对象
        //  获取唯一可用的对象
        val instance = SingleObject()

//        fun getInstance() = Helper.instance  多此一举

    }

    private object Helper {
        val instance = SingleObject()
    }
}
//java
//public class SingleObject {
//
//   //创建 SingleObject 的一个对象
//   private static SingleObject instance = new SingleObject();
//
//   //让构造函数为 private，这样该类就不会被实例化
//   private SingleObject(){}
//
//   //获取唯一可用的对象
//   public static SingleObject getInstance(){
//      return instance;
//   }
//
//   public void showMessage(){
//      System.out.println("Hello World!");
//   }
//}

