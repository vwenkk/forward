package com.smart.forward.dto.params;

/**
 * SmartManagerApi.queryOrgInfoByOrgId 方法参数传输对象，把web的json参数转为DTO对象
 * @author Administrator
 */
public class QueryOrgInfoByOrgIdDTO {

    private String url;

    private String appToken;

    private String orgId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return "QueryOrgInfoByOrgIdDTO{" +
                "url='" + url + '\'' +
                ", appToken='" + appToken + '\'' +
                ", orgId='" + orgId + '\'' +
                '}';
    }
}
