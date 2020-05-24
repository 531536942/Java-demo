package cn.com.huangdc.io.IODemo;

import java.io.File;

public class FileTest {
    public static void main(String[] args) {
        fileTest();
    }

    public static void fileTest(){
        File file = new File("cd/heelo.txt");
        System.out.println("###");
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getParent());

        System.out.println(file.exists());
    }
}