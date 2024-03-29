package study.core.order.service;

import study.core.discount.DiscountPolicy;
import study.core.member.model.Member;
import study.core.member.repository.MemberRepository;
import study.core.order.model.Order;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository ;
    private final DiscountPolicy discountPolicy;
       public OrderServiceImpl( MemberRepository memberRepository,DiscountPolicy discountPolicy){
           this.memberRepository = memberRepository;
           this.discountPolicy = discountPolicy;
    }

    @Override
    public Order creteOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
