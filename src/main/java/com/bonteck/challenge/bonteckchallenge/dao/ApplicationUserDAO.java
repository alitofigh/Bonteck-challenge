package com.bonteck.challenge.bonteckchallenge.dao;

import com.bonteck.challenge.bonteckchallenge.security.auth.ApplicationUser;

import java.util.Optional;

/**
 * @author Ali Tofigh 2/10/2022 12:19 PM
 */

public interface ApplicationUserDAO {
    Optional<ApplicationUser> getUser(String username);
}
