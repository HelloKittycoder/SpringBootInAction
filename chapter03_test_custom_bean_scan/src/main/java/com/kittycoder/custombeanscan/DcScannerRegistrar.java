package com.kittycoder.custombeanscan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by shucheng on 2020/9/20 14:00
 * 这个是模仿mybatis的MapperScannerRegistrar来写的
 */
public class DcScannerRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes dcScanAttrs = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(DcScan.class.getName()));
        if (dcScanAttrs != null) {
            registerBeanDefinitions(dcScanAttrs, registry);
        }
    }

    void registerBeanDefinitions(AnnotationAttributes annoAttrs, BeanDefinitionRegistry registry) {
        ClassPathDcScanner scanner = new ClassPathDcScanner(registry);
        scanner.registerFilters();
        scanner.doScan(annoAttrs.getStringArray("value"));
    }

    public static class ClassPathDcScanner extends ClassPathBeanDefinitionScanner {

        /**
         * 原本mybatis中这里定义这个MapperFactoryBean的属性，是为了让用户可以自己定制MapperFactoryBean，
         * 这里只是为了简单模拟自定义扫描的过程，不需要弄这么复杂，所以把这个属性给去掉了
         */
        // private DcInterfaceFactoryBean<?> dcInterfaceFactoryBean = new DcInterfaceFactoryBean<>();
        private static final Logger logger = LoggerFactory.getLogger(ClassPathDcScanner.class);

        public ClassPathDcScanner(BeanDefinitionRegistry registry) {
            super(registry, false);
        }

        @Override
        protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
            Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
            if (beanDefinitions.isEmpty()) {
                // 没有扫描到需要进行代理的接口
                logger.warn("No DataCenter interface was found in '{}' package. Please check your configuration.",
                        Arrays.toString(basePackages));
            } else {
                processBeanDefinitions(beanDefinitions);
            }
            return beanDefinitions;
        }

        private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
            GenericBeanDefinition definition;
            for (BeanDefinitionHolder holder : beanDefinitions) {
                definition = (GenericBeanDefinition) holder.getBeanDefinition();
                String beanClassName = definition.getBeanClassName();
                logger.debug("Creating WindInterfaceFactoryBean with name '{}' and '{}' windInterface",
                        holder.getBeanName(), beanClassName);

                definition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
                // definition.setBeanClass(this.dcInterfaceFactoryBean.getClass());
                definition.setBeanClass(DcInterfaceFactoryBean.class);
            }
        }

        public void registerFilters() {
            // 这里表示不添加任何限制，只要是指定包下的类都会被扫描出来
            addIncludeFilter(((metadataReader, metadataReaderFactory) -> true));
            // 如果要断点调试过滤器中间的过滤过程的话，写成下面的形式会更好点
            /*addIncludeFilter(((metadataReader, metadataReaderFactory) -> {
                System.out.println(metadataReader + "==================" + metadataReaderFactory);
                return true;
            }));*/
        }

        /**
         * 这段很关键，不加的话，无法扫描到接口（参考mybatis的ClassPathMapperScanner里写的）
         * 因为ClassPathBeanDefinitionScanner的候选bean策略是不会把接口纳入bean扫描的范围，这里需要自己定制
         */
        @Override
        protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
            return beanDefinition.getMetadata().isIndependent() && beanDefinition.getMetadata().isIndependent();
        }
    }
}
