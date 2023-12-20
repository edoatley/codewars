package org.codewars.threaded;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedCounter {

    public static void countInThreads(Counter counter) {
        try {
            ExecutorService executorService1 = Executors.newFixedThreadPool(1);
            ExecutorService executorService2 = Executors.newSingleThreadExecutor();
            ExecutorService executorService3 = Executors.newFixedThreadPool(1);

            for (int i = 1; i <= 100; i++) {
                if (i % 3 == 0) {
                    invoke(counter, i, executorService1);
                } else if (i % 3 == 1) {
                    invoke(counter, i, executorService2);
                } else if (i % 3 == 2) {
                    invoke(counter, i, executorService3);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    private static synchronized void invoke(Counter counter, int i, ExecutorService executorService) throws ExecutionException, InterruptedException {
        executorService.submit(() -> counter.count(i)).get(); // important this is used
    }
}

//    public static void countInThreads(Counter counter) {
//        try {
//            ExecutorService[] execs = new ExecutorService[3];
//
//            for (int i = 0; i < 3; i++) {
//                execs[i] = Executors.newFixedThreadPool(1);
//            }
//
//            for (int i = 1; i <= 100; i++) {
//                int n = i;
//                Runnable task = () -> counter.count(n);
//                execs[n % 3].submit(task).get();
//            }
//
////            for (int i = 0; i < 3; i++) {
////                execs[i].shutdown();
////                execs[i].awaitTermination(1, TimeUnit.SECONDS);
////            }
//        }
//        catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        catch (ExecutionException e) {
//            e.printStackTrace();
//        }