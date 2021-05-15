package com.harium.suneidesis.http.client;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class SunbotHttpClientTest {

    private SunbotHttpClient sunbotHttpClient;

    @Before
    public void setUp() {
        sunbotHttpClient = new SunbotHttpClient();
    }

    @Test
    public void testBuildMessage() throws JSONException {
        String json = sunbotHttpClient.buildMessage("Hello");
        String expected = "{message:\"Hello\"}";
        JSONAssert.assertEquals(expected, json, false);
    }

    @Test
    public void testBuildMessageWithLanguage() throws JSONException {
        sunbotHttpClient.language("en");

        String json = sunbotHttpClient.buildMessage("Hello");
        String expected = "{message:\"Hello\", lang:\"en\"}";
        JSONAssert.assertEquals(expected, json, false);
    }

}
