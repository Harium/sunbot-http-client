package com.harium.suneidesis.http.client;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionSpec;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SunbotHttpClient {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private OkHttpClient client;

    public SunbotHttpClient() {
        this.client = new OkHttpClient().newBuilder()
                .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.CLEARTEXT))
                .build();
    }

    public void sendMessage(String url, String message) {
        sendMessage(url, message, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}

            @Override
            public void onResponse(Call call, Response response) {}
        });
    }

    public void sendMessage(String url, String message, Callback callback) {
        String json = "{\"message\":\"" + message + "\"}";
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

}

