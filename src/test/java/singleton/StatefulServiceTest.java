package singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

//        statefulService1.order("userA", 10000);
//        statefulService2.order("userB", 20000);
        int userAPr = statefulService1.order("userA", 10000);
        int userBPr = statefulService2.order("userB", 20000);
//        int price = statefulService1.getPrice();
//        //ThreadA: 사용자A는 10000원을 기대했지만, 기대와 다르게 20000원 출력
//        System.out.println("price = " + price);
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
        System.out.println(userAPr);
        System.out.println(userBPr);
    }
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
