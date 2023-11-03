package springCore.basic.order;

import org.junit.jupiter.api.Test;
import springCore.basic.discount.FixDiscountPolicy;
import springCore.basic.member.Grade;
import springCore.basic.member.Member;
import springCore.basic.member.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        memoryMemberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memoryMemberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1l, "itemA", 10000);

        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}