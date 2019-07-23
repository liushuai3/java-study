package cn.lcools.thread.lock;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by liushuai3 on 2019/7/19.
 */
public interface Lock {
    void lock() throws InterruptedException;
    void lock(long mills) throws InterruptedException,TimeoutException;
    void unlock();
    List<Thread> getBlockedThreads();
}
