package com.kittycoder.condition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by shucheng on 2020/8/20 9:52
 */
public class StudentCondition implements Condition {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        logger.info("调用matches方法");
        return true;
    }
}
