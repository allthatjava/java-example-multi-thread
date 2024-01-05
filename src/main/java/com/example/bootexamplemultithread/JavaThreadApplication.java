package com.example.bootexamplemultithread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

class SharedResource {
    private volatile boolean flag = false;
    public void setFlagTrue(){
        flag = true;
    }

    public boolean getFlag(){
        return flag;
    }
}

class ThreadRunner extends Thread{

    private SharedResource resource;
    private String name;

    public ThreadRunner(SharedResource resource, String threadName){
        this.resource = resource;
        this.name = threadName;
    }

    @Override
    public void run(){
        System.out.println("ThreadRunner "+name+" Test");

        try {
            if(resource.getFlag())
                System.out.println(name+" Flag:"+resource.getFlag());
            else
                resource.setFlagTrue();

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Runner implements Runnable{
    public void run(){
        System.out.println("Runnable Test");
    }
}

public class JavaThreadApplication{
    public static void main(String[] args) throws InterruptedException {

        Thread runner = new Thread(new Runner());
        runner.start();

        // Used volitile keyword to share the variable's value between thread
        SharedResource resource = new SharedResource();
        ThreadRunner runner2 = new ThreadRunner(resource,"runner2");
        runner2.start();
        ThreadRunner runner3 = new ThreadRunner(resource, "runner3");
        runner3.start();

        Thread.sleep(5000);

//      Thread runner3 = new Thread(new Runnable() {
//          @Override
//          public void run() {
//              System.out.println("Annonymous Thread Test");
//          }
//      });
        // Same as above lines
        Thread runner4 = new Thread(()-> System.out.println("Annonymous Thread Test"));
        runner4.start();

    }
}