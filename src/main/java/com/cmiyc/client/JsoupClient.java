package com.cmiyc.client;

import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

public abstract class JsoupClient extends AbstractClient {

    public JsoupClient(String baseUrl, String authorizationHeader) {
        super(baseUrl, authorizationHeader);
    }

    public String getRequest(String url) {
        return request(Connection.Method.GET, url, null);
    }

    public String postRequest(String url, String requestBody) {
        return request(Connection.Method.POST, url, requestBody);
    }

    public String putRequest(String url, String requestBody) {
        return request(Connection.Method.PUT, url, requestBody);
    }

    public String deleteRequest(String url) {
        return request(Connection.Method.DELETE, url, null);
    }

    @SneakyThrows
    public String request(Connection.Method method, String url, String requestBody) {
        if(method.hasBody() == (requestBody == null))
            throw new IllegalArgumentException(method.hasBody() ? "Request body cannot be null for this method" : "This method cannot have body");

        Connection connection = prepareRequest(method, url);
        if(requestBody != null)
            connection.requestBody(requestBody);

        Connection.Response response = connection.execute();
        return response.body();
    }

    protected Connection prepareRequest(Connection.Method method, String url) {
        return Jsoup
                .connect(getBaseUrl() + url)
                .method(method)
                .header("Authorization", getAuthorization())
                .ignoreContentType(true);
    }
}
