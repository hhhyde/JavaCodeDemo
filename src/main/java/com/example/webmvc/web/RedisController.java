package com.example.webmvc.web;

import com.example.webmvc.cache.RedisCache;
import com.example.webmvc.entity.Role;
import com.example.webmvc.framework.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RequestMapping("redis")
@Scope("prototype")
@Controller
public class RedisController extends BaseController {

    @Autowired
    private RedisCache cache;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
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
        Object obj = cache.getCache(key, Role.class);
        return obj.toString();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/list/{key}")
    public String getCacheList(@PathVariable String key) {
        Object obj = cache.getCacheList(key, Role.class);
        return obj+"";
    }

}
