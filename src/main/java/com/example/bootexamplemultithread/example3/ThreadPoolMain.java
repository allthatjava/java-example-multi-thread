package com.example.bootexamplemultithread.example3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable{
    private int id;

    public Processor(int id){ this.id = id; }

    @Override
    public void run(){
        System.out.println("Process Start:"+id+", "+Thread.currentThread().getName());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Process End:"+id+", "+Thread.currentThread().getName());
    }
}

public class ThreadPoolMain {

    public static void main(String[] args) {
        // Limit the pool size as 2 for testing reuse Pools
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for( int i=0; i<5; i++){
            executor.submit(new Processor(i));
        }
        executor.shutdown();
        System.out.println("All task submitted");

        try{
            executor.awaitTermination(1, TimeUnit.DAYS);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }

        System.out.println("All tasks completed");
    }
}
