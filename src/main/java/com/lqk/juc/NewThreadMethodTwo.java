package com.lqk.juc;

public class NewThreadMethodTwo implements Runnable {
    private int tickets = 10;
    @Override
    public void run() {
        for (int i = 0;i<100;i++){
            if(tickets > 0){
                System.out.println(Thread.currentThread().getName()
                        +"卖票"+(this.tickets--));
            }else {
                break;
            }

        }
    }

    public static void main(String[] args) {
        NewThreadMethodTwo t = new NewThreadMethodTwo();
        new Thread(t,"A窗口").start();
        new Thread(t,"B窗口").start();
    }
}
