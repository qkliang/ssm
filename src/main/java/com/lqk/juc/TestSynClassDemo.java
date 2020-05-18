package com.lqk.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 同步代码块
 */
public class TestSynClassDemo {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(3);
        SynclassDemo synclassDemo = new SynclassDemo(countDownLatch);
        long start = System.currentTimeMillis();
        new Thread(synclassDemo,"A窗口").start();
        new Thread(synclassDemo,"B窗口").start();
        new Thread(synclassDemo,"C窗口").start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时："+ (end - start));
    }
}
class SynclassDemo implements Runnable{
    private int tickets = 100;
    private CountDownLatch count;
    public SynclassDemo(CountDownLatch count){
        this.count = count;
    }

    @Override
    public void run() {
        while (true){
            synchronized (this){
                if(tickets > 0){
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+
                            "售票成功，余票为"+ --tickets);
                }else {
                    break;
                }
            }

        }
        count.countDown();
    }
}