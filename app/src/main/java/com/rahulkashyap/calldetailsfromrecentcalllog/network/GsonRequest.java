package com.rahulkashyap.calldetailsfromrecentcalllog.network;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Volley adapter for JSON requests that will be parsed into Java objects by
 * Gson.
 */
public class GsonRequest<T> extends Request<T> {
    public static final String TAG = "Gson_Request";
    private static final int DEFAULT_MAX_RETRIES = 2;
    private final Gson gson = new Gson();
    private Class<T> clazz;
    private Map<String, String> headers;
    private Map<String, String> parameters;
    private Listener listener;
    private JSONObject bodyParameters = null;
    private Priority mPriority = Priority.NORMAL;
    private boolean isPolicy = true;
    private RetryPolicy retryPolicy;
    private String bodyParams = null;
    private String contentType = "application/json";

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url   URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     */
    public GsonRequest(int method, String url, Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.listener = listener;
        setRetryPolicy();
    }

    public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> parameters, Listener listener,
                       ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.parameters = parameters;
        this.listener = listener;
        setRetryPolicy();
        Log.v("GSON_REQUEST_URL", url);
    }

    public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> parameters, Listener listener,
                       ErrorListener errorListener, JSONObject bodyParameters) {
        this(method, url, clazz, parameters, listener, errorListener);
        this.bodyParameters = bodyParameters;
    }

    public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> parameters, Listener listener,
                       ErrorListener errorListener, String bodyParams) {
        this(method, url, clazz, parameters, listener, errorListener);
        this.bodyParams = bodyParams;
        Log.v("GSON_REQUEST_PARAMS", url);

    }


    @Override
    public byte[] getBody() throws AuthFailureError {
        if (bodyParameters != null) {
            try {
                return bodyParameters.toString().getBytes();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (bodyParams != null) {
            return bodyParams.getBytes();
        }
        return null;
    }

    public String getBodyContentType() {
        return contentType;
    }

    public void setHeader(Map<String, String> headers) {
        String label = System.getProperty("http.agent");
        headers.put("User-Agent", label);
        this.headers = headers;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters != null ? parameters : super.getParams();
    }

    public void setParams(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    protected void deliverResponse(T response) {
        //SharePreferenceUtil.setTokenWorked(context,token);
        listener.onResponse(response);
    }

    @Override
    public Priority getPriority() {
        return mPriority;
    }

    public void setPriority(Priority priority) {
        mPriority = priority;
    }

    private void setRetryPolicy() {
        if (isPolicy()) {
            retryPolicy = new DefaultRetryPolicy((int) TimeUnit.SECONDS.toMillis(60), DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            setRetryPolicy(retryPolicy);
        }

    }

    public RetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

    public void setCustomPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
    }

    public boolean isPolicy() {
        return isPolicy;
    }

    public void setPolicy(boolean isPolicy) {
        this.isPolicy = isPolicy;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            T gsonResp = null;
            if (clazz.equals(JSONObject.class)) {
                try {
                    gsonResp = (T) new JSONObject(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                gsonResp = gson.fromJson(json, clazz);
            }
            return Response.success(gsonResp, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}
