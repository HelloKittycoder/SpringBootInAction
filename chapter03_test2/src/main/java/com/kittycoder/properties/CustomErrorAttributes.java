package com.kittycoder.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * Created by shucheng on 2020/8/31 9:16
 */
@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    /*@Autowired
    private ServerProperties serverProperties;*/

    // 因为这里用的不是默认提供的DefaultErrorAttributes，这里需要另外再写个构造器，
    // 把配置文件的属性给注入进去
    // 可参看：ErrorMvcAutoConfiguration#errorAttributes，DefaultErrorAttributes#includeException
    public CustomErrorAttributes(@Value("${server.error.include-exception}")boolean includeException) {
        super(includeException);
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> attributes = super.getErrorAttributes(webRequest, includeStackTrace);
        attributes.put("foo", "bar");
        // System.out.println("isIncludeException====" + serverProperties.getError().isIncludeException());
        return attributes;
    }
}
