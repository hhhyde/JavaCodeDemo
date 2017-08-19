package com.example.webmvc.web;

import com.example.webmvc.entity.Users;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RequestMapping("swagger")
@RestController
@Api(description = "SwaggerAPI 模板文件",tags = {"Tag1"})
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


    @ApiOperation(value = "接受一个对象")
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Users.class),
            @ApiResponse(code = 404, message = "Location not found. We are not able to detect the user current location")
    })
    private String object(Users users) {
        System.out.println(users.getName());
        return "200";
    }

}
