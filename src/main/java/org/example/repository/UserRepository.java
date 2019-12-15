package org.example.repository;

import org.springframework.data.repository.CrudRepository;

import org.example.domain.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

}
