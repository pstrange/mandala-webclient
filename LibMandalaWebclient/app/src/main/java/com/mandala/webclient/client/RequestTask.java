package com.mandala.webclient.client;

import android.app.Dialog;
import android.os.AsyncTask;
import android.view.View;

import com.mandala.webclient.interfaces.DispacherListener;
import com.mandala.webclient.interfaces.RequestInterface;
import com.mandala.webclient.model.ResponseObject;

/**
 * Created by just_ on 04/06/2017.
 */

public class RequestTask extends AsyncTask<Void, Void, Object> {

    private RequestInterface request;
    private Dialog dialogLoader = null;
    private View viewLoader = null;
    private DispacherListener dispacherListener = null;

    public RequestTask(RequestInterface request){
        this.request = request;
    }

    public RequestTask(View viewLoader, RequestInterface request){
        this.request = request;
        this.viewLoader = viewLoader;
    }

    public RequestTask(Dialog dialogLoader, RequestInterface request){
        this.request = request;
        this.dialogLoader = dialogLoader;
    }

    @Override
    protected void onPreExecute() {
        if(request.isCanceled()){
            cancel(true);
            return;
        }
        if(WebClient.getInstance().isConnected()){
            showLoader();
        }else{
            request.onNetworkError();
            cancel(true);
        }
    }

    @Override
    protected Object doInBackground(Void... voids) {
        if(request.isCanceled())
            return null;

        WebClient webClient = WebClient.getInstance();
        return webClient.dispatch(request);
    }

    @Override
    protected void onPostExecute(Object response) {
        hideLoader();
        reportToDispacher();
        if(request.isCanceled())
            return;
        
        if(response instanceof Exception) {
            Exception ex = (Exception)response;
            request.onError(ex, ex.getMessage());
        }else{
            ResponseObject result = (ResponseObject)response;
            request.onComplete(result.response, result.content);
        }
    }

    public void showLoader(){
        if(dialogLoader != null)
            dialogLoader.show();

        if(viewLoader != null)
            viewLoader.setVisibility(View.VISIBLE);
    }

    public void hideLoader(){
        if(dialogLoader != null && dialogLoader.isShowing())
            dialogLoader.dismiss();

        if(viewLoader != null && viewLoader.getVisibility() == View.VISIBLE)
            viewLoader.setVisibility(View.GONE);
    }

    public RequestInterface getRequest() {
        return request;
    }

    public void reportToDispacher(){
        if(dispacherListener != null)
            dispacherListener.onRequestEnd(this);
    }

    public void setDispacherListener(DispacherListener dispacherListener) {
        this.dispacherListener = dispacherListener;
    }
}