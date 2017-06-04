package com.mandala.webclient.client;

import android.app.Dialog;
import android.os.AsyncTask;
import android.view.View;

import com.mandala.webclient.interfaces.RequestInterface;
import com.mandala.webclient.model.ResponseObject;

/**
 * Created by israel on 16/04/16.
 */
public class Dispacher {

    public static void sendRequest(RequestInterface request){
        RequestTask requestTask = new RequestTask(request);
        requestTask.execute();
    }

    public static void sendRequest(View viewLoader, RequestInterface request){
        RequestTask requestTask = new RequestTask(viewLoader, request);
        requestTask.execute();
    }

    public static void sendRequest(Dialog dialogLoader, RequestInterface request){
        RequestTask requestTask = new RequestTask(dialogLoader, request);
        requestTask.execute();
    }

    private static class RequestTask extends AsyncTask<Void, Void, Object> {

        private RequestInterface request;
        private Dialog dialogLoader = null;
        private View viewLoader = null;

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
            if(WebClient.getInstance().isConnected()){
                showLoader();
            }else{
                request.onNetworkError();
                cancel(true);
            }
        }

        @Override
        protected Object doInBackground(Void... voids) {
            WebClient webClient = WebClient.getInstance();
            return webClient.dispatch(request);
        }

        @Override
        protected void onPostExecute(Object response) {
            hideLoader();
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
    }

}
