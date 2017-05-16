package spring.rest.oauth2.mongo.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServiceConfiguration extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
		endpoints.tokenStore(tokenStore());
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("web")
		.secret("PvwQph4sNQRJLCqv")
		.authorizedGrantTypes("password")
		.scopes("openid")
		.accessTokenValiditySeconds(60*60*24*3);
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}
	
}
