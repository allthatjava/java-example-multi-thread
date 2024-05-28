package com.example.bootexamplemultithread.example6;

import java.util.List;

public class Waiter implements Runnable {

    private final List<String> list;

    public Waiter(List<String> list){
        this.list = list;
    }

    @Override
    public void run() {
        try {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " Waiting to be notified...");
            synchronized (list) {
                while (list.isEmpty()) {
                    list.wait();
                }
                System.out.println(threadName + " is notified...");
            }

            String extract = null;
            while( (extract = getItemFromList()) != null){
                System.out.println(threadName + ":" + extract);
            }

            System.out.println("\n"+threadName + " Thread finished");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized String getItemFromList() throws InterruptedException {

        String threadName = Thread.currentThread().getName();
        if (!list.isEmpty()) {
            return list.remove(0);
        }
        System.out.println("List is empty. Finishing Thread:"+threadName );

        return null;
    }
}
