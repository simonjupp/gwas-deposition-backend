package uk.ac.ebi.spot.gwas.deposition.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.ebi.spot.gwas.deposition.domain.User;
import uk.ac.ebi.spot.gwas.deposition.repository.UserRepository;
import uk.ac.ebi.spot.gwas.deposition.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User findOrCreateUser(User user) {
        log.info("Looking for user [{}]: {}", user.getName(), user.getEmail());
        Optional<User> userOpt = userRepository.findByEmailIgnoreCase(user.getEmail());
        if (userOpt.isPresent()) {
            log.info("Returning existing user: {}", user.getId());
            return userOpt.get();
        }
        user = userRepository.insert(user);
        log.info("Returning newly created user: {}", user.getId());
        return user;
    }
}
