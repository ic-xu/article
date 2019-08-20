package com.admin.cx.task;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by ubuntu
 * On 19-7-9 下午7:04
 */

public class TaskManager {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private static ExecutorService sendEmailService = Executors.newCachedThreadPool();

    private static Map<String, Future> jobManage = new HashMap<>();

    public static void submit(Runnable runnable, String name) {
        Future<?> submit = executorService.submit(runnable);
        jobManage.put(name, submit);
    }


    public static void sendEmilJog(Runnable runnable) {
        sendEmailService.execute(runnable);
    }

    public static Future getJobByName(String name) {
        return jobManage.get(name);
    }

}
