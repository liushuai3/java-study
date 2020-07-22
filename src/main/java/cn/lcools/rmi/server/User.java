package cn.lcools.rmi.server;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by liushuai3 on 2019/5/17.
 */
public class User implements Serializable {
    private String name;
    private int age;
    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
