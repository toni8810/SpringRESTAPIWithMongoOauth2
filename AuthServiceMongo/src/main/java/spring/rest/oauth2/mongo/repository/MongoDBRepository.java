package spring.rest.oauth2.mongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import spring.rest.oauth2.mongo.domain.User;

public interface MongoDBRepository extends MongoRepository<User, String> {
	Optional<User> findByUsername(String username);
}
