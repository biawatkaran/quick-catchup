package com.basics;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExercise {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new ThreadPoolMessage());

        Future<String> future = executorService.submit(() -> "Callable Lambda Result");

        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
    }

}

class ThreadPoolMessage implements Runnable{

    public void run(){

        List<String> messages = Arrays.asList("Humpty Dumpty sat on a wall",
                "Humpty Dumpty had a great fall",
                "All the king's horses and all the king's men",
                "Couldn't put Humpty together again");


        for(String message: messages) {
            System.out.println("Printing " + message );
        }
    }
}
