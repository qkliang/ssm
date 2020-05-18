package com.lqk.juc;

public class NewThreadMethodTwo{
    public static void main(String[] args) {
        NewThreadMethodTwoDemo t = new NewThreadMethodTwoDemo();
        new Thread(t,"A窗口").start();
        new Thread(t,"B窗口").start();
    }
}
class NewThreadMethodTwoDemo implements Runnable{
    private int tickets = 100;
    @Override
    public void run() {
        while(true){
            synchronized (NewThreadMethodTwoDemo.class){
                if(tickets > 0){
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()
                            +"卖票 No."+(this.tickets--));
                }else {
                    break;
                }
            }
        }
    }
}