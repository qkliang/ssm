package com.lqk.juc;

/**
 * 第三锁：相较于第二锁，增加了一个普通的方法
 * 结论 ： 新增的普通方法，因为没有加锁，不受多线程的影响，相较于两个加锁的方法
 *         由于此方法没有延迟200ms，因此先打印，另外两锁共享是同步方法，被同一个对象
 *         调用，共用一把锁，按照调用的先后顺序执行。
 */
public class LockThree {
    public static void main(String[] args) {
        NumberThree nt = new NumberThree();
        new Thread(new Runnable() {
            @Override
            public void run() {
                nt.getOne();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                nt.getTwo();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                nt.getThree();
            }
        }).start();
    }
}
class NumberThree{
    public synchronized void getOne(){
        try {
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public synchronized void getTwo(){
        System.out.println("two");
    }
    public void getThree(){
        System.out.println("three");
    }
}