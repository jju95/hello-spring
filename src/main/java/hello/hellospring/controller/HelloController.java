package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Controller
public class HelloController {

	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data", "hello!");
		return "hello";
	}
	
	@GetMapping("hello-mvc")
	public String helloMvc( @RequestParam(value = "name", required = false) String name, Model model) {
		model.addAttribute("name", name);
		return "hello-template";
	}
	
	@GetMapping("hello-string")
	@ResponseBody
	public String helloString( @RequestParam (value = "name") String name) {
		return "hello " +  name;
	}
	
	@GetMapping("hello-api")
	@ResponseBody // 이 어노테이션 쓰면 json으로 반환되는게 국룰임 ^^ 
	public Hello helloApi(@RequestParam("name") String name) {
		Hello hell = new Hello();
		hell.setUserName(name);
		return hell;
	}
	
	@Getter
	@Setter
	@ToString
	class Hello{
		private String userName;
	}
	
}
