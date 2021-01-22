package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import domain.Member;

public class JdbcTemplateMemberRepository implements MemberRepository{

	private final JdbcTemplate jdbcTemplate;
	
	public JdbcTemplateMemberRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	} // 생성자가 하나만 있으면 AutoWired 생략 가능
	
	@Override
	public Member save(Member mem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Member> findById(Long id) {
		// TODO Auto-generated method stub
		List<Member> result =  jdbcTemplate.query("select * from member where id = ?", memberRowMapper());
		return result.stream().findAny();
	}

	@Override
	public Optional<Member> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	

	private RowMapper<Member> memberRowMapper() {
		return (rs, rowNum) -> {
			Member member = new Member();
			member.setId(rs.getLong("id"));
			member.setName(rs.getString("name"));
			return member;
		};
	}
	
	
}
