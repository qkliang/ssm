package com.lqk.juc;

/**
 *  第五锁：将有Thread.sleep()的方法设置为static方法，并且让两个线程用同一个对象调用两个方法
 *  结论 ： 被static修饰的方法实际上是LockFile类对象的方法，是类锁，锁的对象是类的class对象
 *         未被static修饰的方式是对象锁，锁的对象是方法的调用者。
 *         因为两个方法锁的对象不是同一个，所以两个方法用的不是同一个锁，
 *         后调用的方法不用等待先调用的方法
 */
public class LockFive {
    public static void main(String[] args) {
        NumberFive numberFive = new NumberFive();
        new Thread(new Runnable() {
            @Override
            public void run() {
                numberFive.getOne();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                numberFive.getTwo();
            }
        }).start();
    }
}
class NumberFive{
    public static synchronized void getOne(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }
    public synchronized void getTwo(){
        System.out.println("two");
    }
}
