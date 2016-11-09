package qian.ling.yi.ext.httpClient;

import java.util.Map;

/**
 * Created by liuguobin on 2016/9/27.
 */
public class HttpClientRequestBuilder {
    private Map<String, String> headMap;
    private Map<String, String> params;
    private String url;

    public HttpClientRequestBuilder(String url) {
        this.url = url;
    }

    public Map<String, String> post(){
        return null;
    }

    public Map<String, String> getHeadMap() {
        return headMap;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {

        return url;
    }

    public HttpClientRequestBuilder setHeadMap(Map<String, String> headMap) {
        this.headMap = headMap;
        return this;
    }

    public HttpClientRequestBuilder setParams(Map<String, String> params) {
        this.params = params;
        return this;
    }

}
