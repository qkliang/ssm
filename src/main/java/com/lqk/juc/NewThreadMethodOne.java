package com.lqk.juc;

public class NewThreadMethodOne extends Thread{
    private int tickets = 10;
    private String ThreadName;
    public NewThreadMethodOne(String ThreadName){
        this.ThreadName = ThreadName;
    }
    @Override
    public void run(){
        for(int i=0;i< 100;i++){
            if(tickets > 0){
                System.out.println(this.ThreadName+"卖票" + tickets--);
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

