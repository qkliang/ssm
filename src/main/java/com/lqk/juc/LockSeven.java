package com.lqk.juc;

/**
 * 第七锁: Thread.sleep() 所在的方法设置为静态方法，另一个设置为无static的同步方法
 *        两个线程用两个对象调用两个方法
 * 结论 ： 被static和synchronized同时修饰的方法，锁的对象是类的class对象；
 *        仅仅被synchronized修饰的方法，锁的对象是方法的调用者;
 *        两个方法用的不是同一个锁，后调用的方法无需等待先调用的方法，
 *        即使是用同一个对象调用两个方法，锁的对象也不是同一个。
 *
 */
public class LockSeven {
    public static void main(String[] args) {
        NumberSeven numberSeven = new NumberSeven();
        NumberSeven numberSeven1 = new NumberSeven();
        new Thread(new Runnable() {
            @Override
            public void run() {
                numberSeven.getOne();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                numberSeven1.getTwo();
            }
        }).start();
    }
}
class NumberSeven{
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
