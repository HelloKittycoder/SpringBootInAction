package com.kittycoder.custombeanscan;

import com.kittycoder.datacenter.common.PageInfo;
import com.kittycoder.datacenter.common.ResponseEntity;
import com.kittycoder.datacenter.dcdata.DcDataSource;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * 代理类，给被代理的接口添加额外的逻辑
 * 在这里就是，获取接口方法上必要的参数，然后再去调用别人提供的对外接口（这里用DcDataSource简单模拟下）
 * Created by shucheng on 2020/9/20 13:59
 */
public class DcProxy<D> implements InvocationHandler, Serializable {

    private final Class<D> dcInterface;

    public DcProxy(Class<D> dcInterface) {
        this.dcInterface = dcInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // System.out.println("调用DcProxy代理方法");
        // 如果是Object中的方法，则直接放行
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }

        // 获取方法返回值的泛型参数
        ParameterizedType genericReturnType = (ParameterizedType) method.getGenericReturnType();
        Class<?> rawType = (Class<?>) genericReturnType.getRawType();// ResponseEntity
        Class<?> actualTypeArgument = (Class<?>) genericReturnType.getActualTypeArguments()[0];// Student

        // 获取dcType
        DcRequest dcRequest = method.getAnnotation(DcRequest.class);
        if (dcRequest == null) {
            throw new RuntimeException("接口" + dcInterface + "的" + method.getName() + "方法上缺少DcRequest注解");
        }
        String dcType = dcRequest.value();

        Object result;
        if (ResponseEntity.class.equals(rawType)) { // 查询单个对象的数据
            result = DcDataSource.getData(dcType, actualTypeArgument);
        } else if (PageInfo.class.equals(rawType)) { // 查询带分页的数据
            result = DcDataSource.getList(dcType, actualTypeArgument);
        } else {
            throw new RuntimeException("接口方法声明不符合要求：接口返回值必须为ResponseEntity或PageInfo");
        }
        return result;
    }
}
