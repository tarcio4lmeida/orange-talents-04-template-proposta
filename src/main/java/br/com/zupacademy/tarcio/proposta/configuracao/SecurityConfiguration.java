package br.com.zupacademy.tarcio.proposta.configuracao;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers(HttpMethod.GET, "/propostas/**").hasAuthority("SCOPE_nossocartao")
                                .antMatchers(HttpMethod.POST, "/propostas").hasAuthority("SCOPE_nossocartao")
                                
                                .antMatchers(HttpMethod.POST, "/biometrias").hasAuthority("SCOPE_nossocartao")
                                
                                .antMatchers(HttpMethod.GET, "/actuator/**").hasAuthority("SCOPE_nossocartao")
                                
                                .antMatchers(HttpMethod.PUT, "/cartoes/**").hasAuthority("SCOPE_nossocartao")
                                
                                .antMatchers(HttpMethod.POST, "/aviso-viagens").hasAuthority("SCOPE_nossocartao")
                                
                                .antMatchers(HttpMethod.POST, "/carteira-digitais").hasAuthority("SCOPE_nossocartao")
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

}