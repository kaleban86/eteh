package com.eteh.eteh.controller;


import com.eteh.eteh.models.User;
import com.eteh.eteh.models.UserProfileModels;
import com.eteh.eteh.repository.UserProfileRepo;
import com.eteh.eteh.repository.UserRepository;
import com.eteh.eteh.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;


@Controller
public class FormControllerUserProfile {


    final
    UserProfileRepo userProfileRepo;

    private final UserService userService;

    private final UserRepository userRepository;

    public FormControllerUserProfile(UserService userService, UserProfileRepo userProfileRepo, UserRepository userRepository) {
        this.userService = userService;
        this.userProfileRepo = userProfileRepo;
        this.userRepository = userRepository;
    }

    @GetMapping("/user-profile")
    public String findAll(Model model, @AuthenticationPrincipal User user){
        Long id = user.getId();
        List<UserProfileModels> userId = userProfileRepo.fainBiId(id);

        Optional<User> userIdRepo = userRepository.findById(id);

        model.addAttribute("userIdRepo",userIdRepo);







        return "/user-profile";
    }

    @PostMapping("/user-profile")
    public String updateUser(User user){
        userService.saveUser(user);
        return "redirect:/hello";
    }

}
