package org.launchcode.controllers;

import org.launchcode.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addUser(Model model, @ModelAttribute @Valid User user, Errors errors, String verify) {
        if (errors.hasErrors()) { //checks for errors
            return "user/add";
        }
        if (user.getPassword() != null && verify != null) { //checks that password and verify are filled out
            if (user.getPassword().equals(verify)) {
                model.addAttribute("name", user.getUsername());
                return "user/index";
            } else {
                user.setPassword("");
                model.addAttribute("user", user);
                model.addAttribute("error", "Passwords do not match!");
                return "user/add";
            }

        }
        return "user/add"; //could make this better :)
    }
//        String password = user.getPassword();
//        if (user.getPassword().equals(verify)) {
//            model.addAttribute("name",user.getUsername());
//            return "user/index";
//        } else {
//            model.addAttribute("user",user);
//            model.addAttribute("error","Passwords do not match!!");
//            return "user/add";
//        }

    @RequestMapping(value = "")
    public String index() {
        return "user/index";
    }
}
