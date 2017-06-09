package com.example.webmvc.web;

import com.example.webmvc.cache.RedisCache;
import com.example.webmvc.entity.Role;
import com.example.webmvc.framework.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.LinkedList;
import java.util.List;

@RequestMapping("redis")
@Scope("prototype")
@Controller
@EnableSwagger2
@Api(value = "SayController|一个用来测试swagger注解的控制器")
public class RedisController extends BaseController {

    @Autowired
    private RedisCache cache;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value="根据用户编号获取用户姓名", notes="test: 仅1和2有正确返回")
    @ApiImplicitParam(paramType="query", name = "userNumber", value = "用户编号", required = true, dataType = "Integer")
    public String putCache(@RequestParam(value = "expireTime", required = false) Long expireTime) {
        Role role = new Role();
        role.setRolename("chris123");
        role.setRoleid(123455L);
        role.setF1("F1");
        role.setLevelId("33");
        if (null == expireTime) {
            cache.putCache("role", role);
        } else {
            cache.putCache("role", role, expireTime);
        }
        return "200";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public String putCacheList() {
        Role role1 = new Role();
        role1.setRolename("chris123");
        role1.setRoleid(123455L);
        role1.setF1("F1");
        role1.setLevelId("33");
        Role role2 = new Role();
        role2.setRolename("chris124");
        role2.setRoleid(123455L);
        role2.setF1("F1");
        role2.setLevelId("33");
        List roleList = new LinkedList();
        roleList.add(role1);
        roleList.add(role2);

        cache.putCache("roleList", roleList);

        return "200";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{key}")
    public String getCache(@PathVariable String key) {
        Object obj = cache.getCache(key, String.class);
        return obj.toString();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/role/{key}")
    public String getRoleCache(@PathVariable String key) {
        Object obj = cache.getCache(key, Role.class);
        return obj.toString();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/role/list/{key}")
    public String getRoleCacheList(@PathVariable String key) {
        Object obj = cache.getCacheList(key, Role.class);
        return obj+"";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE,value = "/delete/{key}")
    public String delete(@PathVariable String key){
        cache.deleteCache(key);
        return "200";
    }


}
