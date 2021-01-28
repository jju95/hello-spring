package hello.hellospring.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import domain.Member;

public class JdbcTemplateMemberRepository implements MemberRepository{

	private final JdbcTemplate jdbcTemplate;
	
	public JdbcTemplateMemberRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource); 
	} // 생성자가 하나만 있으면 AutoWired 생략 가능
	
	@Override
	public Member save(Member mem) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", mem.getName());
		
		Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
		mem.setId(key.longValue());

		return mem;
	}

	@Override
	public Optional<Member> findById(Long id) {
		// TODO Auto-generated method stub
		List<Member> result =  jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
		return result.stream().findAny();
	}

	@Override
	public Optional<Member> findByName(String name) {
		// TODO Auto-generated method stub
		List<Member> result =  jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
		return result.stream().findAny();
	} 

	@Override
	public List<Member> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from member", memberRowMapper());
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
