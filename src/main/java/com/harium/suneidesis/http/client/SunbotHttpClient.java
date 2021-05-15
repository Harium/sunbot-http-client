package com.harium.suneidesis.http.client;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionSpec;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.Arrays;

public class SunbotHttpClient {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String USER_USERNAME = "user_username";

    private static final String CHANNEL_ID = "channel_id";
    private static final String CHANNEL_NAME = "channel_name";

    private static final String LANGUAGE = "lang";
    public static final String MESSAGE = "message";

    private String user;
    private String userId;
    private String userName;
    private String language;
    private String channelId;
    private String channelName;

    private OkHttpClient client;

    public SunbotHttpClient() {
        this.client = new OkHttpClient().newBuilder()
                .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.CLEARTEXT))
                .build();
    }

    public SunbotHttpClient user(String user) {
        this.user = user;
        return this;
    }

    public SunbotHttpClient userId(String userId) {
        this.userId = userId;
        return this;
    }

    public SunbotHttpClient userName(String userName) {
        this.userName = userName;
        return this;
    }

    public SunbotHttpClient language(String language) {
        this.language = language;
        return this;
    }

    public SunbotHttpClient channelId(String channelId) {
        this.channelId = channelId;
        return this;
    }

    public SunbotHttpClient channelName(String channelName) {
        this.channelName = channelName;
        return this;
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
        String json = buildMessage(message);

        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    protected String buildMessage(String message) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        appendParam(MESSAGE, message, builder);
        appendParam(USER_USERNAME, user, builder);
        appendParam(USER_ID, userId, builder);
        appendParam(USER_NAME, userName, builder);
        appendParam(CHANNEL_ID, channelId, builder);
        appendParam(CHANNEL_NAME, channelName, builder);
        appendParam(LANGUAGE, language, builder);

        // Remove comma from last param
        if (',' == builder.charAt(builder.length() - 1)) {
            builder.deleteCharAt(builder.length() - 1);
        }
        builder.append("}");

        return builder.toString();
    }

    private void appendParam(String tag, String value, StringBuilder builder) {
        if (value == null || value.isEmpty()) {
            return;
        }

        builder.append("\"");
        builder.append(tag);
        builder.append("\":\"");
        builder.append(value);
        builder.append("\",");
    }

}

