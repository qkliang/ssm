package com.lqk.juc;

import java.util.concurrent.CountDownLatch;

public class TestSynMethodDemo {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(3);
        SynclassDemo synclassDemo = new SynclassDemo(latch);
        long start = System.currentTimeMillis();
        new Thread(synclassDemo,"A窗口").start();
        new Thread(synclassDemo,"B窗口").start();
        new Thread(synclassDemo,"C窗口").start();

        long end = System.currentTimeMillis();

        System.out.println(end - start);

    }
}
class SynMethodDemo implements Runnable{
    private Integer tickets = 100;
    @Override
    public void run() {
        while (true){
            if(tickets > 0){
                selTicket();
            }else {
                break;
            }
        }
    }
    public synchronized void selTicket(){
        System.out.println(Thread.currentThread().getName()+
                "售票成功，余票为"+ --tickets);
    }
}