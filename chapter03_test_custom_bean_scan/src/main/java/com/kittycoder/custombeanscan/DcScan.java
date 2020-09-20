package com.kittycoder.custombeanscan;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by shucheng on 2020/9/20 14:00
 *
 * 说明：如果想另外用注解（如：DcClass）标记单个接口，然后添加自己的特殊逻辑的话；
 * 注意在DcClass上面不能再加@Component，spring默认的几个注解（Component,Repository,Service,Controller，
 * 这几个会优先扫描，详细见ClassPathBeanDefinitionScanner）标记的类被扫描过以后，就不会把自己定义的扫描逻辑
 * （即使扫描的条件都满足）扫描到
 *
 * 目前是DcScan批量对某个包下的接口都进行扫描，没有写DcClass来对指定接口进行扫描，这个后续可以模仿mybatis的@Mapper来做下
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(DcScannerRegistrar.class)
public @interface DcScan {
    // 需要进行代理的包路径
    String[] value() default {};
}
