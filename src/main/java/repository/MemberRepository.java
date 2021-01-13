package repository;

import java.util.List;
import java.util.Optional;

import domain.Member;

public interface MemberRepository {
	Member save(Member mem);
	Optional<Member> findById(Long id);
	Optional<Member> findByName(String name);
	List<Member> findAll();
}
