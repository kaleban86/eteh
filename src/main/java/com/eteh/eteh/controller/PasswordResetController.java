package com.eteh.eteh.controller;

import com.eteh.eteh.dto.PasswordResetDto;
import com.eteh.eteh.models.PasswordResetToken;
import com.eteh.eteh.models.User;
import com.eteh.eteh.repository.PasswordResetTokenRepository;
import com.eteh.eteh.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

@Controller
@RequestMapping("/reset-password")
public class PasswordResetController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    private final PasswordResetTokenRepository tokenRepository;

    public PasswordResetController(UserService userService, BCryptPasswordEncoder passwordEncoder, PasswordResetTokenRepository tokenRepository) {
        this.userService = userService;

        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
    }

    @ModelAttribute("passwordResetForm")
    public PasswordResetDto passwordReset() {
        return new PasswordResetDto();
    }

    @GetMapping
    public String displayResetPasswordPage(@RequestParam(required = false) String token,
                                           Model model) {

        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if (resetToken == null){
            model.addAttribute("error", "Не удалось найти токен сброса пароля.");
        } else if (resetToken.isExpired()){
            model.addAttribute("error", "Токен истек, пожалуйста, запросите новый сброс пароля.");
        } else {
            model.addAttribute("token", resetToken.getToken());
        }

        return "reset-password";
    }

    @PostMapping
    @Transactional
    public String handlePasswordReset(@ModelAttribute("passwordResetForm") @Valid PasswordResetDto form,
                                      BindingResult result,
                                      RedirectAttributes redirectAttributes) {

        if (result.hasErrors()){
            redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
            redirectAttributes.addFlashAttribute("passwordResetForm", form);
            return "redirect:/reset-password?token=" + form.getToken();
        }

        PasswordResetToken token = tokenRepository.findByToken(form.getToken());
        User user = token.getUser();
        String updatedPassword = passwordEncoder.encode(form.getPassword());
        userService.updatePassword(updatedPassword, user.getId());
        tokenRepository.delete(token);

        return "redirect:/login?resetSuccess";
    }

}
