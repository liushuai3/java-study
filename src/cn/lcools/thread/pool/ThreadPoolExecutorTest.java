package cn.lcools.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by liushuai3 on 2019/7/23.
 * 三种ExecutorService特性总结:
 *
 * 1. newSingleThreadExecutor
 *
 * 创建一个单线程的线程池。这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
 *
 * 2.newFixedThreadPool
 *
 * 创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。
 *
 * 3. newCachedThreadPool
 *
 * 创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，
 *
 * 那么就会回收部分空闲（60秒不执行任务）的线程，当任务数增加时，此线程池又可以智能的添加新线程来处理任务。此线程池不会对线程池大小做限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。
 *
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5, // 核心池的大小（即线程池中的线程数目大于这个参数时，提交的任务会被放进任务缓存队列）
                10, // 线程池最大能容忍的线程数
                200, //线程存活时间
                TimeUnit.MILLISECONDS, //参数keepAliveTime的时间单位
                new ArrayBlockingQueue<Runnable>(5) //任务缓存队列，用来存放等待执行的任务
        );
        for (int i = 0; i < 15; i++) {
            MyTask myTask = new MyTask(i);
            threadPoolExecutor.execute(myTask);
            System.out.println("线程池中线程数目："+threadPoolExecutor.getPoolSize()+"，队列中等待执行的任务数目："+
                    threadPoolExecutor.getQueue().size()+"，已执行完的任务数目："+threadPoolExecutor.getCompletedTaskCount());
        }
        threadPoolExecutor.shutdown();
    }
}
