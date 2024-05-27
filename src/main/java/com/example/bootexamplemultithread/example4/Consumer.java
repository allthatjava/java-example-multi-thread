package com.example.bootexamplemultithread.example4;

public class Consumer extends Thread{

    private Producer producer;
    public Consumer(Producer producer){
        this.producer = producer;
    }
    public void run(){
        while(true){
            producer.consume();
        }
    }
}
