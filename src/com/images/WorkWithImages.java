package com.images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithImages {
    static private List<String> urls;

    public static List<String> getUrls() {
        return urls;
    }

    static public void WorkWithImages(String filename){
        try  {
            urls = Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static public void DownloadImage(String url,String name) throws IOException {

        BufferedImage image = ImageIO.read(new URL(url));
        ImageIO.write(image, "jpg", new File("output/"+name+".jpg"));
    }
    static public void RebaseImage(String s_path,String d_path,String name) throws IOException{
        BufferedImage image = ImageIO.read(new File(s_path));
        ImageIO.write(image, "jpg", new File(d_path+"/"+name+".jpg"));

    }
    static public void CleanDir(String name){
        File dir = new File(name);
        for(File file: dir.listFiles())
            if (!file.isDirectory())
                file.delete();
    }
}
