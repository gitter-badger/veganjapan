package com.alex.alexwebapp.models;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

/**
 * @author alex on 2016/08/21.
 */
@Transactional
public interface UserDao extends CrudRepository<User, Long> {

    /**
     * Return the user having the passed email or null if no user is found.
     *
     * @param email the user email.
     */
    public User findByEmail(String email);
}