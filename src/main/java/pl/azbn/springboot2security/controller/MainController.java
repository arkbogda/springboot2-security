package pl.azbn.springboot2security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.azbn.springboot2security.entity.AppUser;
import pl.azbn.springboot2security.repo.AppUserRepo;
import pl.azbn.springboot2security.service.UserService;

@Controller
public class MainController {

    private AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public MainController(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder, UserService userService) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }


    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/signup")
    public ModelAndView signup() {
        return new ModelAndView("registration", "user", new AppUser());
    }

    @RequestMapping("/register")
    public ModelAndView signup (AppUser user) {
        userService.addNewUser(user);
        return new ModelAndView("redirect:/login");
    }
}
