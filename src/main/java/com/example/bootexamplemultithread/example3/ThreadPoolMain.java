package com.example.bootexamplemultithread.example3;

import java.util.concurrent.*;

class Processor implements Runnable{
    private int id;

    public Processor(int id){ this.id = id; }

    @Override
    public void run(){
        System.out.println("Process Start:"+id+", "+Thread.currentThread().getName());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Process End:"+id+", "+Thread.currentThread().getName());
    }
}

public class ThreadPoolMain {

    public static void main(String[] args) {
        // Limit the pool size as 2 for testing reuse Pools
//        final ExecutorService executor = Executors.newFixedThreadPool(2);
        final ExecutorService executor = Executors.newCachedThreadPool();

        for( int i=0; i<100; i++){
            executor.submit(new Processor(i));

            final ThreadPoolExecutor threadPool = (ThreadPoolExecutor) executor;
            System.out.println("Current pool size: " + threadPool.getPoolSize());
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
