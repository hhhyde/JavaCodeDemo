package com.example.webmvc.action;

import com.example.webmvc.framework.BaseController;
import com.example.webmvc.framework.FormModel;
import com.example.webmvc.inter.RoleMapper;
import com.example.webmvc.model.Role;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("welcome")
@Scope("prototype")
public class WelcomeController extends BaseController {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired()
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping("")
    public String welcome() {
        return "index";
    }

    @ResponseBody
    @RequestMapping("hello")
    public String returnStr() {
        return "hello world!";
    }


    /**
     * 重定向到一个action
     */
    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:/roleRes!getRoleRes.action";
    }

    /**
     * 转发到一个action
     */
    @RequestMapping(value = "/forward", method = RequestMethod.GET)
    public String forward() {
        return "forward:/roleRes!getRoleRes.action";
    }


    @ResponseBody
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String request() {
        response.setStatus(404);
        return "12";
    }

    @ResponseBody
    @RequestMapping("form")
    public String form(@FormModel("role") Role role) {
        return role.toString();
    }

    @ResponseBody
    @RequestMapping("testDB")
    public String testDB() {
        Role role = roleMapper.selectByPrimaryKey(new Long(75));
        return "200";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws InterruptedException, IOException {
        response.setHeader("Content-disposition", "attachment; filename="
                + new String(file.getOriginalFilename().getBytes("utf-8"), "ISO8859-1"));
        String ss = file.getOriginalFilename();
        response.getOutputStream().write((file.getBytes()));
        return ss;
    }

}
