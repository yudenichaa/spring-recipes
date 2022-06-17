package dev.nightzen.recipes.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.nightzen.recipes.business.entity.User;
import dev.nightzen.recipes.business.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class AuthController {
    UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public void register(@RequestBody @Valid User user) {
        userService.register(user);
    }
}
