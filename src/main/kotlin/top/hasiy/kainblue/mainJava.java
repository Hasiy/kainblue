package top.hasiy.kainblue;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class mainJava {

    //获得构造器
//    Constructor con = Singleton.class.getDeclaredConstructor();
////设置为可访问
//con.setAccessible(true);
//    //构造两个不同的对象
//    Singleton singleton1 = (Singleton) con.newInstance();
//    Singleton singleton2 = (Singleton) con.newInstance();
////验证是否是不同对象
//System.out.println(singleton1.equals(singleton2));

    public static void main(String[] args) {
        //获取person集合中的所有大于18周岁，并排序
        List<Person> persons = new ArrayList<>();
        List<Person> users = new LinkedList<>();
        persons.add(new Person(1, "name1", 10));
        persons.add(new Person(2, "name2", 21));
        persons.add(new Person(5, "name5", 55));
        persons.add(new Person(3, "name3", 34));
        persons.add(new Person(4, "name4", 6));

        persons.stream()
                .filter(p -> p.getAge() >= 18)//获取所有18岁以上的用户
                .sorted(Comparator.comparing(Person::getAge))//依年纪升序排序
                .sorted(Comparator.comparing(Person::getAge).reversed())//依年纪降序排序
                .collect(Collectors.toList())
                .forEach(System.out::println);

        boolean isAllAdult = persons.stream().
                allMatch(p -> p.getAge() > 18);
        System.out.println("是否所有的用户都大于18岁" + isAllAdult);
    }
}
