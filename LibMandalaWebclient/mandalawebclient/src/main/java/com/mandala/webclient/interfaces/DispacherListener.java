package com.mandala.webclient.interfaces;

import com.mandala.webclient.client.RequestTask;

/**
 * Created by just_ on 04/06/2017.
 */

public interface DispacherListener {
    void onRequestEnd(RequestTask task);
}
