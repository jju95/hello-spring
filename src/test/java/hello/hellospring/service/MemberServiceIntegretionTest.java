package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import domain.Member;
import hello.hellospring.repository.MemberRepository;

@SpringBootTest
@Transactional /// <-- auto commit 이 아니라서 케이스가 끝나면, 롤백해줌
public class MemberServiceIntegretionTest {

	// 테스트는 DI 할 필요가 없음
	@Autowired MemberRepository repository;
	@Autowired MemberService service;
	// --- 
	
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
