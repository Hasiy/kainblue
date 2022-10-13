
public class testThreadLocal {

    public static void main(String[] args) {

        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<>();

            //主线程
            stringThreadLocal.set("11111");
            integerThreadLocal.set(111);
            System.out.println(Thread.currentThread().getName() + ":" + stringThreadLocal.get());
            System.out.println(Thread.currentThread().getName() + ":" + integerThreadLocal.get());

            //子线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    stringThreadLocal.set("22222");
                    integerThreadLocal.set(222);
                    System.out.println(Thread.currentThread().getName() + ":" + stringThreadLocal.get());
                    System.out.println(Thread.currentThread().getName() + ":" + integerThreadLocal.get());
                }
            }).start();

    }

}
