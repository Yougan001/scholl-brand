package icu.yougan.trade.utils;

import icu.yougan.trade.service.OrderService;

import java.util.concurrent.DelayQueue;

/**
 * @Author:油柑
 * @Date:2024/5/18 22:38
 * @Version:v1.0.0
 * @Description:订单工具处理器
 **/
public class OrderTaskHandler {
    public static OrderService orderService=null;

    private static DelayQueue<OrderTask> delayQueue = new DelayQueue<>();

    public static void run(){
        new Thread(() -> {
            while (true) {
                if(orderService!=null&&delayQueue.size() >0){
                    OrderTask orderTask = delayQueue.poll();
                    if (orderTask != null) {
                        if(orderService.updateOrder(orderTask.getOrderModel())){
                            System.out.println("成功取消订单："+orderTask.getOrderModel());
                        }else {
                            System.out.println("取消任务："+orderTask.getOrderModel());
                        }

                    }
                }
            }
        }).start();

    }

    public static void addOrder(OrderTask o){
        System.out.println("添加任务："+o);
        delayQueue.put(o);
    }
}
