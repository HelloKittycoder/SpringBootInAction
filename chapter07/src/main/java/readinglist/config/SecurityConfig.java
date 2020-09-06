package readinglist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import readinglist.repository.ReaderRepository;

/**
 * Created by shucheng on 2020/9/1 9:31
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 情形一：下面是完全不用任何安全验证的写法
     * 去掉spring security的安全验证（不然无法直接调用/actuator/shutdown）
     */
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll()
            .and().csrf().disable();
    }*/

    /**
     * 情形二：需要将端点保护起来（P136）
     */
    @Autowired
    private ReaderRepository readerRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").access("hasRole('READER')")
                .antMatchers("/actuator/shutdown").access("hasRole('ADMIN')")
                .antMatchers("/**").permitAll()
            .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
            .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UserDetails user = readerRepository.findById(username).orElse(null);
                if (user != null) {
                    return user;
                }
                throw new UsernameNotFoundException("User '" + username + "' not found.");
            }
        })
        // 添加一个内存里的admin用户
        .and()
        .inMemoryAuthentication()
        .withUser("admin").password("1")
        .roles("ADMIN", "READER");
    }

    @Component
    public static class MyPasswordEncoder implements PasswordEncoder {
        @Override
        public String encode(CharSequence rawPassword) {
            return rawPassword.toString();
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return encodedPassword.equals(rawPassword);
        }
    }
}
