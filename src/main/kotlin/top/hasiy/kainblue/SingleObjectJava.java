package top.hasiy.kainblue;

//饿汉
public class SingleObjectJava {
    //创建 SingleObject 的一个对象
    private static SingleObjectJava instance = new SingleObjectJava();

    //让构造函数为 private，这样该类就不会被实例化
    private SingleObjectJava(){}

    //获取唯一可用的对象
    public static SingleObjectJava getInstance(){
        return instance;
    }

    public void showMessage(){
        System.out.println("Hello World!");
    }
}