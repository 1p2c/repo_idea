package com.lagou.controller;

import com.lagou.damain.Test;
import com.lagou.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //组合的注解 @Controller +@ResponseBody
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/findAll")
    public List<Test> findAll(){

        List<Test> testList = testService.findAll();
        return testList;
    }

}
