package hello.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired LogRepository logRepository;

    /**
     * memberService    @Transactional: OFF
     * memberRepository @Transactional: ON
     * logRepository    @Transactional: ON
     * 회원 및 로그가 모두 저장된다.
     */
    @Test
    void outerTxOff_success() {
        //given
        String username = "outerTxOff_success";

        //when
        memberService.joinV1(username);

        //then: 모든 데이터가 정상적으로 저장된다.
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }

    /**
     * memberService    @Transactional: OFF
     * memberRepository @Transactional: ON
     * logRepository    @Transactional: ON <- 예외 발생!
     * 회원은 저장되지만, 로그는 롤백된다.
     */
    @Test
    void outerTxOff_fail() {
        //given
        String username = "로그예외_outerTxOff_fail";

        //when
        assertThatThrownBy(() -> memberService.joinV1(username))
                .isInstanceOf(RuntimeException.class);

        //then: 로그 데이터는 롤백된다.
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isEmpty());
    }

    /**
     * memberService    @Transactional: ON
     * memberRepository @Transactional: OFF
     * logRepository    @Transactional: OFF
     * 두 트랜잭션을 하나로 묶는다.
     */
    @Test
    void singleTx() {
        //given
        String username = "singleTx_success";

        //when
        memberService.joinV1(username);

        //then: 모든 데이터가 정상적으로 저장된다.
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }

    /**
     * memberService    @Transactional: ON
     * memberRepository @Transactional: ON
     * logRepository    @Transactional: ON
     * 모든 내부 트랜잭션이 정상 처리되는 케이스
     */
    @Test
    void outerTxOn_success() {
        //given
        String username = "outerTxOn_success";

        //when: 모든 데이터가 정상적으로 저장된다.
        memberService.joinV1(username);

        //then
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }

    /**
     * memberService    @Transactional: ON
     * memberRepository @Transactional: ON
     * logRepository    @Transactional: ON <- 예외발생!
     * LogRepository 내부 트랜잭션에서 예외가 발생하여 롤백하는 케이스
     */
    @Test
    void outerTxOn_fail() {
        //given
        String username = "로그예외_outerTxOn_fail";

        //when
        assertThatThrownBy(() -> memberService.joinV1(username))
                .isInstanceOf(RuntimeException.class);

        //then: 모든 데이터가 롤백된다.
        assertTrue(memberRepository.find(username).isEmpty());
        assertTrue(logRepository.find(username).isEmpty());
    }

    /**
     * memberService    @Transactional: ON
     * memberRepository @Transactional: ON
     * logRepository    @Transactional: ON <- 예외발생!
     *
     * LogRepository 내부 트랜잭션에서 예외가 발생했으나, service 단에서 이 예외를 catch
     * 정상흐름이 반환되어 멤버는 저장될 것으로 생각할 수 있으나, rollback-only 마크로 인하여 물리 트랜잭션이 롤백된다.
     */
    @Test
    void recoverException_fail() {
        //given
        String username = "로그예외_recoverException_fail";

        //when
        assertThatThrownBy(() -> memberService.joinV2(username))
                .isInstanceOf(UnexpectedRollbackException.class);

        //then: 모든 데이터가 롤백된다.
        assertTrue(memberRepository.find(username).isEmpty());
        assertTrue(logRepository.find(username).isEmpty());
    }

    /**
     * memberService    @Transactional: ON
     * memberRepository @Transactional: ON
     * logRepository    @Transactional: ON(REQUIRES_NEW) <- 예외발생!
     *
     * LogRepository의 propagation 옵션을 REQUIRES_NEW로 변경 -> 별도의 트랜잭션 생성
     */
    @Test
    void recoverException_success() {
        //given
        String username = "로그예외_recoverException_success";

        //when
        memberService.joinV2(username);

        //then: member 저장, log 롤백
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isEmpty());
    }
}
