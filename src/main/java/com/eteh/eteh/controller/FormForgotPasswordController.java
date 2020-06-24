package com.eteh.eteh.controller;

import com.eteh.eteh.dto.PasswordForgotDto;
import com.eteh.eteh.models.PasswordResetToken;
import com.eteh.eteh.models.User;
import com.eteh.eteh.repository.PasswordResetTokenRepository;
import com.eteh.eteh.service.EmailService;
import com.eteh.eteh.service.Mail;
import com.eteh.eteh.service.MailSender;
import com.eteh.eteh.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("/forgot-password")
public class FormForgotPasswordController {

    private final
    MailSender mailSender;
    private final
    PasswordResetTokenRepository tokenRepository;
    private final
    UserService userService;
    private final
    EmailService emailService;

    public FormForgotPasswordController(MailSender mailSender, UserService userService, PasswordResetTokenRepository tokenRepository, EmailService emailService) {
        this.mailSender = mailSender;
        this.userService = userService;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
    }


    @ModelAttribute("forgotPasswordForm")
    public PasswordForgotDto forgotPasswordDto() {
        return new PasswordForgotDto();
    }

    @GetMapping
    public String displayForgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping
    public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDto form,
                                            BindingResult result,
                                            HttpServletRequest request) {

        if (result.hasErrors()) {
            return "forgot-password";
        }
        User user = userService.findByEmail(form.getEmail());
        if (user == null) {
            result.rejectValue("email", null, "Мы не смогли найти учетную запись для этого адреса электронной почты.");
            return "forgot-password";
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        tokenRepository.save(token);

        Mail mail = new Mail();
        mail.setTo(user.getEmail());


        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "http://localhost:8080/");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(model);


       mailSender.send(user.getEmail(),"Password reset request", (String) model.put("resetUrl", url + "/reset-password?token=" + token.getToken()));

        return "redirect:/forgot-password?success";
    }
}
