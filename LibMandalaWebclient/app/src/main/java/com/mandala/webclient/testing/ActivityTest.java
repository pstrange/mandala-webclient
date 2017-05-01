package com.mandala.webclient.testing;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mandala.webclient.R;
import com.mandala.webclient.client.Dispacher;
import com.mandala.webclient.client.WebClient;
import com.mandala.webclient.testing.model.ResponseGenre;
import com.squareup.okhttp.Response;

/**
 * Created by just_ on 01/05/2017.
 */

public class ActivityTest extends AppCompatActivity {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        progressDialog = new ProgressDialog(this);
        WebClient.init(this);
        findViewById(R.id.button_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dispacher.sendRequest(progressDialog, new RequestGenres(){
                    @Override
                    public void onComplete(Response response, ResponseGenre content) {
                        Toast.makeText(getApplicationContext(), content.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
