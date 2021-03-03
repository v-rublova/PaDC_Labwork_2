package com.main;

import com.images.Threaded;
import com.images.WorkWithImages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class Main {


    public static void main(String[] args) throws IOException, InterruptedException {
        WorkWithImages.WorkWithImages("image_urls.txt");
        //single thread download
        /*byte i = 0;
        for (String url : WorkWithImages.getUrls()) {
            WorkWithImages.DownloadImage(url, names.get(i));
            i++;
        }
        //single thread filesystem
        i = 0;
        for (byte j = 0; j < names.size(); j++) {
            WorkWithImages.RebaseImage("output/" + names.get(i) + ".jpg", "rebase", names.get(i));
            i++;
        }*/
        for (int i = 0; i < 10; i++) {
            System.out.println("Load: "+Threaded.ExecuteThreaded(10));
            System.out.println("Base: "+Threaded.ExecuteThreaded(10, "rebase"));

        }

    }
}
