package com.example.webmvc.web;

import com.example.webmvc.cache.RedisCache;
import com.example.webmvc.entity.Role;
import com.example.webmvc.framework.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("redis")
@Scope("prototype")
@Controller
public class RedisController extends BaseController {

    @Autowired
    private RedisCache cache;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public String putCache() {
        Role role = new Role();
        role.setRolename("chris123");
        role.setRoleid(123455L);
        role.setF1("F1");
        role.setLevelId("33");
        cache.putCache("role", role);
        return "200";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{key}")
    public String getCache(@PathVariable String key) {
        Object obj = cache.getCache(key, Role.class);
        return "200";
    }

}
