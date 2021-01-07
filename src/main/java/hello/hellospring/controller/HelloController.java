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
	
	@GetMapping("hello-mvc") // mvc 패턴 방식 
	public String helloMvc( @RequestParam(value = "name", required = false) String name, Model model) {
		model.addAttribute("name", name);
		return "hello-template";
	}
	
	@GetMapping("hello-string")
	@ResponseBody // api 방식
	public String helloString( @RequestParam (value = "name") String name) {
		return "hello " +  name;
		
		// 문자열 return 
	}
	
	@GetMapping("hello-api")
	@ResponseBody // api 방식 
	public Hello helloApi(@RequestParam("name") String name) {
		Hello hell = new Hello();
		hell.setUserName(name);
		return hell; 
		
		// 객체를 return 해줄시 표준 규격은 json 방식으로 처리 하기로 약속함 
		
	}

	// lombok 사용
	@Getter
	@Setter
	@ToString
	class Hello{
		private String userName;
	}
	
}
