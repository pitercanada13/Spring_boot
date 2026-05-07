package hiber.controller;

import hiber.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {

        model.addAttribute("users", userService.listUsers());

        return "admin";
    }
}

/*
package hiber.controller;


import hiber.model.User;
import hiber.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String adminPage(Model model) {
        List<User> users = userService.listUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "admin";
    }

    @PostMapping("/add")
    public String addUser(User user) {
        userService.add(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}

 */


//package hiber.controller;
//
//
//import hiber.model.User;
//import hiber.service.RoleService;
//import hiber.service.UserService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//    private final RoleService roleService;
//
//    private final UserService userService;
//
////    public AdminController(UserService userService) {
////        this.userService = userService;
////    }
//
//    public AdminController(UserService userService, RoleService roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
//    }

//    // список пользователей
//    @GetMapping
//    public String adminPage(Model model) {
//        model.addAttribute("users", userService.listUsers());
//        return "admin";
//    }
//
//    // УДАЛЕНИЕ
//    @GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable Long id) {
//        userService.delete(id);
//        return "redirect:/admin";
//    }
//
//    // ФОРМА РЕДАКТИРОВАНИЯ
////    @GetMapping("/edit/{id}")
////    public String editUser(Model model, @PathVariable Long id) {
////        model.addAttribute("user", userService.getById(id));
////        return "edit";
////    }
//    @GetMapping("/edit/{id}")
//    public String editUser(Model model, @PathVariable Long id) {
//        model.addAttribute("user", userService.getById(id));
//        model.addAttribute("allRoles", roleService.listRoles());
//        return "edit";
//    }
//
//    // СОХРАНЕНИЕ (update)
//    @PostMapping("/update")
//    public String updateUser(@ModelAttribute("user") User user) {
//        userService.update(user);
//        return "redirect:/admin";
//    }

//@GetMapping
//public String adminPage(Model model) {
//    model.addAttribute("users", userService.listUsers());
//    return "admin";
//}
//
//    @GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable Long id) {
//        userService.delete(id);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editUser(Model model, @PathVariable Long id) {
//        model.addAttribute("user", userService.getById(id));
//        model.addAttribute("allRoles", roleService.listRoles());
//        return "edit";
//    }
//
//    @PostMapping("/update")
//    public String updateUser(@ModelAttribute("user") User user) {
//        userService.update(user);
//        return "redirect:/admin";
//    }
//}