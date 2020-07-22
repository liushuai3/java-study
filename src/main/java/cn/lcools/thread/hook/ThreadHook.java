package cn.lcools.thread.hook;

/**
 * Created by liushuai3 on 2019/7/23.
 */
public class ThreadHook {
    public static void main(String[] args) {

        //钩子线程，当主线程执行完成后执行钩子线程
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("钩子线程执行完成");
        }));
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("钩子线程1执行完成");
        }));
        System.out.println("主线程执行完成");
    }
}
