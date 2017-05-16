package spring.rest.oauth2.mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.rest.oauth2.mongo.domain.User;
import spring.rest.oauth2.mongo.repository.MongoDBRepository;

@RestController
@EnableResourceServer
public class AppController extends ResourceServerConfigurerAdapter{
	
	@Autowired
	private MongoDBRepository mongoDBRepository;
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public User signUp(User user) {
		return mongoDBRepository.save(user);
	}
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public List<User> getAllUser() {
		return mongoDBRepository.findAll();
	}
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/user").hasRole("USER");
	}
	
	
	
}
