package com.example.webmvc.action;

import com.example.webmvc.framework.BaseController;
import com.example.webmvc.framework.FormModel;
import com.example.webmvc.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("welcome")
@Scope("prototype")
public class WelcomeController extends BaseController {
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
	public String form(@FormModel("user") User user) {
		return user.toString();
	}

}


