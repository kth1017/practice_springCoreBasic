package hello.core.scope;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {
    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new
                AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        System.out.println("1번 컨테이너" + ac);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);
        System.out.println("프로토1 " + clientBean1);
        ClientBean clientBean2 = ac.getBean(ClientBean.class); // 1과 동일한 싱글톤 빈을 주입받음
        int count2 = clientBean2.logic();  // 로직안에선 각각의 프로토타입빈이 생성됨
        assertThat(count2).isEqualTo(1);
        System.out.println("프로토2 " + clientBean2); // cli1 == cli2

    }
    static class ClientBean {
//        private final PrototypeBean prototypeBean;
//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) { // 이경우 생성시점에서 싱글톤, 프로토타입 각 1번만 생성 > *15 DL
//            this.prototypeBean = prototypeBean;
//        }
        @Autowired
        ApplicationContext applicationContext;


        public int logic() {
            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
            System.out.println("2번 컨테이너 " + applicationContext); // 1번과 같은 컨테이너를 공유함
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }
    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;
        public void addCount() {
            count++;
        }
        public int getCount() {
            return count;
        }
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}

