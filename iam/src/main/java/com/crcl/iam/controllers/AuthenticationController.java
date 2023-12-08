package com.crcl.iam.controllers;

import com.crcl.iam.dto.CreateAccount;
import com.crcl.iam.dto.UserDto;
import com.crcl.iam.servcies.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name = "Authentication", description = "Authentication API")
public class AuthenticationController {

    private final UserService userService;

    @GetMapping("login")
    @Operation(summary = "Returns the login page.")
    public String login() {
        System.out.println("responding with login page");
        return "sign-in";
    }

    @PostMapping("login")
    @Operation(summary = "Redirects to the login page with an error message indicating invalid username or password.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "303", description = "Redirected to the login page with an error message.")
    })
    public String loginFailed() {
        return "redirect:/authenticate?error=invalid username or password";
    }

    @GetMapping("sign-up")
    @Operation(summary = "Retrieves the registration page.")
    public String registerPage(Model model) {
        model.addAttribute("account", new CreateAccount());
        model.addAttribute("step", 1);
        return "sign-up";
    }

    @PostMapping("sign-up")
    @Operation(summary = "Saves a user account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The user was created successfully."),
            @ApiResponse(responseCode = "400", description = "The request body is invalid.")
    })
    public Mono<String> saveUser(@RequestBody(description = "The account to be created.", required = true)
                                 @ModelAttribute("account") @Valid CreateAccount account, Errors errors) {
        if (errors.hasErrors()) {
            return Mono.just("sign-up");
        }

        UserDto entityDto = account.toUserDto();
        return this.userService.save(entityDto)
                .flatMap(userDto -> Mono.just("sign-up"));
    }

}