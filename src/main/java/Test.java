import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Test {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        //https://blog.csdn.net/Stone__Fly/article/details/108357699
        Class cache = Integer.class.getDeclaredClasses()[0];
        Field myCache = cache.getDeclaredField("cache");
        myCache.setAccessible(true);
        Integer[] newCache = (Integer[]) myCache.get(cache);
        newCache[132] = newCache[133]; //前四行获取了 Integer 类中的缓存区后，第五行将缓存区133位置的对象放到了132位置，经过推算，可以发现，133位置放的是5，132原来放的是4。

        int a = 2;
        int b = a + a;
        double c = 0.0;
        System.out.printf("%d + %d = %d", a, a, b); //
        System.out.printf("\n");
        System.out.printf(getMonthBefore(3));
        System.out.printf("\n");
        int aa = Integer.parseInt("49");
        System.out.printf("%d", aa);

        String[] split = "1232.01.17".split("\\.");
        int first = Integer.parseInt(split[0]);
        int second = Integer.parseInt(split[1]);
        int third = Integer.parseInt(split[2]);
        StringBuilder sb = new StringBuilder();
        sb.append(first);
        if (second < 10) {
            sb.append(0);
        }
        sb.append(second);
        if (third < 10) {
            sb.append(0);
        }
        sb.append(third);
        System.out.printf("\n");
        System.out.printf(sb.toString());

    }

    public static String getMonthBefore(int before) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        Calendar ca = Calendar.getInstance(Locale.CHINA);
        ca.setTime(date);
        ca.add(Calendar.MONTH, -before);
        Date starDate = ca.getTime();
        return simpleDateFormat.format(starDate);
    }
}