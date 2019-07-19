package cn.lcools.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import cn.lcools.thread.Lock;

/**
 * Created by liushuai3 on 2019/7/19.
 */
public class BooleanLock implements Lock {
    //当前线程
    private Thread currentThread;
    //加锁状态
    private Boolean locked = false;
    //阻塞线程列表
    private final List<Thread> blockedList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException{
        synchronized (this){
            while (locked){
                //locked 为true 表示其它线程已经加锁，将当前线程加入阻塞列表
                if(!blockedList.contains(Thread.currentThread())){
                    blockedList.add(Thread.currentThread());
                }
                try {
                    //阻塞当前线程，等待通知取消阻塞
                    this.wait();
                }catch (InterruptedException e){
                    //这里线程是上面的wait方法被打断 捕获的异常,从阻塞列表移除当前线程
                    blockedList.remove(Thread.currentThread());
                    throw e;
                }
            }
            //如果走到这里表示 当前线程获取到了锁
            blockedList.remove(Thread.currentThread());
            locked = true;
            currentThread = Thread.currentThread();
            System.out.println("线程"+currentThread.getName()+"获得锁");
        }
    }
    @Override
    public void lock(long mills) throws InterruptedException,TimeoutException{
        synchronized (this) {
            //若果传入时间不合法 直接调用没有超时时间的锁
            if (mills <= 0) {
                lock();
            } else {
                long remainingMills = mills;
                long endMills = System.currentTimeMillis() + remainingMills;
                while (locked){
                    //如果超过了时间，线程还未获取到锁，就抛超时异常。
                    if (remainingMills<=0){
                        throw new TimeoutException();
                    }
                    if(!blockedList.contains(Thread.currentThread())){
                        blockedList.add(Thread.currentThread());
                    }
                    try {
                        this.wait(mills);
                    }catch (InterruptedException e){
                        blockedList.remove(Thread.currentThread());
                        throw e;
                    }
                    remainingMills = endMills - System.currentTimeMillis();
                }
                //如果走到这里表示 当前线程获取到了锁
                blockedList.remove(Thread.currentThread());
                locked = true;
                currentThread = Thread.currentThread();
                System.out.println("线程"+currentThread.getName()+"获得锁");
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this){
            //如果当前线程是获取到锁的线程，就可以去执行释放锁的操作
            if(currentThread == Thread.currentThread()){
                //释放锁
                this.locked = false;
                //唤醒所有正在wait而陷入阻塞的线程，因为locked已经被设置成false，所以可以进入下一轮争抢
                this.notifyAll();
                System.out.println("线程"+currentThread.getName()+"释放锁");
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return blockedList;
    }

}
