package com.example.board.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Log4j2
@RequiredArgsConstructor
 public class CustomSecurityConfig {
//1. config만 있을 때 모든 사용자에 대해서 필터 처리 로그인을 요구함
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // 2. SecurityFilterChain 객체를 반환하는 메소드를 생성 시 아무 설정 없을시 모두가 접근할 수 있도록 해줌
        // 어떤 유저가 접근하고 어디까지 접근해야할지 내가 정해줘야 함


                // 람다로 고쳐서 써야함

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/blog/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll()
                )
                .rememberMe(Customizer.withDefaults());

        log.info("-----------config----------");
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        //PasswordEncoder 인터페이스를 구현하거나 스프링 시큐리티 api에서 제공하는 클래스를 지정해야한다.
        // BCryptPasswordEncoder는 해쉬 알고리즘으로 암호화 처리해주는 클래스
        return new BCryptPasswordEncoder();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){

        // 정적 자원까지 필터가 처리 되고 있어  정적 자원들의 부분을 필터가 작동하지 않게 해준다.

        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

}
