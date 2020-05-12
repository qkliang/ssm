package com.lqk.juc;

/**
 * 保证了内存可见性，不能保证原子性，原子性需要使用Atomic关键字
 */
public class VolatileDemo {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        new Thread(myThread).start();
        while (true){
            if(myThread.isFlag()){
                System.out.println("-----------------");
                break;
            }
        }
    }
}
class MyThread extends Thread{
    public volatile boolean flag = false;
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag = " + isFlag());
    }
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}