package com.example.webmvc.web;

import com.example.webmvc.framework.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("session")
@Controller
public class SessionController extends BaseController {

    @RequestMapping("")
    public String sessionManager(){
        return "session_manager";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public String putSession(@RequestParam("key") String key, @RequestParam("value") String value) {
        request.getSession().setAttribute(key,value);
        return "200";
    }

    @ResponseBody
    @RequestMapping("{key}")
    public String getSession(@PathVariable String key){
        Object value=request.getSession().getAttribute(key);
        return null==value?"null":value.toString();
    }
}
