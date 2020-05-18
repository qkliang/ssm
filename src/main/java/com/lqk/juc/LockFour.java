package com.lqk.juc;

/**
 * 第四锁：相较于前三锁，第四锁仍然是两个同步方法，但是使用不同的对象调用
 * 结论： 被synchronized修饰的方法，锁的对象是调用者，因为两个方法的调用者是两个不同对象
 *       两个方法用的不是同一个锁，后调用的方法不需要等待先调用的方法
 *       第一个对象有延迟200ms，故先执行getTwo，再执行getOne
 */
public class LockFour {
    public static void main(String[] args) {
        NumberFour numberFour = new NumberFour();
        NumberFour numberFour1 = new NumberFour();
        new Thread(new Runnable() {
            @Override
            public void run() {
                numberFour.getOne();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                numberFour1.getTwo();
            }
        }).start();
    }
}
class NumberFour{
    public synchronized void getOne(){
        try {
            Thread.sleep(200);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("one");
    }
    public synchronized void getTwo(){
        System.out.println("two");
    }
}