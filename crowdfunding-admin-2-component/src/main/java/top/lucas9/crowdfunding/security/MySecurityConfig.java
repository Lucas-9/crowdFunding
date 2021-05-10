package top.lucas9.crowdfunding.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

//    使用 BCryptPasswordEncoder 加密
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//    @Bean
//    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
//        builder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .authorizeRequests()
                .antMatchers("/index.html", "/bootstrap/**", "/css/**", "/fonts/**", "/img/**", "/jquery/**", "/layer/**", "/script/**", "/ztree/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/admin/login/page.html")
                .permitAll()
                .loginProcessingUrl("/admin/login/security.html")
                .usernameParameter("loginAccount")
                .passwordParameter("userPassword")
                .defaultSuccessUrl("/admin/main/page.html")
                .and()
                .logout()
                .logoutUrl("/admin/security/logout.html")
                .logoutSuccessUrl("/index.html")
                .and()
                .csrf()
                .disable();	// 禁用CSRF功能

    }
}
