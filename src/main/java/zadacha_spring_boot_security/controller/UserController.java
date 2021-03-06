package zadacha_spring_boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zadacha_spring_boot_security.model.User;
import zadacha_spring_boot_security.service.UserServiceDao;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("")
public class UserController {

    private UserServiceDao userServiceDao;

    @Autowired
    public UserController(UserServiceDao userServiceDao) {
        this.userServiceDao = userServiceDao;
    }

    @GetMapping("/user")
    public String showAllUser(Model model, Principal principal) {
        User user = userServiceDao.ByUserName(principal.getName());
        model.addAttribute("user_user", user);
        return "user";
    }
}
