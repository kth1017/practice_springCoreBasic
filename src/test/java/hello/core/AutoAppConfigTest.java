package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;


public class AutoAppConfigTest {

//    @Test
//    void NoUniqueScan() { // @component를 같은 추상타입의 구현체 둘에 각각 붙였을때
//        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
//        DiscountPolicy discountPolicy = ac.getBean(DiscountPolicy.class);
//        System.out.println(DiscountPolicy.class);
//        System.out.println(discountPolicy);
//    } // 예상대로 Nounique 에외뜸

    @Test
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }



}