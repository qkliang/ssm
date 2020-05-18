package com.lqk.juc;

/**
 * 第八锁：将两个方法均设置为static方法，并且让两个线程用两个个对象调用两个方法
 * 结论 ：
 */
public class LockEight {
    public static void main(String[] args) {
        NumberEight numberEight = new NumberEight();
        NumberEight numberEight1 = new NumberEight();
        new Thread(new Runnable() {
            @Override
            public void run() {
                numberEight.getOne();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                numberEight1.getTwo();
            }
        }).start();
    }
}
class NumberEight{
    public static synchronized void getOne(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }
    public static synchronized void getTwo(){
        System.out.println("two");
    }
}
