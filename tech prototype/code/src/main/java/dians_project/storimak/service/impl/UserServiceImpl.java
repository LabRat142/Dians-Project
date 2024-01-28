package dians_project.storimak.service.impl;

import dians_project.storimak.model.User;
import dians_project.storimak.model.UserRole;
import dians_project.storimak.repository.UserRepository;
import dians_project.storimak.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(String username, String password, String repeatPassword) {
        if(!Objects.equals(password, repeatPassword)) {
            throw new RuntimeException();
        }
        User tmp;
        if(!Objects.equals(username, "admin")) {
            tmp = new User(username, passwordEncoder.encode(password), UserRole.ROLE_USER);
        } else {
            tmp = new User(username, passwordEncoder.encode(password), UserRole.ROLE_ADMIN);
        }
        userRepository.save(tmp);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
    }
}
