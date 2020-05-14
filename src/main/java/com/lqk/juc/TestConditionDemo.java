package com.lqk.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestConditionDemo {
    public static void main(String[] args) {
        ConditionDemo cd = new ConditionDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    cd.printA();
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    cd.printB();
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    cd.printC();
                }
            }
        }, "C").start();
    }
}
class ConditionDemo{
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void printA(){
        try {
            lock.lock();
            if(num !=1 ){
                c1.await();
            }
            System.out.print(Thread.currentThread().getName());
            num = 2;
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void printB(){
        try{
            lock.lock();
            if(num != 2){
                c2.await();
            }
            System.out.print(Thread.currentThread().getName());

            num = 3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printC(){
        try {
            lock.lock();
            if(num != 3){
                c3.await();
            }
            System.out.print(Thread.currentThread().getName());
            num =1;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
