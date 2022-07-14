package ru.kata.spring.boot_security.demo.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;


import java.util.HashSet;
import java.util.Set;
@Service
public class RoleServiseImpl implements RoleService{

    private final RoleDao roleDao;

    @Autowired
    public RoleServiseImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public Set<Role> getAllRoles() {
        return  new HashSet<>(roleDao.getAllRoles());
    }

    public Role findById(Long id) {
        return roleDao.findRoleById(id);
    }

    public void addRole(Role role) {
        roleDao.saveRole(role);

    }
}
