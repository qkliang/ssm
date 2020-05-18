package com.lqk.juc;

public class NewThreadMethodOne extends Thread{
    private int tickets = 100;
    private String ThreadName;
    public NewThreadMethodOne(String ThreadName){
        this.ThreadName = ThreadName;
    }
    @Override
    public void run(){
        while(true){
            synchronized (this){
                if(tickets > 0){
                    System.out.println(ThreadName+"卖票 No." + tickets--);
                }else {
                    break;
                }
            }

        }
    }

    public static void main(String[] args) {
        NewThreadMethodOne t1 = new NewThreadMethodOne("A窗口");
        NewThreadMethodOne t2 = new NewThreadMethodOne("B窗口");
        t1.start();
        t2.start();

    }


}

