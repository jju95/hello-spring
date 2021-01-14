package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import domain.Member;
import service.MemberService;

public class MemberServiceTest {

	MemberService service = new MemberService();

	@Test
	void 회원가입() {
		// given
		Member member = new Member();
		member.setName("테스트아이디");
		
		// when
		long result = service.join(member);
		
		//then (검증)
		Member findMember = service.findOne(member.getId()).get();
		assertThat(member.getId()).isEqualTo(findMember.getName());
	}

	@Test
	void 중복_회원_검증() {
		// given
		Member member1 = new Member();
		member1.setName("테스트아이디");
		
		Member member2 = new Member();
		member2.setName("테스트아이디");
		
		// when
		long result = service.join(member1);
		long result2 = service.join(member2);
		
		// then
		
	}
	
	
	@Test
	public List<Member> findMembers(Member mem) {
		return null;
	}
	
	@Test
	public Optional<Member> findOne(Long memberId){
		return null;
	}
	
}
