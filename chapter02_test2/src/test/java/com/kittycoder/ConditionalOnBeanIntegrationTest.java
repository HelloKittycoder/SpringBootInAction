package com.kittycoder;

import org.junit.Test;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by shucheng on 2020/8/22 20:15
 * 参考tutorials（https://github.com/HelloKittycoder/tutorials）中spring-boot-autoconfiguration的写法
 */
public class ConditionalOnBeanIntegrationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

    @Test
    public void whenDependentBeanIsPresent_thenConditionalBeanCreated() {
        this.contextRunner.withUserConfiguration(BasicConfiguration.class, ConditionalOnBeanConfiguration.class)
            .run(context -> {
                assertThat(context).hasBean("created")
                        .getBean("created").isEqualTo("This is always created");
                assertThat(context).hasBean("createOnBean")
                        .getBean("createOnBean").isEqualTo("This is created when bean (name=created) is present");
            });
    }

    @Test
    public void whenDependentBeanIsPresent_thenConditionalMissingBeanIgnored() {
        this.contextRunner.withUserConfiguration(BasicConfiguration.class, ConditionalOnMissingBeanConfiguration.class)
            .run(context -> {
                assertThat(context).hasBean("created")
                        .getBean("created").isEqualTo("This is always created");
                assertThat(context).doesNotHaveBean("createOnMissingBean");
            });
    }

    @Test
    public void whenDependentBeanIsNotPresent_thenConditionalMissingBeanCreated() {
        this.contextRunner.withUserConfiguration(ConditionalOnMissingBeanConfiguration.class)
                .run(context -> {
                    assertThat(context).doesNotHaveBean("created");
                    assertThat(context).hasBean("createOnMissingBean")
                            .getBean("createOnMissingBean").isEqualTo("This is created when bean (name=created) is missing");
                });
    }

    @Configuration
    public static class BasicConfiguration {
        @Bean
        public String created() {
            return "This is always created";
        }
    }

    @Configuration
    @ConditionalOnBean(name = "created")
    public static class ConditionalOnBeanConfiguration {
        @Bean
        public String createOnBean() {
            return "This is created when bean (name=created) is present";
        }
    }

    @Configuration
    @ConditionalOnMissingBean(name = "created")
    public static class ConditionalOnMissingBeanConfiguration {
        @Bean
        public String createOnMissingBean() {
            return "This is created when bean (name=created) is missing";
        }
    }
}
