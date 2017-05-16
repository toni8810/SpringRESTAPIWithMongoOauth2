package spring.rest.oauth2.mongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import spring.rest.oauth2.mongo.repository.MongoDBRepository;

@Service
public class MongoUserDetailsService implements UserDetailsService {
	
	@Autowired
	private MongoDBRepository mongoDBRepository;
	
	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		return mongoDBRepository.findByUsername(arg0).map(user -> new User(user.getUsername(), user.getPassword(), true, true, true, true, AuthorityUtils.createAuthorityList("ROLE_USER")))
				.orElseThrow(() -> new UsernameNotFoundException("Username "+arg0+" is not found!"));
	}

}
