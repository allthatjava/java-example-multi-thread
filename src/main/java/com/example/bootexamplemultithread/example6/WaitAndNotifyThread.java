package com.example.bootexamplemultithread.example6;

import java.util.ArrayList;
import java.util.List;

public class WaitAndNotifyThread{

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        Thread waiter1 = new Thread(new Waiter(list));
        waiter1.setName("Waiter1");
        Thread waiter2 = new Thread(new Waiter(list));
        waiter2.setName("Waiter2");
        Thread notifier = new Thread(new Notifier(list));

        waiter1.start();
        waiter2.start();
        notifier.start();

//        try {
//            waiter1.join();
//            waiter2.join();
//            notifier.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }


}
