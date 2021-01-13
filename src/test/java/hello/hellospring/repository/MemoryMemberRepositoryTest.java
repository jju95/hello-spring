package hello.hellospring.repository;

import org.apache.logging.log4j.core.Logger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import domain.Member;
import repository.MemoryMemberRepository;

public class MemoryMemberRepositoryTest {

	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	@Test
	public void save() {
		Member mem = new Member();
		mem.setName("희주");
		repository.save(mem);
		
		Member result = repository.findById(mem.getId()).get();
		//System.out.println(" result = "+(result == mem));
		Assertions.assertThat(mem).isEqualTo(result);
	}
	
}
