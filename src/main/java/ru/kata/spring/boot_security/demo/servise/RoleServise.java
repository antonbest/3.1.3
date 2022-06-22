package ru.kata.spring.boot_security.demo.servise;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;

public class RoleServise  {

    private final RoleRepository roleRepository;

    public RoleServise(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Set<Role> getAllRoles() {
        return  new HashSet<>(roleRepository.findAll());
    }

    public Role findById(Long id) {
        return roleRepository.getById(id);
    }

    public Set<Role> findByIdRoles(String roleName) {
        return roleRepository.getRoleByRoleName(roleName);
    }


    public void addRole(Role role) {
        roleRepository.save(role);

    }
}