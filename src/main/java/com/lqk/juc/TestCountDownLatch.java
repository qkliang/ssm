package com.lqk.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁：在完成某些运算时，只有其它所有的运算全部完成，当前运算才继续执行
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(50);
        long startTime = System.currentTimeMillis();
        CountDownLatchDemo cwl = new CountDownLatchDemo(countDownLatch);
        for (int i = 0; i < 50; i++) {
            new Thread(cwl).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();

        System.out.println("耗时：" + (endTime - startTime));
    }

}

class CountDownLatchDemo implements Runnable {
    private CountDownLatch latch;
    public CountDownLatchDemo(CountDownLatch latch){
        this.latch = latch;
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                if (i % 2 == 0) {
                    System.out.println(i);
                }
            }
        } finally {
            latch.countDown();
        }
    }
}