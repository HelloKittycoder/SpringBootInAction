package readinglist.extension;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shucheng on 2020/9/3 9:15
 * 自定义度量信息
 *
 * 自定义Metrics（这个还不太会用，后续再看下）
 * 参考链接：https://www.cnblogs.com/april-chen/p/10529923.html
 * https://blog.csdn.net/myherux/article/details/81781199
 *
 * 访问 http://localhost:8080/actuator/metrics 能查看所有可用指标
 * http://localhost:8080/actuator/metrics/demo.count 查看指定指标的值
 */
public class DemoMetrics implements MeterBinder {
    private AtomicInteger count = new AtomicInteger(0);

    @Autowired
    private ApplicationContext ctx;

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        Gauge.builder("demo.count", count, c -> c.incrementAndGet())
                .tags("host", "localhost")
                .description("demo of custom meter binder")
                .register(meterRegistry);

        Gauge.builder("spring.context.startup-date", ctx::getStartupDate)
                .tags("host", "localhost")
                .description("startup date of spring context")
                .register(meterRegistry);

        Gauge.builder("spring.beans.definitions", ctx::getBeanDefinitionCount)
                .tags("host", "localhost")
                .description("spring bean definition count")
                .register(meterRegistry);

        Gauge.builder("spring.beans", () -> ctx.getBeanNamesForType(Object.class).length)
                .tags("host", "localhost")
                .description("spring beans count")
                .register(meterRegistry);

        Gauge.builder("spring.controllers", () -> ctx.getBeanNamesForAnnotation(Controller.class).length)
                .tags("host", "localhost")
                .description("spring controllers count")
                .register(meterRegistry);
    }
}
