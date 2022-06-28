package hello.core.statictest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

public class StaticTest {

//    @Configuration
//    static class Test { // static 빼면 오류
//        @Bean
//        public TestA testA() {
//            return new TestA();
//        }
//    }

//    @org.junit.jupiter.api.Test
//    void 이너클래스Test_static() {
//        ApplicationContext ac = new AnnotationConfigApplicationContext(Test.class);
//        TestA testA = ac.getBean(TestA.class);
//        assertThat(testA).isInstanceOf(TestA.class);
//    }
    @Configuration
    static class Test {
        @Bean
        public TestB testB() {
            return new TestBimpl();
        }
    }

//인터페이스도 static에 저장
    @org.junit.jupiter.api.Test
    void 인터페이스TestB() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(Test.class);
        TestB testB = ac.getBean(TestB.class); // 인터페이스도 static
        System.out.println("testB = " + testB);
    }

    static class TestBimpl2 implements TestB { // staitic 제외시 에러
    }

    @org.junit.jupiter.api.Test
    void 구현체impl2_static() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBimpl2.class); // 인터페이스도 인자로 사용 가능
        TestBimpl2 testB = ac.getBean(TestBimpl2.class);
        System.out.println("testB = " + testB);
    }


/*  1. 이너클래스가 non-static이면 .class 사용 불가
    2. 인터페이스도 클래스기때문에 생성시 원래 static(검증은 아직)
    3. 이너클래스를 구현클래스로 만든 후 테스트해도 동일한 결과
 */
}
