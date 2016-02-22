package com.mconnect.mobile.imageviewer.data.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by pzhou on 15-12-31.
 */

@DatabaseTable(tableName = "resource")
public class Resource {
    @DatabaseField(id = true)
    private String url;

    @DatabaseField
    private String requestHeaders;

    @DatabaseField
    private String requestBody;

    @DatabaseField
    private String responseHeaders;

    @DatabaseField
    private String responseBody;

    @DatabaseField
    private String httpMethod;

    @DatabaseField
    private int httpCode;

    @DatabaseField
    private String className;

    public Resource() {

    }

    private Resource(String url, String requestHeaders, String requestBody, String responseHeaders, String responseBody, String httpMethod, int httpCode, String className) {
        this.url = url;
        this.requestHeaders = requestHeaders;
        this.requestBody = requestBody;
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;
        this.httpMethod = httpMethod;
        this.httpCode = httpCode;
        this.className = className;
    }

    public static Resource create(String url, String requestHeaders, String requestBody, String responseHeaders, String responseBody, String httpMethod, int httpCode, String className) {
        return new Resource(url, requestHeaders, requestBody, responseHeaders, responseBody, httpMethod, httpCode, className);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(String requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(String responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }
}
