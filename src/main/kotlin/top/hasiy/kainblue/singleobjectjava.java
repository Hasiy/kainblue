package top.hasiy.kainblue;

//静态内部类写法
public class singleobjectjava {
    //创建 SingleObject 的一个对象
    private static singleobjectjava instance = new singleobjectjava();

    // 让构造函数为 private，这样该类就不会被实例化
    private singleobjectjava() {
    }

    //  获取唯一可用的对象
    public static singleobjectjava getInstance() {
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello World!");
    }
}