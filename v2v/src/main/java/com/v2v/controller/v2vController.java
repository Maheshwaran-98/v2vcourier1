package com.v2v.controller;
	import java.util.Map;
	import com.v2v.config.*;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.RequestMapping;
@Controller
	public class v2vController {
		@RequestMapping("/")
	    public String home(Map<String, Object> model) {
	        //model.put("message", "HowToDoInJava Reader !!");
	        return "home1";
		}
		@RequestMapping("/AboutUs")
		public String aboutus() {
			return "AboutUs";
		}
		@RequestMapping("/AlertOfExisting")
		public String alert() {
			return "AlertOfExisting";
		}
		@RequestMapping("/home1")
		public String home() {
			return "home";
		}
		@RequestMapping("/login")
		public String login() {
			return "login";
		}
		@RequestMapping("/neww")
		public String neww() {
			return "neww";
		}
		@RequestMapping("/welcome")
		public String welcome() {
			return "welcome";
		}
	}


