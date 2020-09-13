package com.kittycoder.dyndatasource;

import java.lang.annotation.*;

/**
 * 标识要使用的数据源
 * Created by shucheng on 2020/9/11 20:46
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String value() default "datasource1";
}
