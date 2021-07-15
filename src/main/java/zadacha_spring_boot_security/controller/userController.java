package zadacha_spring_boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zadacha_spring_boot_security.model.User;
import zadacha_spring_boot_security.service.UserServiceDao;


import java.util.List;

@Controller
@RequestMapping("")
public class userController {

    private UserServiceDao userServiceDao;

    @Autowired
    public userController(UserServiceDao userServiceDao) {
        this.userServiceDao = userServiceDao;
    }

    @GetMapping("/user")
    public String showAllUser(Model model){
        List<User> user = userServiceDao.getAllUser();
        model.addAttribute("user_user", user);
        return "user";
    }
}
