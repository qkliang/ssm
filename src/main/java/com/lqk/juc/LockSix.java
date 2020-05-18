package com.lqk.juc;

/**
 * 第六锁：将两个方法均设置为static方法，并且让两个线程用同一个对象调用两个方法
 * 结论 ：被synchronized和static修饰的方法，锁的对象是类的class对象，两个方法均被static
 *       修饰，所以使用的是同一个锁，后调用的方法需要等待先调用的方法
 */
public class LockSix {
    public static void main(String[] args) {
        NumberSix numberSix = new NumberSix();
        new Thread(new Runnable() {
            @Override
            public void run() {
                numberSix.getOne();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                numberSix.getTwo();
            }
        }).start();
    }
}
class NumberSix{
    public static synchronized void  getOne() {
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
