package top.hasiy.kainblue;

//https://zhuanlan.zhihu.com/p/33102022
//懒汉
 class Singleton {
    private Singleton() {
    }  //私有构造函数

//    private static Singleton instance = null;  //单例对象
    //静态工厂方法
    /*
     * 1.要想让一个类只能构建一个对象，自然不能让它随便去做new操作，因此Signleton的构造方法是私有的。
     * 2.instance是Singleton类的静态成员，也是我们的单例对象。它的初始值可以写成Null，也可以写成new Singleton()。
     * 3.getInstance是获取单例对象的方法。
     *如果单例初始值是null，还未构建，则构建单例对象并返回。这个写法属于单例模式当中的懒汉模式。
     * 如果单例对象一开始就被new Singleton()主动构建，则不再需要判空操作，这种写法属于饿汉模式。
     * 这两个名字很形象：饿汉主动找食物吃，懒汉躺在地上等着人喂。
     */
    //线程不安全
//    public static Singleton getInstance() {
//        if (instance == null) {
//            instance = new Singleton();
//        }
//        return instance;
//    }

    /* 线程安全
     * 1.为了防止new Singleton被执行多次，因此在new操作之前加上Synchronized 同步锁，
     * 锁住整个类（注意，这里不能使用对象锁）。
     * 2.进入Synchronized 临界区以后，还要再做一次判空。因为当两个线程同时访问的时候，
     * 线程A构建完对象，线程B也已经通过了最初的判空验证，不做第二次判空的话，线程B还是会再次构建instance对象。
     */
//    public static Singleton getInstance() {
//        if (instance == null) {      //双重检测机制
//            synchronized (Singleton.class) {  //同步锁
//                if (instance == null) {     //双重检测机制
//                    instance = new Singleton();
//                }
//            }
//        }
//        return instance;
//    }

    //添加关键字volatile 防止JVM编译器的指令重排
    //JVM执行顺序
    //memory =allocate(); //1：分配对象的内存空间
    // ctorInstance(memory); //2：初始化对象
    // instance =memory; //3：设置instance指向刚分配的内存地址
    private volatile static Singleton instance = null;  //单例对象

    //静态工厂方法
    public static Singleton getInstance() {
        if (instance == null) {      //双重检测机制
            synchronized (Singleton.class) {  //同步锁
                if (instance == null) {     //双重检测机制
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}

