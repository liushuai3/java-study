package cn.lcools.thread.hook;

import java.util.concurrent.TimeUnit;

/**
 * Created by liushuai3 on 2019/7/23.
 */
public class CaptureThreadException {
    public static void main(String[] args) {
        //设置线程异常默认回调接口
        Thread.setDefaultUncaughtExceptionHandler((thread,exception)->{
            System.out.println("线程"+thread.getName()+"发生异常");
            exception.printStackTrace();
        });

        Thread thread = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(1/0);

        },"Test-Thread");
        //单独为线程设置异常回调接口
        thread.setUncaughtExceptionHandler((t,exception)->{
            System.out.println("线程0"+t.getName()+"发生异常");
            exception.printStackTrace();
        });
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        Thread thread1 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(1/0);

        },"Test-Thread1");
        //单独为线程设置异常回调接口
        thread1.setUncaughtExceptionHandler((t,exception)->{
            System.out.println("线程1"+t.getName()+"发生异常");
            exception.printStackTrace();
        });
        thread1.start();
    }
}
