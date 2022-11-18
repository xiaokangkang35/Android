package com.n0sense.demoapp12;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Requests {
    private RequestQueue requestQueue;
    private final ObjectMapper mapper = new ObjectMapper();

    public Requests(RequestQueue queue) {
        this.requestQueue = queue;
    }


    public interface STATUS_CODE{
        int ERR_NO_INTERNET = 1001;
        int ERR_REQUEST_FAILED = 1002;
        int SUCCESS = 0;
        int NORMAL = 1;
    }

    public interface Callback {
        interface OnFail{
            void onFail(Map<String, Object> failResponse);
        }
        interface OnSuccess{
            void onSuccess(Map<String, Object> successResponse);
        }
    }

    private int request(String url, int method, Callback.OnSuccess success, Callback.OnFail fail) {
        AtomicReference<Map<String, Object>> data = new AtomicReference<>();
        StringRequest stringRequest = new StringRequest(method, url,
                response -> {
                    try {
                        data.set(mapper.readValue(response, LinkedHashMap.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    success.onSuccess(data.get());
                },
                error -> {
                    fail.onFail(data.get());
                });
        requestQueue.add(stringRequest);
        return STATUS_CODE.NORMAL;
    }

    public int doGet(String url, Callback.OnSuccess success, Callback.OnFail fail){
        return request(url, Request.Method.GET, success, fail);
    }

    public int doPost(String url, Callback.OnSuccess success, Callback.OnFail fail){
        return request(url, Request.Method.POST, success, fail);
    }
}
