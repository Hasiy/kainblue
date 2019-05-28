
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import com.alibaba.fastjson.JSONObject;

interface G<A, B> {
    B apply(A a);
}
interface F<B, C> {
    C apply(B b);
}
interface FG<A, B, C> {
    C apply(A a);
}

public class ComposeFunInJava<A, B, C> {
    public static void main(String[] args) {
        int sum = IntStream.range(0, 1000).parallel().map(n -> n * n).sum();
        System.out.println("sum=:"+sum);

        G<String, Integer> g = String::length;
        F<Integer, Boolean> f = (x) -> x % 2 != 0;
        FG<String, Integer, Boolean> fg = (x) -> f.apply(g.apply(x));

        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("ab");
        strings.add("abc");


        List<String> result = new ArrayList<>();
        for (String s : strings) {
            if (fg.apply(s)) {
                result.add(s);
            }
        }
        System.out.println("result:"+result);

        JSONObject obj = new JSONObject();
        System.out.println(obj.toString());
        obj.put("key 1", 1);
        obj.put("key 2", 2);
        obj.put("key 3", 3);
        System.out.println(obj.toString());

        for (String key : obj.keySet()) {
            System.out.println(key + "=" + obj.get(key));
        }

    }
}