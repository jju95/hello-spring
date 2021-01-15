package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Member;
import repository.MemberRepository;
import repository.MemoryMemberRepository;
import service.MemberService;

public class MemberServiceTest {

	MemoryMemberRepository repository;
	MemberService service;
	
	@BeforeEach
	public void beforeEach(){
		repository = new MemoryMemberRepository();
		service = new MemberService(repository);
	}
	
	@AfterEach
	public void afterEach(){
		System.out.println(repository.getClass().getResourceAsStream("테스트아이디"));
		repository.clearStore();
	}
	
	@Test
	public void 회원가입() {
		// given
		Member member = new Member();
		member.setName("테스트아이디");
		
		// when
		long result = service.join(member);
		
		//then (검증)
		Member findMember = service.findOne(result).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	@Test
	public void 중복_회원_검증() {
		// given
		Member member1 = new Member();
		member1.setName("테스트아이디");
		
		Member member2 = new Member();
		member2.setName("테스트아이디");
		
		// when
		long result = service.join(member1);
	    IllegalStateException e = assertThrows(IllegalStateException.class, () -> service.join(member2)); // 문제 터짐
	    
	    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
	}
	
}
