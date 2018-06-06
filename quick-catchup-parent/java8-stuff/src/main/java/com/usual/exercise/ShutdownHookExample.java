package com.usual.exercise;

/**
 * Created by Beauty on 9/2/14.
 */
public class ShutdownHookExample {

    private void attachShutdownHook() {

        Runtime.getRuntime().addShutdownHook(new Thread(){

            @Override
            public void run() {
                System.out.println("Exit in shutdown hook");
            }
        });

    }

    public static void main(String[] args) {

        ShutdownHookExample shutdownHookExample = new ShutdownHookExample();
        shutdownHookExample.attachShutdownHook();
        System.out.println("shutdown hook is attached already");
        //System.exit(1);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
