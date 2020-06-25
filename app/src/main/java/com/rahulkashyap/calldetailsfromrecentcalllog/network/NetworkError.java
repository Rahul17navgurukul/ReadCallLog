package com.rahulkashyap.calldetailsfromrecentcalllog.network;

import com.android.volley.VolleyError;

public class NetworkError extends Exception {
    public int code = -1;
    private String message;
    private String localizedMessage;

    public NetworkError(String s) {
        super(s);
        localizedMessage = s;
    }

    public NetworkError(VolleyError volleyError) {
        if (volleyError.networkResponse != null && volleyError.networkResponse.data != null)
            localizedMessage = new String(volleyError.networkResponse.data);
        else
            localizedMessage = volleyError.getLocalizedMessage();
    }

    @Override
    public String getLocalizedMessage() {
        return "" + localizedMessage;
    }


    public String getMessage() {
        return message == null ? super.getMessage() : message;
    }

}