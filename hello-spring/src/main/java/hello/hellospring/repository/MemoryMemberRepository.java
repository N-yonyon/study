package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //sequence:키값을 생성해줌

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //member에 Id값을 set해줌 //Id값을 set할 때 sequence값을 하나씩 올려줌
        store.put(member.getId(), member); //set한 값을 store에 저장
        return member; //저장된 결과 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //Optional.ofNullable:id값이 null이어도 감싸서 반환해줌
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //getName이 파라메터에서 가져온 name과 같은지 확인하고 같으면 필터링하고
                .findAny(); //그 중에서 찾으면 그대로 반환함
        //findAny의 결과는 Optional로 반환됨
        //맵에서 루프를 돌면서 찾아지면 그 값을 반환하고 끝까지 돌렸는데도 없으면 null이 포함되어 반환함
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store에 있는 values는 14행에 Member
    }
}
