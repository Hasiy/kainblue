package top.hasiy.kainblue.rxJava;
/*
 * @Author: hasiy
 * @Date: 2019/12/17 - 10 : 08
 * @LastEditors: zhuxiaoyao
 * @LastEditTime: 2019/12/17 - 10 : 08
 * @Description: kainblue
 * @Email: hasiy.jj@gmail.com
 */

import io.reactivex.Observable;


public class sampleRxJava {

    public static void main(String[] args) {

        Observable.range(0, 10)
                .map(String::valueOf)
                .forEach(System.out::println)
                .isDisposed();

    }

}
