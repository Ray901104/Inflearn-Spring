package springCore.basic;

import springCore.basic.discount.DiscountPolicy;
import springCore.basic.discount.FixDiscountPolicy;
import springCore.basic.member.MemberRepository;
import springCore.basic.member.MemberService;
import springCore.basic.member.MemberServiceImpl;
import springCore.basic.member.MemoryMemberRepository;
import springCore.basic.order.OrderService;
import springCore.basic.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
