//package com.example.demo.configuration;
//
//import com.example.demo.ajax.*;
//import com.example.demo.security.filter.AjaxLoginProcessingFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@Order(0)
//public class AjaxSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(ajaxAuthenticationProvider());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .antMatcher("/api/**")
//                .authorizeRequests()
//                .antMatchers("/api/messages").hasRole("MANAGER")
//                .antMatchers("/api/login").permitAll()
//                .anyRequest().authenticated();
////        http
////                .addFilterBefore(ajaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        http
//                .exceptionHandling()
//                .accessDeniedHandler(ajaxAccessDeniedHandler())
//                .authenticationEntryPoint(new AjaxLoginAuthenticationEntryPoint());
//
//
//        customConfigurerAjax(http);
//
//    }
//
//    private void customConfigurerAjax(HttpSecurity http) throws Exception {
//        http
//                .apply(new AjaxLoginConfigurer<>())
//                .successHandlerAjax(authenticationSuccessHandler())
//                .failureHandlerAjax(authenticationFailureHandler())
//                .setAuthenticationManager(authenticationManagerBean())
//                .loginProcessingUrl("/api/login");
//    }
//
//    @Bean
//    public AccessDeniedHandler ajaxAccessDeniedHandler() {
//        return new AjaxAccessDeniedHandler();
//    }
//
//    @Bean
//    AjaxAuthenticationProvider ajaxAuthenticationProvider(){
//        return new AjaxAuthenticationProvider();
//    }
//
////    @Bean
////    public AjaxLoginProcessingFilter ajaxLoginProcessingFilter() throws Exception {
////        AjaxLoginProcessingFilter ajaxLoginProcessingFilter = new AjaxLoginProcessingFilter();
////        ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManagerBean());
////        ajaxLoginProcessingFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
////        ajaxLoginProcessingFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
////        return ajaxLoginProcessingFilter;
////    }
//
//    @Bean
//    public AuthenticationSuccessHandler authenticationSuccessHandler(){
//        return new AjaxSuccessHandler();
//    }
//
//    @Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        return new AjaxAuthenticationFailure();
//    }
//}
