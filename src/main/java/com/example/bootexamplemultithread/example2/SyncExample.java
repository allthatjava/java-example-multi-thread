package com.example.bootexamplemultithread.example2;

public class SyncExample{
    private int count;

    public static void main(String[] args){
        SyncExample se = new SyncExample();
        se.doWork();
    }

    public synchronized void increment(){count++;}

    public void doWork(){
       Thread t1 = new Thread(new Runnable() {
           @Override
           public void run() {
               for(int i=0; i < 1000; i++){
                   increment();
               }
           }
       });

       Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i < 1000; i++){
                    increment();
                }
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

        System.out.println(count);
    }

}
