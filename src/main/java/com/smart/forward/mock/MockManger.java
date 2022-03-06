package com.smart.forward.mock;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.pubinfo.smart.domain.result.R;
import com.pubinfo.smart.util.HttpUtil;

import java.io.IOException;

/**
 * 测试invoke接口
 * @author Administrator
 */
public class MockManger {
    public static R<String> mockQueryOrgInfoByOrgId(String url, String appToken, String orgId) throws IOException {
        JSONObject jObj = new JSONObject();
        jObj.put("orgId", orgId);
//        String resStr = HttpUtil.postJsonByAppToken(url + "/openApi/app/system/manage/queryUserInfoByOrgId", jObj.toJSONString(), appToken);
        String resStr = "{\"code\":0,\"msg\":\"mockSuccess\"}";
        return (R<String>) (R) JSON.parseObject(resStr, new TypeReference<R<String>>() {
        }, new Feature[0]);
    }
}
