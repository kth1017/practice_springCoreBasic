package singleton;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {
        @Test
        void 비교() {

            //1. 조회: 호출할 때 마다 같은 객체를 반환
            SingletonService singletonService1 = SingletonService.getInstance();
            //2. 조회: 호출할 때 마다 같은 객체를 반환
            SingletonService singletonService2 = SingletonService.getInstance();
            //참조값이 같은 것을 확인
            System.out.println("singletonService1 = " + singletonService1);
            System.out.println("singletonService2 = " + singletonService2);
            // singletonService1 == singletonService2
            assertThat(singletonService1).isSameAs(singletonService2);
        }

}
