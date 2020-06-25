package com.rahulkashyap.calldetailsfromrecentcalllog.network.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.rahulkashyap.calldetailsfromrecentcalllog.network.GsonRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class PostCallDetails extends BaseRequest {

    private final String mUrl;
    private final String uid;
    private final String prospect_number;
    private final String call_type;
    private final String talk_time;
    private final String start_datetime;
    private final String end_datetime;

    public PostCallDetails(String mUrl, String uid, String prospect_number, String call_type, String talk_time, String start_datetime, String end_datetime) {
        this.mUrl = mUrl;
        this.uid = uid;
        this.prospect_number = prospect_number;
        this.call_type = call_type;
        this.talk_time = talk_time;
        this.start_datetime = start_datetime;
        this.end_datetime = end_datetime;
    }

    public String getServiceUrl() {
        return mUrl;
    }

    public HashMap<String, String> getParameters() {
        return new HashMap<String, String>();
    }

    private HashMap<String, String> getHeaders() {
        HashMap<String, String> header = new HashMap<String, String>();
        header.put("Authorization", "Basic RVRTQmFzaWNBdXRoOjc4QEg0IzFw");
        //header.put("", "");
        return header;
    }

    public JSONObject getJsonRequest() {
        JSONObject jsonObject = new JSONObject();
        //set body
        try {
            jsonObject.put("user_id",uid );
            jsonObject.put(prospect_number, prospect_number);
            jsonObject.put("call_type",call_type);
            jsonObject.put("talk_time",talk_time);
            jsonObject.put("start_datetime", start_datetime);
            jsonObject.put("end_datetime", end_datetime);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }


    public GsonRequest createServerRequest(Response.ErrorListener errorListener, Response.Listener listener) {
        GsonRequest<JSONObject> gsonRequest = new GsonRequest<>(
                Request.Method.POST, getServiceUrl(),
                JSONObject.class, getParameters(), listener, errorListener, getJsonRequest());
        gsonRequest.setShouldCache(false);
        gsonRequest.setHeader(getHeaders());
        return gsonRequest;
    }
}
