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
        System.out.println("加载器："+class1.getClassLoader());
        MyClassLoader myClassLoader1 = new MyClassLoader("E:\\classloader",myClassLoader);
        System.out.println("父加载器："+myClassLoader1.getParent());
        Class class2 = myClassLoader1.loadClass("cn.lcools.nio.client.AClient");
        System.out.println(class2);
        System.out.println("加载器："+class2.getClassLoader());
    }
}
