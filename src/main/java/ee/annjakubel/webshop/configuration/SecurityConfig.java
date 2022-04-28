package ee.annjakubel.webshop.configuration;

import ee.annjakubel.webshop.authentication.TokenGenerator;
import ee.annjakubel.webshop.authentication.TokenParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);

        TokenParser tokenParser = new TokenParser(authenticationManager());

        http.
                cors().and().headers().xssProtection().disable().and().csrf().disable()
                .addFilter(tokenParser)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/products").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/signup").permitAll()
                .anyRequest().authenticated();
    }


}
