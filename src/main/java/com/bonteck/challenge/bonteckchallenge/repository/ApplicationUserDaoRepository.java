package com.bonteck.challenge.bonteckchallenge.repository;

import com.bonteck.challenge.bonteckchallenge.dao.ApplicationUserDAO;
import com.bonteck.challenge.bonteckchallenge.model.RoleEntity;
import com.bonteck.challenge.bonteckchallenge.model.UserEntity;
import com.bonteck.challenge.bonteckchallenge.security.auth.ApplicationUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
            Set<GrantedAuthority> authorities = new HashSet<>();
            for(RoleEntity roleEntity : userEntity.getRoles()) {
                authorities.addAll(getRole(roleEntity.getRoleId()).getAuthorities());
            }
            return Optional.of(new ApplicationUser(authorities,
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
