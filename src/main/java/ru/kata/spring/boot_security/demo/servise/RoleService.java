package ru.kata.spring.boot_security.demo.servise;

import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.HashSet;
import java.util.Set;

public interface RoleService {


    public Set<Role> getAllRoles();

    public Role findById(Long id);

    public void addRole(Role role);
}
