package com.example.webmvc.web;

import com.example.webmvc.entity.Role;
import com.example.webmvc.entity.Users;
import com.example.webmvc.framework.BaseController;
import com.example.webmvc.framework.FormModel;
import com.example.webmvc.service.RoleService;
import com.example.webmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("welcome")
@Scope("prototype")
public class WelcomeController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

//    @Autowired
//    private UsersMapper usersMapper;

    @Value("${application.message:Hello World}")
    private String message = "Hello World";


    @RequestMapping("")
    public String welcome() {
        return "index";
    }

    @RequestMapping(value = "thymeleaf", method = RequestMethod.GET)
    public String jsp(Map<String, Object> model) {
        model.put("name", "111");
        return "hello";
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
        return "redirect:/roleRes!getRoleRes.web";
    }

    /**
     * 转发到一个action
     */
    @RequestMapping(value = "/forward", method = RequestMethod.GET)
    public String forward() {
        return "forward:/roleRes!getRoleRes.web";
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
        Role role = roleService.selectByPrimaryKey(new Long(75));
        return "200";
    }

    @ResponseBody
    @RequestMapping("users")
    public String users() {
        Users users = userService.selectByPrimaryKey("admin");
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
