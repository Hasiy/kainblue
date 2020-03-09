package top.hasiy.kainblue


/*
 * @Author: hasiy
 * @Date: 2019/11/4 - 14 : 48 
 * @LastEditors: zhuxiaoyao
 * @LastEditTime: 2019/11/4 - 14 : 48 l
 * @Description: kainblue
 * @Email: hasiy.jj@gmail.com
 */

open class Person(open val id: Int, open val name: String, open val age: Int) {
    override fun toString(): String {
        return "Person:(id=$id, name='$name', age=$age)"
    }
}
