package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;

import ru.kata.spring.boot_security.demo.servise.RoleService;
import ru.kata.spring.boot_security.demo.servise.RoleServiseImpl;
import ru.kata.spring.boot_security.demo.servise.UserService;
import ru.kata.spring.boot_security.demo.servise.UserServiceImpl;

@Controller
public class AdminControl {

    private final UserService userService;
    private final RoleService roleServise;

    public AdminControl(UserService userService, RoleService roleServise) {
        this.userService = userService;
        this.roleServise = roleServise;
    }


    @GetMapping("/admin")
    public String adminInfo(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("userMain", user);
        model.addAttribute("roles", roleServise.getAllRoles());
        return "admin";
    }


    @PostMapping("/admin/create")
    public String addUser(User user, @RequestParam ("listRoles") long[] roles) {
        userService.saveUser(user,roles);
        return "redirect:/admin";
    }


    @PostMapping("/admin/update")
    public String update(@ModelAttribute("user") User user, @RequestParam("listRoles") long[] roleId) {
        userService.updateUser(user, roleId);
        return "redirect:/admin";
    }


    @PostMapping("/admin/delete/{id}")
    public String removeUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
