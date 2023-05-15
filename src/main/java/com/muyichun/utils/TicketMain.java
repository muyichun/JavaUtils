package com.muyichun.utils;

/* 售票任务
 * @Author muyichun
 * @Date 2023-05-15 20:52:58$
 */
public class TicketMain{
    private static int quantity = 99999;
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        while(quantity > 0){
            //打印车票
            System.out.println(quantity + "号票");
            quantity--;
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
    }
}