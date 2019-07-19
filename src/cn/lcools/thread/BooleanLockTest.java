package cn.lcools.thread;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by liushuai3 on 2019/7/19.
 */
public class BooleanLockTest {
    private final BooleanLock booleanLock = new BooleanLock();
    public static void main(String[] args) throws Exception{
        BooleanLockTest test = new BooleanLockTest();
        //IntStream.range(0,3).mapToObj(i -> new Thread(test::syncMethoud)).forEach(Thread::start);
        new Thread(test::syncMethoud,"T1").start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(test::syncMethoud,"T2");
        t2.start();
        t2.interrupt();

    }
    public void syncMethoud(){
        try{
            booleanLock.lock(3000);
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            booleanLock.unlock();
        }
    }
}
