package com.alex.alexwebapp.models;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * @author alex on 2016/08/21.
 * TODO: delete (for now, keep for reference)
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