package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import domain.Member;

public class MemoryMemberRepository implements MemberRepository {

	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;
	
	@Override
	public Member save(Member mem) {
		// TODO Auto-generated method stub
		mem.setId(++sequence);
		store.put(mem.getId(), mem);
		return mem;
	}

	@Override
	public Optional<Member> findById(Long id) {
		// TODO Auto-generated method stub
		//return store.get(id);
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		// TODO Auto-generated method stub
		// 람다식
		return store.values().stream()
		.filter(mem -> mem.getName().equals(name))
		.findAny();
	}

	@Override
	public List<Member> findAll() {
		// TODO Auto-generated method stub
		return new ArrayList<>(store.values());
	}
	
	public void clearStore() {
		store.clear();
	}
}
