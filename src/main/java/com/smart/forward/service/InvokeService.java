package com.smart.forward.service;

import com.alibaba.fastjson.JSON;
import com.pubinfo.smart.domain.result.R;
import com.pubinfo.smart.http.SmartAuthApi;
import com.pubinfo.smart.http.SmartInnerApi;
import com.pubinfo.smart.http.SmartManagerApi;
import com.pubinfo.smart.http.SmartMessageApi;
import com.pubinfo.smart.util.RSAUtil;
import com.smart.forward.dto.params.MockQueryOrgInfoByOrgIdDTO;
import com.smart.forward.dto.params.QueryOrgInfoByOrgIdDTO;
import com.smart.forward.mock.MockManger;
import com.smart.forward.util.RUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class InvokeService {

    @Value("${smart.url}")
    private String url;
    @Value("${smart.userId}")
    private String userId;
    @Value("${smart.appMark}")
    private String appMark;
    @Value("${smart.appKey}")
    private String appKey;
    @Value("${smart.serectKey}")
    private String serectKey;
    @Value("${smart.publicKey}")
    private String publicKey;

    public R<String> getAppToken() {
        try {
            R<String> appTokenR = SmartAuthApi.getAppToken(url, appMark, appKey, serectKey);
            if (!"".equals(appTokenR.getData()) && appTokenR.getData() != null) {

                String appToken = RSAUtil.decryptByPublicKey(appTokenR.getData(), publicKey);
                appTokenR.setData(appToken);
            }
            return appTokenR;
        } catch (IOException | GeneralSecurityException e) {
            return RUtil.error(e.getMessage());
        }
    }

    public R<String> getUserToken(String appToken) {
        try {
            R<String> tempTokenR = SmartInnerApi.getTempToken(url, appKey, userId);
            R<String> userTokenR = SmartAuthApi.getUserTokenByTemp(url, appToken, tempTokenR.getData());
            if (!"".equals(userTokenR.getData()) && userTokenR.getData() != null) {
                String userToken = RSAUtil.decryptByPublicKey(userTokenR.getData(), publicKey);
                userTokenR.setData(userToken);
            }
            return userTokenR;
        } catch (IOException | GeneralSecurityException e) {
            return RUtil.error(e.getMessage());
        }
    }

    public R<String> getUserInfo(String appToken, String userToken) {
        try {
            R<String> userInfoR = SmartAuthApi.getUserInfo(url, appToken, userToken);

            if (!"".equals(userInfoR.getData()) && userInfoR.getData() != null) {
                String userInfo = RSAUtil.decryptByPublicKey(userInfoR.getData(), publicKey);
                userInfoR.setData(userInfo);
            }
            return userInfoR;
        } catch (IOException | GeneralSecurityException e) {
            return RUtil.error(e.getMessage());
        }
    }

    public Object invoke(String method, String params) {
        List<Class> libClasses = new ArrayList<>();
        libClasses.add(MockManger.class);
        libClasses.add(SmartInnerApi.class);
        libClasses.add(SmartManagerApi.class);
        libClasses.add(SmartMessageApi.class);
        for (Class libClass : libClasses) {
            Method[] classDeclaredMethods = libClass.getDeclaredMethods();
            for (Method classDeclaredMethod : classDeclaredMethods) {
                String name = classDeclaredMethod.getName();
                if (name.equals(method)) {
                    // 调用对应方法
                    return doInvoke(classDeclaredMethod, params);
                }
            }
        }
        return RUtil.error("not found method");
    }

    private R<String> doInvoke(Method classDeclaredMethod, String params) {
        try {
            String methodName = classDeclaredMethod.getName();
            R<String> resultR;
            switch (methodName) {
                case "mockQueryOrgInfoByOrgId":
                    MockQueryOrgInfoByOrgIdDTO mockDTO = JSON.parseObject(params, MockQueryOrgInfoByOrgIdDTO.class);
                    resultR = MockManger.mockQueryOrgInfoByOrgId(mockDTO.getUrl(), mockDTO.getAppToken(), mockDTO.getOrgId());
                    break;
                case "queryOrgInfoByOrgId":
                    QueryOrgInfoByOrgIdDTO dto = JSON.parseObject(params, QueryOrgInfoByOrgIdDTO.class);
                    resultR = SmartManagerApi.queryOrgInfoByOrgId(dto.getUrl(), dto.getAppToken(), dto.getOrgId());
                    break;
                default:
                    return null;
            }
            if (!"".equals(resultR.getData()) && resultR.getData() != null) {
                String resultData = RSAUtil.decryptByPublicKey(resultR.getData(), publicKey);
                resultR.setData(resultData);
            }
            return resultR;
        } catch (IOException | GeneralSecurityException e) {
            return RUtil.error(e.getMessage());
        }
    }
}
