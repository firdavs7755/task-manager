package uz.firdavs.taskmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    AuthenticationCheck authenticationCheck;
    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()
                .antMatchers("/auth/one").permitAll()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/add").permitAll()
                .antMatchers("/auth/check").permitAll()
                .anyRequest().authenticated();


        http
                .cors()
                .and()
                .addFilterAfter(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
        /*http.cors().and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/favicon.ico/**").permitAll()
                .antMatchers("/v3/api-docs/**").permitAll()
                .antMatchers("/auth/one").permitAll()
                .antMatchers("/auth/two").permitAll()
                .antMatchers("/auth/add").permitAll()
                .antMatchers("/auth/check").permitAll()
                .antMatchers("/auth/login").permitAll()
                .anyRequest().authenticated();

        http.csrf().disable();

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//shu yozilmasa re-run qilingandayam tokenni saqlab qolaveradi.(intellij restart qilinsa ham).
        return http.build();*/
    }


}


//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.SecurityBuilder;
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.web.cors.CorsConfiguration;
//
//import static org.springframework.security.config.Customizer.withDefaults;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig  {

//    @Autowired
//    JwtService jwtService;
//    @Autowired
//    JwtRequestFilter jwtRequestFilter;

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
//         http
//                .authorizeRequests()
//                .antMatchers("/user/one").permitAll()
//                .antMatchers("/user/login").permitAll()
//                .anyRequest().authenticated();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()
                .antMatchers("/user/one").permitAll()
                .antMatchers("/user/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .addFilterAfter(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }*/
//    @Bean
//    public static PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    JwtRequestFilter jwtAuthenticationFilter(){
//        return new JwtRequestFilter();
//    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.DELETE)
//                .hasRole("ADMIN")
//                .antMatchers("/admin/**")
//                .hasAnyRole("ADMIN")
//                .antMatchers("/user/**")
//                .hasAnyRole("USER", "ADMIN")
//                .antMatchers("/login/**")
//                .anonymous()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        return http.build();
//    }
/*    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/user/one").permitAll()
                .anyRequest().authenticated();

//        http
//                .addFilterBefore( jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // Add CustomFilter before UsernamePasswordAuthenticationFilter

        // http....;

        return http.build();
    }*/
/*    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/user/two").permitAll()
                .anyRequest().authenticated() // Require authentication for all other requests
                .and()
                .formLogin() // Configure form-based login
                .and()
                .httpBasic(); // Enable HTTP Basic Authentication

        return http.build();
    }*/


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(request->request
//                        .requestMatchers(HttpMethod.POST,"/user/login").permitAll()
//                        .requestMatchers("/user/one").permitAll()
//                        .requestMatchers("/user/**").authenticated()
//                ).formLogin(Customizer.withDefaults()) ;
//
////        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // Add CustomFilter before UsernamePasswordAuthenticationFilter
//        return http.build();
//    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("admin")
//                .build();
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("user"))
//                .roles("user")
//                .build();
//        return  new InMemoryUserDetailsManager(admin,user);
//    }

//}


/*
package uz.fb.askueauthservice.configuration;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import uz.fb.askueauthservice.configuration.security.AuthService;
import uz.fb.askueauthservice.entity.Users;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    AuthenticationCheck authenticationCheck;
    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//    @Bean
//    JwtRequestFilter jwtRequestFilter(){
//        return new JwtRequestFilter();
//    }

    @Autowired
    JwtRequestFilter jwtRequestFilter;


    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .mvcMatchers("/public/**").permitAll() // Allow access to specific URLs without authentication
                                .anyRequest().authenticated() // Require authentication for all other requests
                )
                .formLogin(withDefaults()); // Use default login form

        return http.build();
    }

*/
/*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request->request
                        .requestMatchers(HttpMethod.POST,"/user/login").permitAll()
                        .requestMatchers("/user/one").permitAll()
                        .requestMatchers("/**").authenticated()
                  ).formLogin(Customizer.withDefaults()) ;

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // Add CustomFilter before UsernamePasswordAuthenticationFilter
        return http.build();
    }
*//*



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    public DaoAuthenticationConfigurer<AuthenticationManagerBuilder, AuthService> authenticationManagerBuilder(AuthenticationManagerBuilder auth) throws Exception{
//        return auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
//    }
//todo
//    public DaoAuthenticationConfigurer<AuthenticationManagerBuilder, AuthenticationCheck> authenticationManagerBuilder(AuthenticationManagerBuilder auth) throws Exception {
//        return auth.userDetailsService(authenticationCheck).passwordEncoder(passwordEncoder());
//    }
//



//    allowedMethods --> da qayerdan request kelishi yoziladi
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*");
            }
        };
    }

}
*/
