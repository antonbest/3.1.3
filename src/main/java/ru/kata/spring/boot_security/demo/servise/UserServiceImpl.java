package ru.kata.spring.boot_security.demo.servise;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserServiceImpl implements UserDetailsService,UserService {

    @PersistenceContext
    private EntityManager em;
    private UserDao userDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;
    private Set<Role> roles;
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public UserServiceImpl(EntityManager em, UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder, Set<Role> roles) {
        this.em = em;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
        this.roles = roles;
    }

    @Transactional
    public void saveUser(User user, long[] listRoles) {
        Set<Role> rolesSet = new HashSet<>();
        for (int i = 0; i < listRoles.length; i++) {
            rolesSet.add(roleDao.findRoleById(listRoles[i]));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRoles(rolesSet);
        userDao.saveUser(user);
    }


    @Transactional
    public void updateUser(User user, long[] role_id) {
        Set<Role> rolesSet = new HashSet<>();
        for (int i = 0; i < role_id.length; i++) {
            rolesSet.add(roleDao.findRoleById(role_id[i]));
        }
        if (user.getPassword().startsWith("$2a$10$") && user.getPassword().length() == 60) {
            user.setPassword(user.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setRoles(rolesSet);
        userDao.saveUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return user;
    }


    public User findUserById(Integer userId) {
        return userDao.getById(userId);
    }

    public List<User> allUsers() {
        return userDao.getAllUsers();
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.saveUser(user);
    }

    public void deleteUser(Integer userId) {
        userDao.deleteUser(userId);
    }

}
