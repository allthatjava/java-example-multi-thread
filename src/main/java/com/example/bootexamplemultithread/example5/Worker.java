package com.example.bootexamplemultithread.example5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Worker {

    private Random random = new Random();
    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

//    private Object lock1 = new Object();
//    private Object lock2 = new Object();
    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();

    public void stageOne(){
//        synchronized (lock1) {
        lock1.lock();
        System.out.println("Lock1 acquired from "+Thread.currentThread().getName());
            try {
                Thread.sleep(1);
                list1.add(random.nextInt(100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally{
                lock1.unlock();
                System.out.println("Lock1 released from "+Thread.currentThread().getName());
            }
//        }
    }

    public void stageTwo(){
//        synchronized (lock2) {
        lock2.lock();
        System.out.println("Lock2 acquired from "+Thread.currentThread().getName());

            try {
                Thread.sleep(1);
                list2.add(random.nextInt(100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally{
                lock2.unlock();
                System.out.println("Lock2 released from "+Thread.currentThread().getName());
            }
//        }
    }

    public void process(){
        for(int i=0; i<1000; i++){
            stageOne();
            stageTwo();
        }
    }

    public void main(){
        long start = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();
        System.out.println("Durection:"+(end-start)+"ms");
        System.out.println("List1:"+list1.size()+", List2:"+list2.size());

    }
}
