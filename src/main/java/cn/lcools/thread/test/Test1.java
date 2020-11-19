package cn.lcools.thread.test;

import java.util.concurrent.locks.LockSupport;

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
    static Thread threadA = null;
    static Thread threadB = null;

    public static void main(String[] args) throws InterruptedException {
        threadA = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + (char) ('A' + i));
                while (threadB.getState() == Thread.State.NEW) {
                }
                LockSupport.unpark(threadB);
                LockSupport.park(threadA);
            }
        }, "A");
        threadB = new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                LockSupport.park(threadB);
                System.out.println(Thread.currentThread().getName() + ":" + i);
                LockSupport.unpark(threadA);
            }
        }, "B");
        threadA.start();
        threadB.start();

    }
}
