package com.example.webmvc.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("swagger")
@RestController
@Api(value = "SwaggerAPI 模板文件")
public class SwaggerAPIController {

    @ApiOperation(value = "接口实例1", notes = "注意事项")
    @RequestMapping(value = "demo1", method = RequestMethod.GET)
    private String demo1(
            @ApiParam(value = "参数1", required = false) @RequestParam(value = "param1", required = false) String param1,
            @ApiParam(value = "必要参数2", required = true) @RequestParam(value = "param2", required = true) String param2) {

        return "ok";
    }

    @ApiOperation(value = "接口实例2", notes = "上传文件只能用 POST")
    @RequestMapping(value = "demo2", method = RequestMethod.POST)
    private String demo2(
            @ApiParam(value = "参数", required = true) @RequestParam(value = "param", required = true) String param,
            @ApiParam(value = "文件", required = true) @RequestParam(value = "file", required = true) MultipartFile file) {

        return "ok";
    }

}
