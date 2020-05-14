package com.lqk.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁：在完成某些运算时，只有其它所有的运算全部完成，当前运算才继续执行
 * //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
 * public void await() throws InterruptedException { };
 * //和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
 * public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };
 * //将count值减1
 * public void countDown() { };
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
            //将count值减1
            latch.countDown();
        }
    }
}