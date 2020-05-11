package com.lqk.juc;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    public static void main(String[] args) {
        MyAtomic myAtomic = new MyAtomic();
        for(int i =0;i< 10 ; i++){
            new Thread(myAtomic).start();
        }
    }
}
class MyAtomic implements Runnable{
    private int num = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getNum());
    }

    public int getNum() {
        return atomicInteger.incrementAndGet();
    }
}

