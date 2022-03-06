package com.smart.forward.controller;

import com.alibaba.fastjson.JSON;
import com.smart.forward.ForwardApplication;
import com.smart.forward.dto.params.MockQueryOrgInfoByOrgIdDTO;
import com.smart.forward.service.InvokeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForwardApplication.class)
class InvokeControllerTest {

    @Resource
    private InvokeService invokeService;
    @Test
    public void testInvokeServiceWithMock() {
        MockQueryOrgInfoByOrgIdDTO mockDTO = new MockQueryOrgInfoByOrgIdDTO();
        String params = JSON.toJSONString(mockDTO);
        Object result = invokeService.invoke("mockQueryOrgInfoByOrgId", params);
        System.out.println(result);
    }
}