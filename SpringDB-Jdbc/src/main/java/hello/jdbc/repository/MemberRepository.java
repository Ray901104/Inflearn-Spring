package hello.jdbc.repository;

import hello.jdbc.domain.Member;

/**
 * 특정 기술에 종속되지 않은 인터페이스
 * 사용 기술이 변경되어도 (JDBC -> JPA) 서비스는 해당 인터페이스에만 의존하기 때문에 로직 변경을 하지 않아도 된다.
 */
public interface MemberRepository {
    Member save(Member member);
    Member findById(String memberId);
    void update(String memberId, int money);
    void delete(String memberId);
}
