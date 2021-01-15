package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import domain.Member;

@Repository
public interface MemberRepository {
	Member save(Member mem);
	Optional<Member> findById(Long id);
	Optional<Member> findByName(String name);
	List<Member> findAll();
}
