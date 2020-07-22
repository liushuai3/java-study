package cn.lcools.jvm;

/**
 * Created by liushuai3 on 2019/7/24.
 * 自定义类加载器
 * 遵循类加载的双亲委托机制
 */
public class MyClassLoaderTest {
    public static void main(String[] args) throws Exception{
        MyClassLoader myClassLoader = new MyClassLoader("E:\\classloader");
        System.out.println("父加载器："+myClassLoader.getParent());
        Class class1 = myClassLoader.loadClass("cn.lcools.nio.client.AClient");
        System.out.println(class1);
        System.out.println(class1.hashCode());
        System.out.println("加载器："+class1.getClassLoader());
        System.out.println("---------------------------");
        MyClassLoader myClassLoader1 = new MyClassLoader("E:\\classloader",myClassLoader);
        System.out.println("父加载器："+myClassLoader1.getParent());
        Class class2 = myClassLoader1.loadClass("cn.lcools.nio.client.AClient");
        System.out.println(class2);
        System.out.println(class2.hashCode());
        System.out.println("加载器："+class2.getClassLoader());
        System.out.println("---------------------------");
        MyClassLoader myClassLoader2 = new MyClassLoader("E:\\classloader",null);
        System.out.println("父加载器："+myClassLoader2.getParent());
        Class class3 = myClassLoader2.loadClass("cn.lcools.nio.client.AClient");
        System.out.println(class3);
        System.out.println(class3.hashCode());
        System.out.println("加载器："+class3.getClassLoader());

        System.out.println("---------------------------");
        MyClassLoader myClassLoader3 = new MyClassLoader("E:\\classloader",myClassLoader2);
        System.out.println("父加载器："+myClassLoader3.getParent());
        Class class4 = myClassLoader3.loadClass("cn.lcools.nio.client.AClient");
        System.out.println(class4);
        System.out.println(class4.hashCode());
        System.out.println("加载器："+class4.getClassLoader());

    }
}
