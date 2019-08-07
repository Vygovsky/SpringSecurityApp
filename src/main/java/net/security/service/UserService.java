package net.security.service;
import net.security.modal.User;


public interface UserService {

    void save(User user);

    User findByUsername(String username);

}
