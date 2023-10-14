package springCore.basic.discount;

import org.springframework.stereotype.Component;
import springCore.basic.annotation.MainDiscountPolicy;
import springCore.basic.member.Grade;
import springCore.basic.member.Member;

@Component
//@Qualifier("fixDiscountPolicy")
@MainDiscountPolicy
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
