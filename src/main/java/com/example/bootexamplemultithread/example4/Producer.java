package com.example.bootexamplemultithread.example4;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Producer extends Thread{

    private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public void run() {
        int totalCount = 1;
        Random random = new Random();
        while(totalCount <= 50){
            int randomIn = random.nextInt(10);
            System.out.println(totalCount+"th Adding "+randomIn+" to queue");
            try {
                queue.put(randomIn);
            } catch (InterruptedException e) {
            }
            totalCount++;
        }
    }

    public void consume(){
        Random random = new Random();
        while(!queue.isEmpty()){
            try{
                Thread.sleep(100);
                if( random.nextInt(10)==0){
                    Integer value = queue.take();
                    System.out.println(Thread.currentThread().getName()+"-Taken value:"+value+"; Queue size is="+queue.size());
                    System.out.println(queue);
                }
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }

}
