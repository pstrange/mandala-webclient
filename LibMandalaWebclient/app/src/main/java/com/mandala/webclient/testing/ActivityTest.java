package com.mandala.webclient.testing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mandala.webclient.R;
import com.mandala.webclient.client.Dispacher;
import com.mandala.webclient.client.WebClient;
import com.mandala.webclient.interfaces.ClientConfigs;
import com.mandala.webclient.interfaces.RequestConfigs;
import com.mandala.webclient.model.ResponseInfo;
import com.mandala.webclient.testing.model.ResponseGenre;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

/**
 * Created by just_ on 01/05/2017.
 */

public class ActivityTest extends AppCompatActivity {

    LinearLayout layItems = null;
    TextView textStatus = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        layItems = (LinearLayout) findViewById(R.id.lay_items);
        textStatus = (TextView) findViewById(R.id.text_status);

        WebClient.getInstance().setContext(this);
        WebClient.getInstance().setDebugMode(true);
        WebClient.getInstance().setClientConfigs(new ClientConfigs<OkHttpClient>() {
            @Override
            public void configClient(OkHttpClient client) {
                //Instala certificados ssl
            }
        });
        WebClient.getInstance().setRequestConfigs(new RequestConfigs<Request.Builder>() {
            @Override
            public void configRequest(Request.Builder builder) {
                //Agrega cosas en el header de cada peticion por ejemplo tokens o cookies
            }
        });

        findViewById(R.id.button_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View itmView = inflateItem();
                layItems.addView(itmView);

                Dispacher.sendRequest(itmView.findViewById(R.id.loader), new RequestGenres(){
                    @Override
                    public void onComplete(ResponseInfo response, ResponseGenre content) {
                        textStatus.setText(Dispacher.getStatus());
                        ((TextView)itmView.findViewById(R.id.text_display)).setText("Request "+content.toString()+" done");
                    }
                });
                textStatus.setText(Dispacher.getStatus());
            }
        });
    }

    public View inflateItem(){
        View view = LayoutInflater.from(this).inflate(R.layout.layout_request, null);
        return view;
    }
}
