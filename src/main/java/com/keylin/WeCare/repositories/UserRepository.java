package com.keylin.WeCare.repositories;

import org.springframework.data.repository.CrudRepository;

import com.keylin.WeCare.entities.Users;

public interface UserRepository extends CrudRepository<Users, Long> {

}