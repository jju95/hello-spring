package hello.hellospring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.repository.jdbcMemberRepository;
import hello.hellospring.service.MemberService;
import lombok.Data;

@Configuration // 스프링 컨피그
public class SpringConfig {
 	
	DataSource dataSource;
	
	@Autowired
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean 
	public MemberRepository memberRepository() {
		//return new MemoryMemberRepository();
		return new jdbcMemberRepository(dataSource);
	}
	
}
