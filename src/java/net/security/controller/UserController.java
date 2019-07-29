package net.security.controller;

import com.sun.tracing.dtrace.ModuleAttributes;
import net.security.modal.User;
import net.security.service.SecuritySecvice;
import net.security.service.UserService;
import net.security.validator.UserValidarot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SecuritySecvice securitySecvice;
    @Autowired
    private UserValidarot userValidarot;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationPost(@ModelAttribute("userForm") User userForm,
                                   BindingResult result,
                                   Model model) {
        userValidarot.validate(userForm, result);
        if (result.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);
        securitySecvice.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password incorrect");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out is successfully");
        }
        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome (Model model){
        return "welcome";
    }
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin (Model model){
        return "admin";
    }
}
