package springCore.basic.discount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import springCore.basic.member.Grade;
import springCore.basic.member.Member;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o() {
        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        // when
        int discountPrice = rateDiscountPolicy.discount(member, 10000);

        // then
        assertThat(discountPrice).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x() {
        // given
        Member member = new Member(1L, "memberBASIC", Grade.BASIC);

        // when
        int discountPrice = rateDiscountPolicy.discount(member, 10000);

        // then
        assertThat(discountPrice).isEqualTo(0);
    }
}