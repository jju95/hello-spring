package hello.hellospring.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemoryMemberRepositoryTest { // 소스 관리에 대해서 엄청 중요함

	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	@AfterEach // callback 메소드
	public void afterEach() {
		repository.clearStore(); 
		// 데이터 의존성에 대하여 없애기 위해 
		// 공용데이터 클리어
	}
	
	@Test
	public void save() {
		Member mem = new Member();
		mem.setName("희주");
		repository.save(mem);
		
		Member result = repository.findById(mem.getId()).get();
		//System.out.println(" result = "+(result == mem));
		//org.junit.jupiter.api.Assertions.assertEquals(mem, null);
		Assertions.assertThat(mem).isEqualTo(result); // 
	}
	
	@Test
	public void findByName() {
		Member mem = new Member();
		mem.setName("heeju");
		repository.save(mem);
		
		Member mem2 = new Member();
		mem2.setName("heeju 2");
		repository.save(mem2);
		
		Member result = repository.findByName("heeju").get();
		Assertions.assertThat(result).isEqualTo(mem);
	}
	
	@Test
	public void findAll() {
		Member mem = new Member();
		mem.setName("heeju");
		repository.save(mem);
		
		Member mem2 = new Member();
		mem2.setName("heeju 2");
		repository.save(mem2);
		
		List<Member> result = repository.findAll();
		Assertions.assertThat(result.size()).isEqualTo(2);
	}
}