package com.teressas.bookclub_wlogin.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.teressas.bookclub_wlogin.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByEmail(String email);
	Optional<User> findByUserName(String userName);
}
