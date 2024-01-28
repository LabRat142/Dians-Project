package dians_project.storimak.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void register(String username, String password, String repeatPassword);

}
