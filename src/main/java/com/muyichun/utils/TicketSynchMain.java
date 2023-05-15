package com.muyichun.utils;

/* 售票任务
 * @Author muyichun
 * @Date 2023-05-15 20:52:58$
 */
public class TicketSynchMain{
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        TicketingTask ticketingTask = new TicketingTask();
        Thread t1 = new Thread(ticketingTask);
        Thread t2 = new Thread(ticketingTask);
        Thread t3 = new Thread(ticketingTask);
        // 启动线程
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
    }
}

class TicketingTask implements Runnable{
    /*
     * 车票数量
     */
    private int quantity = 99999;
    @Override
    public void run() {
        // 当车票数量大于0时
        while(quantity > 0){
            ticketing();
        }
    }
    private synchronized void ticketing(){
        if (quantity > 0) {
            //打印车票
            System.out.println(quantity + "号票");
            quantity--;
        }
    }
}