package com.images;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Threaded {
    static public ArrayList<String> names = new ArrayList<>() {
        {
            add("name_1");
            add("name_2");
            add("name_3");
            add("name_4");
            add("name_5");
            add("name_6");
            add("name_7");
            add("name_8");
            add("name_9");
            add("name_10");
            add("name_11");
            add("name_12");
            add("name_13");
        }
    };
    static private List<String> urls;
    static List<Callable<Long>> callableTasks;


    static public String ExecuteThreaded(int numOfThreads) throws InterruptedException {
        callableTasks = new ArrayList<>();
        WorkWithImages.CleanDir("output");
        List<Long> res = new ArrayList<>();
        urls = WorkWithImages.getUrls();
        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
        boolean tr = true;
        int low_b = 0;
        byte chunk = (byte) Math.ceil(names.size() / (double) numOfThreads);
        int high_b = chunk;
        do {
            if (high_b == names.size()) tr = false;
            int finalLow_b = low_b;
            int finalHigh_b = high_b;
            callableTasks.add(() ->
                    {
                        long start = System.currentTimeMillis();
                        for (int i = finalLow_b; i < finalHigh_b; i++)
                            WorkWithImages.DownloadImage(urls.get(i), names.get(i));
                        long end = System.currentTimeMillis();
                        return (end - start);
                    }
            );
            low_b = high_b;
            if (high_b + chunk <= names.size()) high_b += chunk;
            else {
                high_b = names.size();

            }

        } while (tr);
        //weak point
        long start = System.currentTimeMillis();
        executor.invokeAll(callableTasks)
                .parallelStream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(res::add);
        long end = System.currentTimeMillis();
        long sum = 0;
        for (long i : res)
            sum += i;
        executor.shutdown();
        return ("NumOfThreads =" + numOfThreads + "; Time = " + (end-start));
    }

    static public String ExecuteThreaded(int numOfThreads, String dest) throws InterruptedException {
        callableTasks = new ArrayList<>();
        WorkWithImages.CleanDir(dest);
        List<Long> res = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
        boolean tr = true;
        int low_b = 0;
        byte chunk = (byte) Math.ceil(names.size() / (double) numOfThreads);
        int high_b = chunk;
        do {
            if (high_b == names.size()) tr = false;
            int finalLow_b = low_b;
            int finalHigh_b = high_b;
            callableTasks.add(() ->
                    {
                        long start = System.currentTimeMillis();
                        for (int i = finalLow_b; i < finalHigh_b; i++)
                            WorkWithImages.RebaseImage("output/" + names.get(i) + ".jpg", dest, names.get(i));
                        long end = System.currentTimeMillis();
                        return (end - start);
                    }
            );
            low_b = high_b;
            if (high_b + chunk <= names.size()) high_b += chunk;
            else {
                high_b = names.size();
            }

        } while (tr);
        //weak point
        long start = System.currentTimeMillis();
        executor.invokeAll(callableTasks)
                .parallelStream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(res::add);
        long end = System.currentTimeMillis();
        long sum = 0;
        for (long i : res)
            sum += i;
        executor.shutdown();
        return ("NumOfThreads =" + numOfThreads + "; Time = " + (end-start));
    }

}
