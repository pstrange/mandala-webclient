package com.mandala.webclient.client;

import android.app.Dialog;
import android.view.View;

import com.mandala.webclient.interfaces.DispacherListener;
import com.mandala.webclient.interfaces.RequestInterface;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by israel on 16/04/16.
 */
public class Dispacher {

    private static Integer MAX_THREADS = 3;
    private static Map<String, RequestTask> queueActive = new HashMap<>();
    private static Map<String, RequestTask> queuePending = new HashMap<>();

    public static void setMaxThreads(Integer maxThreads) {
        MAX_THREADS = maxThreads;
    }

    public static void sendRequest(RequestInterface request){
        RequestTask requestTask = new RequestTask(request);
        requestTask.setDispacherListener(getDispacherListener());
        execute(requestTask);
    }

    public static void sendRequest(View viewLoader, RequestInterface request){
        RequestTask requestTask = new RequestTask(viewLoader, request);
        requestTask.setDispacherListener(getDispacherListener());
        execute(requestTask);
    }

    public static void sendRequest(Dialog dialogLoader, RequestInterface request){
        RequestTask requestTask = new RequestTask(dialogLoader, request);
        requestTask.setDispacherListener(getDispacherListener());
        execute(requestTask);
    }

    private static DispacherListener getDispacherListener(){
        return new DispacherListener(){
            @Override
            public void onRequestEnd(RequestTask task) {
                queueActive.remove(task.getRequest().getUrl());
                if(queuePending.size() > 0){
                    RequestTask pendingTask = getNextPending();
                    execute(pendingTask);
                }
            }
        };
    }

    private static void execute(RequestTask task){
        if(queueActive.size() < MAX_THREADS) {
            if(queuePending.containsKey(task.getRequest().getUrl()))
                queuePending.remove(task.getRequest().getUrl());
            queueActive.put(task.getRequest().getUrl(), task);
            task.execute();
        }else{
            queuePending.put(task.getRequest().getUrl(), task);
        }
    }

    public static boolean isActive(RequestInterface request){
        return queueActive.containsKey(request.getUrl());
    }

    public static boolean isPending(RequestInterface request){
        return queuePending.containsKey(request.getUrl());
    }

    public static void cancelRequest(RequestInterface request){
        request.setCancel(true);
        if(isPending(request))
            queuePending.remove(request.getUrl());
    }

    private static RequestTask getNextPending(){
        for(Iterator<Map.Entry<String, RequestTask>> it = queuePending.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, RequestTask> entry = it.next();
            return entry.getValue();
        }
        return null;
    }

}
