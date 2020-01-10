package com.joe;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.joe.compone.MyCustomUserService;
//import com.joe.compone.MyFilterSecurityInterceptor;

@Configuration
@EnableWebMvc
@EnableAuthorizationServer
@EnableResourceServer
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

//	@Configuration
//	protected static class WebMvcConfiguration implements WebMvcConfigurer {
//
//		@Override
//		public void addViewControllers(final ViewControllerRegistry registry) {
//			registry.addViewController("/login").setViewName("login");
//			registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//		}
//
//	}

	@Configuration
	@Order(2)
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		private static final String RESOURCE_ID = "Sample";

		@Override
		public void configure(final ResourceServerSecurityConfigurer resources) throws Exception {
			resources.resourceId(RESOURCE_ID);
		}

		@Override
		public void configure(final HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/api/**").authenticated();
		}
	}

	@Configuration
	protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
		
		@Autowired
		DataSource dataSource;
		
//		@Autowired
//		MyCustomUserService myCustomUserService;
		
		@Autowired
		RedisConnectionFactory connectionFactory;

		@Autowired
		private AuthenticationManager authenticationManager;
		
		@Bean
		UserDetailsService oauthCustomUserService(){ //注册UserDetailsService 的bean
	        return new MyCustomUserService();
	    }

		@Override
		public void configure(final AuthorizationServerSecurityConfigurer security) throws Exception {
			security.allowFormAuthenticationForClients().tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
		}

		@Override
		public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
			clients.jdbc(dataSource);
		}
		
		

		@Override
		public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.tokenStore(new RedisTokenStore(connectionFactory)).authenticationManager(authenticationManager).userDetailsService(oauthCustomUserService());
		}

	}

	@Configuration
	@Order(1)
	protected static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Autowired
		DataSource dataSource;
//		@Autowired
//		MyFilterSecurityInterceptor myFilterSecurityInterceptor;
		
		@Bean
		UserDetailsService customUserService(){ //注册UserDetailsService 的bean
	        return new MyCustomUserService();
	    }
		
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(customUserService()).passwordEncoder(new BCryptPasswordEncoder());
//			auth.inMemoryAuthentication().withUser("john").password("{noop}123").roles("User");
//			auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder());
		}

		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		@Override
		protected void configure(final HttpSecurity http) throws Exception {
//			http.authorizeRequests().anyRequest().authenticated();
			http
			.requestMatchers().antMatchers("/oauth/**").and()
					.authorizeRequests().anyRequest().authenticated()
					.and().formLogin().usernameParameter("username").passwordParameter("password").loginPage("/login")
					.permitAll().and().httpBasic().disable().logout()
					.permitAll();
//			http
//			.requestMatchers().antMatchers("/oauth/**")
//			.and()
//			.authorizeRequests()
//            .anyRequest().authenticated() //任何请求,登录后可以访问
//            .and()
//            .formLogin()
//            .loginPage("/login")
//            .failureUrl("/login?error")
//            .permitAll() //登录页面用户任意访问
//            .and()
//            .logout().permitAll();
//			
//			http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);

		}
		
	}
}
