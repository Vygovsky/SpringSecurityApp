package net.security.service;

import net.security.dao.RoleDao;
import net.security.dao.UserDao;
import net.security.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleDao.findAll()));
        userDao.save(user);

       /* Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
        user.setRoles(roles);

        user.setRoles(new HashSet<>(roleDao.findAll()));
        userDao.save(user);*/
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
