package com.lqk.juc;

public class ThreadDemo extends Thread{
    private Integer tickets = 500;
    int num = 1;
    @Override
    public void run() {
        //获取当前线程的名字
        String currentThreadName = Thread.currentThread().getName();
        if(tickets > 0){
            System.out.println(currentThreadName+"售出火车票No."+num);
            num++;
            tickets--;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
