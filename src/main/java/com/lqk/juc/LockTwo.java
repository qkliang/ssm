package com.lqk.juc;

/**
 * 第二锁：相较于第一锁，第二锁增加Thread.sleep()给某个方法
 * 结论：被synchronized修饰的方法，锁的对象是调用者，
 *      因为两个方法的调用者是同一个，
 */
public class LockTwo {
    public static void main(String[] args) {
        NumberTwo n = new NumberTwo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                n.getOne();
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                n.getTwo();
            }
        },"B").start();
    }

}
class NumberTwo{
    public synchronized void getOne(){
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " one");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void getTwo(){
        System.out.println(Thread.currentThread().getName() + " two");
    }
}
