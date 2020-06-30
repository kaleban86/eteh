package com.eteh.eteh.service;


import com.eteh.eteh.models.Role;
import com.eteh.eteh.models.User;
import com.eteh.eteh.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.eteh.eteh.models.Role.ADMIN;
import static com.eteh.eteh.models.Role.USER;

import java.util.*;


@Service
public class UserService implements UserDetailsService {


    @Value("${emailAdmin}")
    private String emailAdmin;

    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final MailSender mailSender;

    @Lazy
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, MailSender mailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }


    public User findById(Long id){
        return userRepository.getOne(id);
    }

    public List<User> findAll(){


        return userRepository.findAll();
    }


    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }



    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }


    public Optional<User> fiBiId(Long id){

        return userRepository.findById(id);
    }





    @Lazy
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,NoSuchMethodError {
        return userRepository.findAllByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepository.findAllByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        Set <Role> roles = new HashSet<>();
        roles.add(USER);
        user.setRoles(roles);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        if (!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "Новый пользаватель , %s  %s  %s  , %s  \n " +
                            " Необходимо установить права доступа согласно ролевой модели ",
                    user.getName(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail()

            );



                mailSender.send(emailAdmin, "New user", message);

        }
        logger.info("New user registered "+ user.getName()+" "+ user.getFirstName()+" "+user.getLastName());




        return true;
    }

    public boolean activateUser(String code) {
       User user=  userRepository.findByActivationCode(code);

        if (user == null ){

            return false;
        }




        user.setActivationCode(null);

        userRepository.save(user);

        return false;
    }





    public void updatePassword(String password, Long userId) {
        userRepository.updatePassword(password, userId);
    }
}
