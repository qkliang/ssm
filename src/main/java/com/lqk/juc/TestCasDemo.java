package com.lqk.juc;

/**
 * 模拟CAS算法 ： Compare And Swap
 *  CAS算法优点：
 *      这个算法相对synchronized是比较“乐观的”，它不会像synchronized一样，当一个线程访问共享数据的时候，别的线程都在阻塞。
 *      synchronized不管是否有线程冲突都会进行加锁。由于CAS是非阻塞的，它死锁问题天生免疫，并且线程间的相互影响也非常小，
 *      更重要的是，使用无锁的方式完全没有锁竞争带来的系统开销，也没有线程间频繁调度带来的开销，所以它要比锁的方式拥有更优越的性能。
 *  CAS算法实现思想
 *      在线程开启的时候，会从主存中给每个线程拷贝一个变量副本到线程各自的运行环境中，
 *      CAS算法中包含三个参数(V,E,N)，V表示要更新的变量(也就是从主存中拷贝过来的值)、E表示预期的值、N表示新值。
 *  CAS缺点
 *
 *      循环时间太长；
 *      只能保证一个共享变量原子操作；
 *      会出现ABA问题；
 *  结论
 *      其实就是拿副本中的预期值与主存中的值作比较，如果相等就继续替换新值，如果不相等就说明主存中的值已经被别的线程修改，就继续重试；
 */
public class TestCasDemo {
    public static void main(String[] args) {
        final CompareAndSwap cas = new CompareAndSwap();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int e = cas.get();
                    boolean b = cas.compareAndSet(e, (int) Math.random());
                    System.out.println(b);
                }
            }).start();
        }
    }
}

class CompareAndSwap{
    private int value;

    public synchronized int get(){
        return value;
    }

    public  synchronized int compareAndSwap(int e,int n){
        int oldValue = value;
        if(oldValue == e){
            this.value = n;
        }
        return oldValue;
    }
    public synchronized boolean compareAndSet(int e,int n){
        return e == compareAndSwap(e,n);
    }
}