package com.crcl.iam.controllers;

import com.crcl.iam.dto.CreateAccount;
import com.crcl.iam.dto.UserDto;
import com.crcl.iam.servcies.UserService;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @GetMapping("login")
    public String login() {
        System.out.println("responding with login page");
        return "sign-in";
    }

    @PostMapping("login")
    public String loginFailed() {
        return "redirect:/authenticate?error=invalid username or password";
    }

    @GetMapping("sign-up")
    public String registerPage(Model model) {
        model.addAttribute("account", new CreateAccount());
        model.addAttribute("step", 1);
        return "sign-up";
    }

    @PostMapping("sign-up")
    public Mono<String> saveUser(@ModelAttribute("account") @Valid CreateAccount account, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return Mono.just("sign-up");
        }

        UserDto entityDto = account.toUserDto();
        return this.userService.save(entityDto)
                .flatMap(userDto -> Mono.just("sign-up"));
    }

}
