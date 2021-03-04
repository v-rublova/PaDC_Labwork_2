package com.main;

import com.images.Threaded;
import com.images.WorkWithImages;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //get image urls from file
        WorkWithImages.WorkWithImages("image_urls.txt");

        byte n_thread = 1;
        while (n_thread <= 10) {
            System.out.println("Stats for " + n_thread + " threads;");
            for (int j = 0; j < 4; j++) {
                System.out.println("Load: " + Threaded.ExecuteThreaded(n_thread));
                System.out.println("Base: " + Threaded.ExecuteThreaded(n_thread, "rebase"));
            }
            if (n_thread == 1) n_thread++;
            else n_thread += 2;
        }
    }
}
