package com.example.bootexamplemultithread.example6;

import java.util.List;
import java.util.Random;

public class Notifier implements Runnable {

    private List<String> list;
    private Random random = new Random();

    public Notifier(List<String> list){
        this.list = list;
    }

    @Override
    public void run() {
        synchronized (list) {
            while (list.size() <= 10) {
                try {
                    Thread.sleep(100);
                    list.add(String.valueOf(random.nextInt()));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Added numbers:" + list);

            System.out.println("Notifying...");
            list.notify();
        }
    }
}
