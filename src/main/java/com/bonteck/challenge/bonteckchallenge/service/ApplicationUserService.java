package com.bonteck.challenge.bonteckchallenge.service;

import com.bonteck.challenge.bonteckchallenge.dao.ApplicationUserDAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Ali Tofigh 2/10/2022 10:53 AM
 */

@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserDAO userDAO;

    public ApplicationUserService(ApplicationUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.getUser(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("username '%s' not found", username)));
    }
}
