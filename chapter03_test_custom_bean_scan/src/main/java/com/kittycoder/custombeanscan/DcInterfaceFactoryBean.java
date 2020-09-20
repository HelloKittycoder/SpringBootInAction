package com.kittycoder.custombeanscan;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * 工厂bean（可以理解为是生成bean的模板），用来生成接口代理类的
 * 因为这里使用的是jdk动态代理，所以必须要声明接口，普通的类是无法进行代理的
 * Created by shucheng on 2020/9/20 13:59
 */
public class DcInterfaceFactoryBean<T> implements FactoryBean<T> {

    private Class<T> dcInterface;

    /**
     * 因为DcScannerRegistrar已经设置好了构造器参数，以及factoryBean，
     * 这样就相当于指定要用DcInterfaceFactoryBean(Class<T> dcInterface)这个构造器来创建实例，
     * 下面的这个无参构造就显得没有必要了
     */
    /*public DcInterfaceFactoryBean() {
    }*/

    public DcInterfaceFactoryBean(Class<T> dcInterface) {
        this.dcInterface = dcInterface;
    }

    @Override
    public T getObject() throws Exception {
        DcProxy dcProxy = new DcProxy(dcInterface);
        return (T) Proxy.newProxyInstance(dcInterface.getClassLoader(), new Class[] {dcInterface}, dcProxy);
    }

    @Override
    public Class<?> getObjectType() {
        return this.dcInterface;
    }
}
