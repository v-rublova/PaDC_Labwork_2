package com.main;

import com.images.WorkWithImages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class Main {

    static private ArrayList<String> names = new ArrayList<>() {
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

    public static void main(String[] args) throws IOException {
        WorkWithImages.WorkWithImages("image_urls.txt");
        //single thread download
        byte i = 0;
        for (String url : WorkWithImages.getUrls()) {
            WorkWithImages.DownloadImage(url, names.get(i));
            i++;
        }
        //single thread filesystem
        i = 0;
        for (byte j = 0; j < names.size(); j++) {
            WorkWithImages.RebaseImage("output/" + names.get(i) + ".jpg", "rebase", names.get(i));
            i++;
        }
    }
}
