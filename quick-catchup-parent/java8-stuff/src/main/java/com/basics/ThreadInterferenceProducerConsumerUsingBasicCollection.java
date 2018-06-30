package com.basics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * It was used at JDK5 times synchronized
 * Later with concurrency Package - use Reentrant locks (making sure use try/finally) lock.tryLock() is also useful
 *
 */
public class ThreadInterferenceProducerConsumerUsingBasicCollection {

    public static void main(String[] args) {

        List<String> buffer = new ArrayList<>();
        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_RED);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_GREEN);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_WHITE);

        new Thread(producer, "Producer-Red").start();
        new Thread(consumer1, "Consumer-Green").start();
        new Thread(consumer2, "Consumer-Yellow").start();
    }
}
    class MyProducer implements Runnable {

        private List<String> buffer;
        private String color;

        public MyProducer(List<String> buffer, String color) {

            this.buffer = buffer;
            this.color = color;
        }

        @Override
        public void run() {
            Random random = new Random();
            String[] nums = {"1", "2", "3", "4", "5"};

            for (String num : nums) {
                System.out.println(color + Thread.currentThread().getName() + " Adding... " + num);
                synchronized (buffer) {
                    buffer.add(num);
                }
               /* try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    System.out.println(color + Thread.currentThread().getName() + " Producer thread was interrupted");
                }*/

            }

            System.out.println(color + Thread.currentThread().getName() + " Adding EOF and exiting");

            synchronized (buffer){
                buffer.add("EOF");
            }
        }
    }

    class MyConsumer implements Runnable {

        private List<String> buffer;
        private String color;

        public MyConsumer(List<String> buffer, String color) {
            this.buffer = buffer;
            this.color = color;
        }

        @Override
        public void run() {

            while(true) {
                synchronized (buffer) {
                    if (buffer.isEmpty()) {
                        continue;
                    }
                    if (buffer.get(0).equals("EOF")) {
                        System.out.println(color + Thread.currentThread().getName() + " Exiting");
                        break;
                    } else {
                        System.out.println(color + Thread.currentThread().getName() + " Removed " + buffer.remove(0));
                    }

                }
            }

        }
    }

