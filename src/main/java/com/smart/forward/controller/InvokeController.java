package com.smart.forward.controller;

import com.pubinfo.smart.domain.result.R;
import com.pubinfo.smart.http.SmartAuthApi;
import com.pubinfo.smart.http.SmartInnerApi;
import com.pubinfo.smart.util.RSAUtil;
import com.smart.forward.service.InvokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 接收web请求,通过反射调用对应数据
 * @author Administrator
 */
@RestController
@RequestMapping("/invoke")
public class InvokeController {

    @Resource
    private InvokeService invokeService;


    @RequestMapping(value = "/getAppToken", method = {RequestMethod.GET,RequestMethod.POST})
    public Object getAppToken(){
        return invokeService.getAppToken();
    }

    @RequestMapping(value = "/getUserToken", method = {RequestMethod.GET,RequestMethod.POST})
    public Object getUserToken(String appToken){
        return invokeService.getUserToken(appToken);
    }

    @RequestMapping(value = "/getUserInfo", method = {RequestMethod.GET,RequestMethod.POST})
    public Object getUserInfo(String appToken, String userToken){
        return invokeService.getUserInfo(appToken, userToken);
    }

    @RequestMapping(value = "", method = {RequestMethod.GET,RequestMethod.POST})
    public Object index(@RequestParam("method") String method, @RequestParam("params") String params){
        return invokeService.invoke(method, params);
    }
}
