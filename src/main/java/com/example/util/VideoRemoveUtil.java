package com.example.util;

import java.io.File;

/**
 * Created by bangae1 on 2016-07-16.
 */
public class VideoRemoveUtil implements Runnable {
    private Thread thread;
    private String temp;

    public VideoRemoveUtil(String temp) {
        this.temp = temp;
        System.out.println("video remove Start count 5 minute !!!!!!!!!! ::::: " + this.temp);
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            File file = new File(temp);
            while(file.exists()) {

                System.out.println("file remove loop :::: " +temp);
                thread.sleep(1000 * 60 * 1);
                file.delete();
            }
            System.out.println("video remove complete !!!!!!!!!! :::: " +temp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
