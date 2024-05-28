package com.example.bootexamplemultithread.example4;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerThread {

    public static void main(String[] args) {
        Producer producer = new Producer();
        producer.start();

        Consumer consumer1=new Consumer(producer);
        consumer1.setName("Consumer 1");
        consumer1.start();
        Consumer consumer2=new Consumer(producer);
        consumer2.setName("Consumer 2");
        consumer2.start();
        Consumer consumer3=new Consumer(producer);
        consumer3.setName("Consumer 3");
        consumer3.start();

        try {
            producer.join();
            consumer1.join();
            consumer2.join();
            consumer3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
