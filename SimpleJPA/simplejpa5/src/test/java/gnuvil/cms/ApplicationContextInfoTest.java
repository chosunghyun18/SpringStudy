package gnuvil.cms;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);


    @Test
    @DisplayName("show all bean")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); // 스프링에 등록된 모든 빈 이름을 조회한다.
        for(String beanDefinitionName:beanDefinitionNames){
            Object bean = ac.getBean(beanDefinitionName);

            assertNotNull(bean);
            System.out.println("bean : "+bean + " BeanName : "+beanDefinitionName);
            // Assert that the name of the bean matches the bean definition name
            assertTrue(ac.getBean(beanDefinitionName) == bean);

        }
    }
    @Test
    @DisplayName("show Application Bean")
    void findAppBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDefinitionName:beanDefinitionNames){
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈

            // Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name : "+beanDefinitionName +" object :"+bean);
            }

        }

    }
}
