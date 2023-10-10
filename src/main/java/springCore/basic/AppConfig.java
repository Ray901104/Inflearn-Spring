package springCore.basic;

import springCore.basic.discount.FixDiscountPolicy;
import springCore.basic.member.MemberService;
import springCore.basic.member.MemberServiceImpl;
import springCore.basic.member.MemoryMemberRepository;
import springCore.basic.order.OrderService;
import springCore.basic.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
