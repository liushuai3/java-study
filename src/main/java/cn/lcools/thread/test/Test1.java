package cn.lcools.thread.test;

/**
 * Copyright: Copyright (c) 2020
 *
 * @ClassName: Test1
 * @Description: 请用两个线程交替输出A1B2C3D4...，A线程输出字母，B线程输出数字，要求A线程首先执行，B线程其次执行！
 * @version: v1.0.0
 * @author: liushuai
 * @date: 2020/11/19 10:13
 * *****
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 * 2020/11/19     liushuai           v1.0.0               修改原因
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 26; i++) {
                    synchronized (o) {
                        System.out.println(Thread.currentThread().getName() + ":" + (char) ('A' + i));
                        try {
                            o.notify();
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "A");

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                threadA.start();
                for (int i = 1; i <= 26; i++) {
                    synchronized (o) {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                        o.notify();
                    }
                }
            }
        }, "B");
        threadB.start();

    }
}
