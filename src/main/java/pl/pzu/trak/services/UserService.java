package pl.pzu.trak.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import pl.pzu.trak.domain.User;
import pl.pzu.trak.domain.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User findByLogin(String login);
    User save(UserRegistrationDto registration);
    List<User> findAll();
    User findOne(Long id);
    void save(User user);
}
