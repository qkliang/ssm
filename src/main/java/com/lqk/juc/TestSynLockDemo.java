package com.lqk.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestSynLockDemo {
    public static void main(String[] args) {
        SynLockDemo sel = new SynLockDemo();
        new Thread(sel,"A窗口").start();
        new Thread(sel,"B窗口").start();
        new Thread(sel,"C窗口").start();
    }

}
class SynLockDemo implements Runnable{
    private int ticks = 100;
    private Lock lock = new ReentrantLock();


    @Override
    public void run() {
        while (true){
            lock.lock();
            try {
                if(ticks > 0){
                    Thread.sleep(20);
                    System.out.println(Thread.currentThread().getName()+ " 售票成功，余票为"+ --ticks);
                }else{
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
