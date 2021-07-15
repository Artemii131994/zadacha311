package zadacha_spring_boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import zadacha_spring_boot_security.model.Role;
import zadacha_spring_boot_security.model.User;
import zadacha_spring_boot_security.service.UserServiceDao;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("")
public class adminController {

    private UserServiceDao userServiceDao;

    @Autowired
    public adminController(UserServiceDao userServiceDao) {
        this.userServiceDao = userServiceDao;

    }

    @GetMapping("/admin")
    public String showAllUser(Model model) {
        List<User> allUser = userServiceDao.getAllUser();
        model.addAttribute("allUser", allUser);
        return "userList";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "createUser";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam("role") String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(userServiceDao.getByName(roles));
        }
        user.setRoles(roleSet);
        userServiceDao.update(user);

        return "redirect:/admin";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, ModelMap model) {
        User user = userServiceDao.getUser(id);
        model.addAttribute("user", user);
        return "updateUser";
    }

    @PutMapping("/updateSave")
    public String edit(@ModelAttribute("user") User user,
                       @RequestParam("role") String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(userServiceDao.getByName(roles));
        }
        user.setRoles(roleSet);
        userServiceDao.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userServiceDao.deleteUser(id);
        return "redirect:/admin";
    }


}