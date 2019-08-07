package net.security.service;

import net.security.dao.RoleDao;
import net.security.dao.UserDao;
import net.security.modal.Role;
import org.springframework.beans.factory.annotation.Autowired;
import net.security.modal.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        /*Set<Role> roles = new HashSet<>();
        roles.add(roleDao.findOne(1L));
        user.setRoles(roles);*/
        user.setRoles(new HashSet<>(roleDao.findAll()));
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
