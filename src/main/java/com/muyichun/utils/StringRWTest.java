package com.muyichun.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/* 售票任务
 * @Author muyichun
 * @Date 2023-05-15 20:52:58$
 */
public class StringRWTest {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();

        TestTask testTask = new TestTask();
        Thread t1 = new Thread(testTask);
        Thread t2 = new Thread(testTask);
        Thread t3 = new Thread(testTask);
        // 启动线程
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println(testTask.sum);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
    }

    static class TestTask implements Runnable{
        FileReader reader = new FileReader(new File("/Users/muyichun/Desktop/asc2.txt"));
        char[] buffer = new char[1];
        int size;
        int sum = 0; //找到多少个目标数字
        int count = 0;

        TestTask() throws Exception {
        }

        @Override
        public void run() {
            try {
                while (reader.ready()) {
                    ticketing();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        private synchronized void ticketing() {
            try {
                if ((size = reader.read(buffer)) != -1) {
                    if (buffer[0] == '9'){
                        count++;
                    }else if (count == 4){
                        sum++;
                        count = 0;
                    }else {
                        count = 0;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 单个文件字符串处理
 */
//public class Main {
//    /**
//     * 产生一个随机的字符串
//     */
//    public static String getRandomString(int length) {
//        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        Random random = new Random();
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < length; i++) {
//            int number = random.nextInt(base.length());
//            sb.append(base.charAt(number));
//        }
//        return sb.toString();
//    }
//    public static void main(String[] args) throws Exception {
//        long startTime = System.currentTimeMillis();
////        FileWriter fileWriter;
////        try
////        {
////            fileWriter = new FileWriter("/Users/muyichun/Desktop/asc2.txt", true);
////            fileWriter.write(getRandomString(99999999));
////            fileWriter.flush();
////            fileWriter.close();
////        }
////        catch (IOException e)
////        {
////            e.printStackTrace();
////        }
//
//        FileReader reader = new FileReader(new File("/Users/muyichun/Desktop/asc2.txt"));
//        char[] buffer = new char[1];
//        int size;
//        int sum = 0; //找到多少个目标数字
//        int count = 0;
//
//        while ((size = reader.read(buffer)) != -1) {
//            if (buffer[0] == '9'){
//                count++;
//            }else if (count == 4){
//                sum++;
//                count = 0;
//            }else {
//                count = 0;
//            }
//        }
//        System.out.println(sum);
//        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
//    }
//}
