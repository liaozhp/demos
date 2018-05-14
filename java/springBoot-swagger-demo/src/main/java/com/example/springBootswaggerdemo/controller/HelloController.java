package com.example.springBootswaggerdemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lzp on 2018/5/14.
 * @author
 * @description
 */
@Api(tags = "HelloController",description = "控制器")
@RequestMapping("/HelloService")
@RestController
public class HelloController {

    @RequestMapping(value = "/hello/{str}",method = RequestMethod.GET)
    @ApiOperation(value = "简单测试用例",notes = "swagger的Hello World 测试用例")
    public String hello(@ApiParam("输入参数示例") @PathVariable("str") String str){
        return "Hello world! "+str;
    }
}
