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

    private static RequestTask activeTask = null;
    private static Queue<RequestTask> queue = new LinkedList<>();

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
        if(activeTask == null){
            queue.add(task);
            executeQueue();
        }else
            queue.add(task);
    }

    private static void executeQueue(){
        activeTask = queue.poll();
        if(activeTask != null){
            if(!activeTask.getRequest().isCanceled())
                activeTask.execute();
            else
                executeQueue();
        }
    }

    public static void cancelRequest(RequestInterface request){
        request.setCancel(true);
        if(queue.contains(request))
            queue.remove(request);
    }


    public static void cancelAllRequest(){
        if(queue.size() > 0)
            queue.clear();
        if(activeTask != null)
            activeTask.getRequest().setCancel(true);
    }

    public static String getStatus(){
        return "active: "+String.valueOf(activeTask!=null)+" queue: "+queue.size();
    }

}
