package com.kittycoder.dyndatasource;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 事务处理配置
 * 参考链接：https://blog.csdn.net/qq_34491508/article/details/110056638
 *
 * 这种是java配置的写法，其实等效于 attach/spring-service.xml（只是作为参考，里面没有完全对应上） 中的写法
 *
 * TODO：单数据源下能正常使用；在多数据源的情况下，会导致切换数据源有问题，有空再看看
 *
 * @author shucheng
 * @date 2022/7/5 22:06
 */
// @Configuration
public class TransactionAdviceConfig {

    public static final String AOP_POINTCUT_EXPRESSION = "execution(* com.kittycoder..*.service..*.*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {
        DefaultTransactionAttribute defaultTxAttr = new DefaultTransactionAttribute();
        defaultTxAttr.setReadOnly(true);
        defaultTxAttr.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);

        DefaultTransactionAttribute txAttrRequired = new DefaultTransactionAttribute();
        txAttrRequired.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttrRequired.rollbackOn(new Exception());

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("add*", txAttrRequired);
        source.addTransactionalMethod("save*", txAttrRequired);
        source.addTransactionalMethod("insert*", txAttrRequired);
        source.addTransactionalMethod("update*", txAttrRequired);
        source.addTransactionalMethod("delete*", txAttrRequired);
        source.addTransactionalMethod("get*", defaultTxAttr);
        source.addTransactionalMethod("query*", defaultTxAttr);
        source.addTransactionalMethod("select*", defaultTxAttr);
        source.addTransactionalMethod("*", defaultTxAttr);
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
