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


    @RequestMapping(value = "", method = {RequestMethod.GET,RequestMethod.POST})
    public Object index(@RequestParam("method") String method, @RequestParam("params") String params){
        return invokeService.invoke(method, params);
    }
}
