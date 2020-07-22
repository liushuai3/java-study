package cn.lcools.annotation;

/**
 * Copyright: Copyright (c) 2020
 *
 * @ClassName: User
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: liushuai
 * @date: 2020/7/22 9:29
 * *****
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 * 2020/7/22     liushuai           v1.0.0               修改原因
 */
@MyAnnotation("类名:User")
public class User {
    String name;

    int age;

    public String getName() {
        return name;
    }

    @MyAnnotation()
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    @MyAnnotation("18")
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
