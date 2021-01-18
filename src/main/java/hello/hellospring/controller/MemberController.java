package hello.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.hellospring.service.MemberService;

@Controller
public class MemberController {
	
	// Di 의존성 주입
	private final MemberService service;
	
	@Autowired // 생성자에서 이걸 쓰면 자동으로 연결해줌 의존관계 주입
	public MemberController(MemberService service) {
		this.service = service;
	}

	
	@GetMapping("/members/new")
	public String createForm() {
		return "members/createMemberForm";
	}
	
	/*
	@PostMapping()
	public String create(MemberForm form) {
		
	}*/
	
}
