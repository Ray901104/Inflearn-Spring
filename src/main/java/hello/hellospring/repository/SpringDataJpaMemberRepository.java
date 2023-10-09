package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // 공통 인터페이스로 뺄 수 없는 것들을 만드려면 메소드를 규칙에 따라 만들면 된다.
    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
