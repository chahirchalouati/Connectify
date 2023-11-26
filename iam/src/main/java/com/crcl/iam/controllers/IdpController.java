package com.crcl.iam.controllers;

import com.crcl.iam.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("authentication")
public class IdpController {
    @GetMapping("login")
    public String login() {
        System.out.println("responding with login page");
        return "login";
    }

    @PostMapping("login")
    public String loginFailed() {
        return "redirect:/authenticate?error=invalid username or password";
    }

    @GetMapping("register")
    public String registerPage(Model model) {
        model.addAttribute("formData", new UserDto());
        return "register";
    }
}