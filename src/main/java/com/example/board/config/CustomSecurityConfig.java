package com.example.board.config;


import com.example.board.handler.Custom403Handler;
import com.example.board.handler.CustomSocialLoginSuccessHandler;
import com.example.board.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Log4j2
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {
    //remember_me 기능을 사용하기 위해 정해진 테이블 생성 후 데이터소스 관련 필요.
    //쿠키를 사용하기 위해 데이터 베이스를 다루기 위해 DataSource UserDetailService객체가 필요함
    private final DataSource dataSource;
    private final CustomUserDetailService customUserDetailService;

    //1. config만 있을 때 모든 사용자에 대해서 필터 처리 로그인(인증)을 요구함
    // 2. SecurityFilterChain 객체를 반환하는 메소드를 생성 시 아무 설정 없을시 모두가 접근할 수 있도록 해줌
    // 어떤 유저가 접근하고 어디까지 접근해야할지 내가 정해줘야 함
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 람다로 고쳐서 써야함
        // authorizeHttpRequests 람다식으로 표현 6.1   버전이 이후로 (). 연결식으로 쓰는 방식이 폐지됨.
        // requestMatchers 로 url 어떤 요청에 hasRole로 누가 접근할 수 있는지 설정함
//        http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
//                .requestMatchers("/board/register").authenticated());
//        http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
//                .requestMatchers("/member/login").permitAll()
//        ).formLogin(formLogin -> formLogin.loginPage("/member/login"));


        //csrf토큰 get 외 방식으로 요청시 csrf토큰을 필요로 함 각 코드를 수정해야하므로 일단 사용하지 않기로 한다.
        http.csrf(csrf -> csrf.disable());

        http.formLogin(formLogin->formLogin.loginPage("/member/login").defaultSuccessUrl("/board/list"));
        //post 방식에 대해서 구현한 것이 없으나 Spring security 내부에서 처리 됨. html 버튼 post
        http.oauth2Login(loginPage -> loginPage.loginPage("/member/login").successHandler(authenticationSuccessHandler()));

        http.rememberMe(rememberMe -> rememberMe
                .key("12345678")
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(customUserDetailService)
                .tokenValiditySeconds(60*60*24*10));

        http.exceptionHandling(exceptionHandling->exceptionHandling.accessDeniedHandler(accessDeniedHandler()));

        //http.formLogin( formLogin -> formLogin.loginPage("member/login"));
        //loginPage를 지정하면 로그인이 필요한 경우에 자동으로 redirect 됨.

//        http.csrf(csrf -> csrf.disable());
        log.info("-----------config----------");
        return http.build();


//        http 공식레퍼런스 부분
//                .authorizeHttpRequests((authorizeHttpRequests) ->
//                        authorizeHttpRequests
//                                .requestMatchers("/admin/**").hasRole("ADMIN")
//                                .requestMatchers("/**").hasRole("USER")
//                )
        //                        .formLogin(withDefaults());
        //                return http.build();


    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //PasswordEncoder 인터페이스를 구현하거나 스프링 시큐리티 api에서 제공하는 클래스를 지정해야한다.
        // BCryptPasswordEncoder는 해쉬 알고리즘으로 암호화 처리해주는 클래스
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        // 정적 자원까지 필터가 처리 되고 있어  정적 자원들의 부분을 필터가 작동하지 않게 해준다.

        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return  new Custom403Handler();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new CustomSocialLoginSuccessHandler(passwordEncoder());
    }

}
