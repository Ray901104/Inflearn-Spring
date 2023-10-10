package springCore.basic;

import springCore.basic.member.Grade;
import springCore.basic.member.Member;
import springCore.basic.member.MemberService;
import springCore.basic.member.MemberServiceImpl;
import springCore.basic.order.Order;
import springCore.basic.order.OrderService;
import springCore.basic.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("itemA = " + order);
    }
}
