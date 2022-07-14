package ru.kata.spring.boot_security.demo.servise;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface UserService {

    public void saveUser(User user, long[] listRoles) ;

    public void updateUser(User user, long[] role_id);

    public UserDetails loadUserByUsername(String username) ;

    public User findUserById(Integer userId);

    public List<User> allUsers();

    public User saveUser(User user) ;

    public void deleteUser(Integer userId) ;
}
