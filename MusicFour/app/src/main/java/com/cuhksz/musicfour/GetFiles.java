package com.cuhksz.musicfour;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GetFiles {
    private static final String path = "/sdcard/Music/";
    private static final String windowsPath = "D:\\Music\\";

    public static ArrayList<String> getAllFileName(String path, String end) {
        ArrayList<String> files = new ArrayList<String>();
        boolean flag = false;
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                String fileName = tempList[i].getName();
                String fileEnd = fileName.substring(fileName.lastIndexOf(".") + 1);
                if (fileEnd.equals(end)){
                    files.add(tempList[i].getName());
                }
            }
        }
        return files;
    }

    public static void main(String[] args) {
        ArrayList<String> fileNames = getAllFileName(windowsPath, "mp3");
        for (String i : fileNames) {
            System.out.println(i);
        }
    }
}
