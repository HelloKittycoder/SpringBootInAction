package readinglist.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by shucheng on 2020/8/30 12:01
 * spring-security 5必须要自定义一个PasswordEncoder，不然默认不会以明文进行密码匹配
 * 参考链接：https://blog.csdn.net/weixin_39220472/article/details/80865411
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(rawPassword);
    }
}
