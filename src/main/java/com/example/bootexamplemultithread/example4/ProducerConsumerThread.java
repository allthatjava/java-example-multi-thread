package com.example.bootexamplemultithread.example4;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerThread {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    private static void producer() throws InterruptedException{
        int totalCount = 1;
        Random random = new Random();
        while(totalCount <= 50){
            int randomIn = random.nextInt(10);
            System.out.println(totalCount+"th Adding "+randomIn+" to queue");
            queue.put(randomIn);
            totalCount++;
        }
    }

    private static void consumer(){
        Random random = new Random();
        while(true){
            try{
                Thread.sleep(100);
                if( random.nextInt(10)==0){
                    Integer value = queue.take();
                    System.out.println("Taken value:"+value+"; Queue size is="+queue.size());
                    System.out.println(queue);
                }
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    producer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                    consumer();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
