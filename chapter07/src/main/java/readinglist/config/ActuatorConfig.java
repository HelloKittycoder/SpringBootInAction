package readinglist.config;

import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import readinglist.extension.AmazonHealth;
import readinglist.extension.DemoMetrics;

/**
 * Created by shucheng on 2020/9/3 20:58
 */
@Configuration
public class ActuatorConfig {

    /**
     * 自定义度量信息
     * @return
     */
    @Bean
    public DemoMetrics demoMetrics() {
        return new DemoMetrics();
    }

    /**
     * 自定义http请求追踪
     * 自定义HttpTraceRepository，将跟踪的请求数量设置为2，默认是100
     * （还可以另外重新实现下HttpTraceRepository，如：将数据插到mysql、redis等）
     * @return
     */
    @Bean
    public InMemoryHttpTraceRepository traceRepository() {
        InMemoryHttpTraceRepository traceRepo = new InMemoryHttpTraceRepository();
        traceRepo.setCapacity(2);
        return traceRepo;
    }

    @Bean
    public AmazonHealth amazonHealth() {
        return new AmazonHealth();
    }
}
