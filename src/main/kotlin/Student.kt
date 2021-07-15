import com.google.gson.annotations.Expose

/*
 * @Author: hasiy
 * @Date: 2019/11/4 - 14 : 49
 * @LastEditors: zhuxiaoyao
 * @LastEditTime: 2020-02-22 22:55:24
 * @Description: kainblue
 * @Email: hasiy.jj@gmail.com
 */

data class Student(override var id: Int, override var name: String, override var age: Int, var cls: String) :
    Person(id, name, age) {
    override fun toString(): String {
        return "Student:(id=$id, name='$name', age=$age , cls='$cls')"
    }
}
