package com.mandala.webclient.client;

import android.app.Dialog;
import android.view.View;

import com.mandala.webclient.interfaces.DispacherListener;
import com.mandala.webclient.interfaces.RequestInterface;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by israel on 16/04/16.
 */
public class Dispacher {

    private static int MAX_THREADS = 2;
    private static RequestTask activeTask = null;
    private static Queue<String> queueMax = new LinkedList<>();
    private static Queue<RequestTask> queue = new LinkedList<>();

    public static void setMaxThreads(int maxThreads) {
        MAX_THREADS = maxThreads;
    }

    public static void sendRequest(RequestInterface request){
        RequestTask requestTask = new RequestTask(request);
        requestTask.setDispacherListener(getDispacherListener());
        addToQueue(requestTask);
    }

    public static void sendRequest(View viewLoader, RequestInterface request){
        RequestTask requestTask = new RequestTask(viewLoader, request);
        requestTask.setDispacherListener(getDispacherListener());
        addToQueue(requestTask);
    }

    public static void sendRequest(Dialog dialogLoader, RequestInterface request){
        RequestTask requestTask = new RequestTask(dialogLoader, request);
        requestTask.setDispacherListener(getDispacherListener());
        addToQueue(requestTask);
    }

    private static DispacherListener getDispacherListener(){
        return new DispacherListener(){
            @Override
            public void onRequestEnd(RequestTask task) {
                executeQueue();
            }
        };
    }

    private static void addToQueue(RequestTask task){
        if(queueMax.size() <= MAX_THREADS){
            queueMax.add(String.valueOf(task.hashCode()));
            queue.add(task);
            executeQueue();
        }else
            queue.add(task);
    }

    private static void executeQueue(){
        queueMax.poll();
        activeTask = queue.poll();
        if(activeTask != null){
            if(!activeTask.getRequest().isCanceled())
                activeTask.execute();
            else
                executeQueue();
        }
    }

    private static void cancelAllRequest(){
        if(queue.size() > 0)
            queue.clear();

        if(queueMax.size() > 0)
            queueMax.clear();

        if(activeTask != null)
            activeTask.getRequest().setCancel(true);
    }
}
