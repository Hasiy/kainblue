
public class JavaMain {
    public static  void  main(String[] args){
        System.out.println("hello java");

        int a=127;
        int b=1;
        a=~a;
        System.out.println("~a:"+a);
        a=a&b;
        System.out.println("a&b:"+a);
        a=a|b;
        System.out.println("a|b:"+a);
        a=a^b;
        System.out.println("a^b:"+a);
        a=a<<b;
        System.out.println("a<<b:"+a);
        a=a>>b;
        System.out.println("a>>b:"+a);
        a=a>>>b;
        System.out.println("a>>>b:"+a);
        a<<=b;
        System.out.println("a<<=b:"+a);
        a>>=b;
        System.out.println("a>>=b:"+a);
        a>>>=b;
        System.out.println("a>>>=b:"+a);

    }

}
