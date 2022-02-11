package com.bonteck.challenge.bonteckchallenge.repository;

import com.bonteck.challenge.bonteckchallenge.dao.ApplicationUserDAO;
import com.bonteck.challenge.bonteckchallenge.model.UserEntity;
import com.bonteck.challenge.bonteckchallenge.security.auth.ApplicationUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.bonteck.challenge.bonteckchallenge.security.ApplicationUserRole.getRole;

/**
 * @author Ali Tofigh 2/10/2022 12:24 PM
 */

@Repository
public class ApplicationUserDaoRepository implements ApplicationUserDAO {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ApplicationUserDaoRepository(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> getUser(String username) {
        try {
            UserEntity userEntity = userRepository.findUserEntityByUsername(username);
            return Optional.of(new ApplicationUser(
                    getRole(userEntity.getRoleId()).getAuthorities(),
                    passwordEncoder.encode(userEntity.getPassword()),
                    userEntity.getUsername(),
                    true,
                    userEntity.isNonLocked(),
                    true,
                    userEntity.isEnable()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
