package pl.pzu.trak.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import pl.pzu.trak.security.LoggingAccessDeniedHandler;
import pl.pzu.trak.services.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    
    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;    

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(
                            "/registration",
                            "/js/**",
                            "/css/**",
                            "/media/**",
                            "/webjars/**").permitAll()
                    .antMatchers("/tasks/**").hasAuthority("ZADANIA")
                    .antMatchers("/users/**").hasAuthority("UZYTKOWNICY")
                    .antMatchers("/roles/**").hasAuthority("ROLES")
                    .anyRequest().hasAuthority("READ")
                .and()
                    .formLogin()
                        .loginPage("/login")
                            .permitAll()
                .and()
                    .logout()
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()                        
                .and()
                    .exceptionHandling()
                        .accessDeniedHandler(accessDeniedHandler);                        
                    
    }

    @Bean
    public static DefaultRolesPrefixPostProcessor defaultRolesPrefixPostProcessor() {
    	return new DefaultRolesPrefixPostProcessor();
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }


    //LDAP    
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.ldapAuthentication()
//				.userDnPatterns("uid={0},CN=Users,O=PZU")
//				.groupSearchBase("CN=Users,O=PZU")
//				.contextSource()
//					.url("ldap://ldap.pzu.pl:389/")
//					.and()
//				.passwordCompare()
//					.passwordEncoder(passwordEncoder())
//					.passwordAttribute("userPassword");    
//	}
//	
//    private PasswordEncoder passwordEncoder() {
//		final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
//		return new PasswordEncoder() {
//			@Override
//			public String encode(CharSequence rawPassword) {
//				return bcrypt.encode(rawPassword.toString());
//			}
//			@Override
//			public boolean matches(CharSequence rawPassword, String encodedPassword) {
//				return bcrypt.matches(rawPassword, encodedPassword);
//			}
//		};
//	}    
//    
//	@Bean
//	public BCryptPasswordEncoder bcryptEncoder() {
//		return new BCryptPasswordEncoder();
//	}    
    
}