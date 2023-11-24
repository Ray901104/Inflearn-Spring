package hello.jdbc.repository;

import hello.jdbc.domain.Member;

import java.sql.SQLException;

/**
 * 구현체에서 체크 예외를 던지는 메서드를 구현하려면 인터페이스에도 던지도록 선언되어야 한다.
 * 인터페이스 마저도 특정 기술에 종속되어 버린다.
 */
public interface MemberRepositoryEx {
    Member save(Member member) throws SQLException;
    Member findById(String memberId) throws SQLException;
    void update(String memberId, int money) throws SQLException;
    void delete(String memberId) throws SQLException;
}
