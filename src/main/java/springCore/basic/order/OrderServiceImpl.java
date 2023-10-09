package springCore.basic.order;

import springCore.basic.discount.DiscountPolicy;
import springCore.basic.discount.FixDiscountPolicy;
import springCore.basic.member.Member;
import springCore.basic.member.MemberRepository;
import springCore.basic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
