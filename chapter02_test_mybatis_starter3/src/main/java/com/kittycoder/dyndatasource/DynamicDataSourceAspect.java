package com.kittycoder.dyndatasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by shucheng on 2020/9/11 20:48
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 切换数据源
    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint point, TargetDataSource targetDataSource) {
        String dsId = targetDataSource.value();
        /**
         * 如果解析出来的datasourceId对应的datasource不存在，DynamicDataSourceContextHolder.get将返回null
         * 这时用的就是默认数据源
         */
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            logger.error("数据源(" + dsId + ")不存在-" + point.getSignature() + "将使用默认数据源datasource1");
        } else {
            logger.info("使用数据源(" + dsId + ")-" + point.getSignature());
            DynamicDataSourceContextHolder.setDataSourceType(targetDataSource.value());
        }
    }

    // 恢复数据源
    @After("@annotation(targetDataSource)")
    public void restoreDataSource(JoinPoint point, TargetDataSource targetDataSource) {
        logger.info("恢复数据源-" + point.getSignature());
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}
