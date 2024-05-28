package com.example.bootexamplemultithread.example4;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Producer extends Thread{

    private final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
    Random random = new Random();
    int consumeCounter = 0;

    @Override
    public void run() {
        int totalCount = 1;
        while(totalCount <= 50){
            int randomIn = random.nextInt(10);
            System.out.println(totalCount+"th Adding "+randomIn+" to queue");
            try {
                queue.put(randomIn);
                System.out.println(queue);
            } catch (InterruptedException e) {
            }
            totalCount++;
        }
    }

    public synchronized void consume(){
        while(!queue.isEmpty()){
            try{
                Thread.sleep(100);
                if( random.nextInt(10)==0){
                    Integer value = queue.take();
                    System.out.println((++consumeCounter)+"th Take - "+Thread.currentThread().getName()+"-Taken value:"+value+"; Queue size is="+queue.size());
                    System.out.println(queue);
                }
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }

}
