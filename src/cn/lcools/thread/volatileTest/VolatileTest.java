package cn.lcools.thread.volatileTest;

import java.util.concurrent.TimeUnit;

/**
 * Copyright: Copyright (c) 2019 -Linkage
 *
 * @ClassName: VolatileTest
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: liushuai3
 * @date: 2019/8/31 15:03
 * *****
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/8/31     liushuai3           v1.0.0               修改原因
 */
public class VolatileTest {

    final static int maxValue = 5;
    static volatile int initValue = 0;

    public static void main(String[] args) {
        new Thread(()->{
            int value = initValue;
            while (value<maxValue){
                if( value != initValue){
                    System.out.println(Thread.currentThread().getName()+"线程:initValue="+initValue+"初始值被改变");
                    value = initValue;
                }
            }
        },"Reader").start();

        new Thread(()->{
            int value = initValue;
            while (value<maxValue){
                System.out.println(Thread.currentThread().getName()+"线程:initValue="+ ++value);
                initValue = value;
                try {
                    TimeUnit.SECONDS.sleep(2);
                }catch (Exception e){

                }
            }
        },"Updater").start();

    }

}
