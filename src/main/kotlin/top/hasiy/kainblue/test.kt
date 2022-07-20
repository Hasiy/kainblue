import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer = new StringBuffer(scanner.nextLine());
//        List<Integer> k = new ArrayList<>();
//        //用一个列表来装括号的匹配
//        //遇见‘（’添加首次匹配位置进去
//        //遇见‘）’从最后一次‘（’匹配位置开始删除字符串
//        for(int i = 0; i < stringBuffer.length();){
//            if(stringBuffer.charAt(i)=='(') {
//                k.add(i);
//                i++;
//            }
//            else if(stringBuffer.charAt(i)=='<'&&k.size()==0) {
//                stringBuffer.delete(i - 1, i + 1);
//                i = i - 1;
//            }
//            else if(stringBuffer.charAt(i)==')') {
//                stringBuffer.delete(k.get(k.size() - 1),i + 1);
//                i = k.get(k.size() - 1);
//                k.remove(k.size() - 1);
//            }
//            else
//                i++;
//        }
//        System.out.println(stringBuffer);
//    }
}