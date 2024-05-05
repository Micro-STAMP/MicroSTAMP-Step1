package microstamp.step1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig { //extends WebSecurityConfigurerAdapter {

    /*@Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/guests/**").permitAll()
                .antMatchers("/guests-request/**").permitAll()
                .antMatchers("/swagger/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/v3/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
            web.ignoring()
                    .antMatchers("/guests/**","/static/**","/webjars/**","/assets/**", "/guests-request/**", "/projects/**", "/systemgoals/**", "/assumptions/**", "/losses/**", "/hazards/**", "/systemsafetyconstraints/**", "/js/**")
                    //.antMatchers("/controlstructures/**", "/components/**", "/actuators/**", "/sensors/**", "/controllers/**","/controlledProcesses/**","/connections/**","/images/**","/labels/**", "/variables/**", "/states/**", "/responsibilities/**")
                    .antMatchers(HttpMethod.OPTIONS, "/**");
    }*/
}
