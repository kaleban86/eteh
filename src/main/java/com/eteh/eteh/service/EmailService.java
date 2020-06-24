package com.eteh.eteh.service;


import com.eteh.eteh.models.User;
import com.eteh.eteh.repository.PasswordResetTokenRepository;
import com.eteh.eteh.repository.UserRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final MailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    private final PasswordResetTokenRepository tokenRepository;
    public EmailService( UserService userService, UserRepository userRepository, MailSender mailSender, SpringTemplateEngine templateEngine, PasswordResetTokenRepository tokenRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.tokenRepository = tokenRepository;
    }


    public void sendEmail(User user,Mail mail) throws NullPointerException  {
        try {
            User userFromDb = userRepository.findAllByUsername(user.getUsername());
         //   MimeMessage message = mailSender.createMimeMessage();
          //  MimeMessageHelper helper = new MimeMessageHelper(message,
                 // MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED
                   // StandardCharsets.UTF_8.name())

           Context context = new Context();
            context.setVariables(mail.getModel());
            String html = new String("Сброс пароля");

          // helper.setTo(mail.getTo());
         // helper.setText(html, true);
         // helper.setSubject(mail.getSubject());
          // helper.setFrom(mail.getFrom());


            mailSender.send(user.getEmail(),"Сброс пароля", html);
/*
            if (!StringUtils.isEmpty(user.getEmail())) {
                String message = String.format(
                        "Добрый день, %s \n" +
                                "Добро пожаловать на корпоративный портал для подтверждения регистрации перейдите link: http://localhost:8080/activate/%s",
                        user.getName(),
                        user.getActivationCode()
                       // tokenRepository.findByToken(t)


                );

            }

 */


        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
