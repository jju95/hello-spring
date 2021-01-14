package service;

import java.util.List;
import java.util.Optional;

import domain.Member;
import repository.MemberRepository;
import repository.MemoryMemberRepository;

public class MemberService {
	
	private final MemberRepository repository = new MemoryMemberRepository();
	
	
	// 회원가입
	public long join (Member mem) {
		validateDuplicateMember(mem); // 같은 이름이 있는 중복 회원X
		repository.save(mem);
		return mem.getId();
	}
	
	private void validateDuplicateMember(Member mem) { // 로직 분리
		repository.findByName(mem.getName())
		.ifPresent( m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
	}
	
	// 전체 회원 조회
	public List<Member> findMembers(Member mem) {
		return repository.findAll();
	}
	
	//한명의 회원만 조희
	public Optional<Member> findOne(Long memberId){
		return repository.findById(memberId);
	
	}
	
}
