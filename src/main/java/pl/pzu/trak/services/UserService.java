package pl.pzu.trak.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import pl.pzu.trak.domain.User;
import pl.pzu.trak.domain.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User findByLogin(String login);
    User save(UserRegistrationDto registration);
}
