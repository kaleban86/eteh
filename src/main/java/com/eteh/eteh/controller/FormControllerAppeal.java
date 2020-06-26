package com.eteh.eteh.controller;


import com.eteh.eteh.models.Appeal;
import com.eteh.eteh.models.Customer;
import com.eteh.eteh.models.User;
import com.eteh.eteh.repository.AppealRepository;
import com.eteh.eteh.repository.CustomerRepository;
import com.eteh.eteh.repository.UserRepository;
import com.eteh.eteh.service.CostumerService;
import com.eteh.eteh.service.MailSender;
import com.eteh.eteh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@PreAuthorize("hasAnyAuthority('ADMIN','SUPER_ADMIN')  ")
@Controller
public class FormControllerAppeal {


    @Value("${upload.path}")
    private String uploadPath;

    private final MailSender mailSender;

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    private
    CostumerService costumerService;

    private final AppealRepository appealRepository;
    private UserService userService;

    @Autowired
    public FormControllerAppeal(MailSender mailSender, UserRepository userRepository, CustomerRepository customerRepository, AppealRepository appealRepository, UserService userService) {
        this.mailSender = mailSender;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.appealRepository = appealRepository;
        this.userService = userService;
    }


    @GetMapping("/appeal")
    public String appeal(String name, Model model) {
        model.addAttribute("name", name);
        List<User> user = userService.findAll();
        model.addAttribute("user", user);
        Iterable<Customer> customersList = customerRepository.findAll();
        model.addAttribute("customerList", customersList);

        return "appeal";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')  ")
    @PostMapping("/appeal/appalAdd")
    public String appealAdd(@RequestParam String briefDescription,
                            @AuthenticationPrincipal User user,
                            @RequestParam("file") MultipartFile[] file,
                            @RequestParam("file2") MultipartFile[] file2,
                            @RequestParam String footing,
                            @RequestParam String text,
                            @RequestParam String executor,
                            @RequestParam(required = false) String controller,
                            @RequestParam String status,
                            @RequestParam String surname,
                            @RequestParam(required = false) String lastName,
                            @RequestParam(required = false) java.sql.Date dataCreation,
                            @RequestParam java.sql.Date dataAnswer,
                            @RequestParam String nameCompany,
                            @RequestParam String address,
                            @RequestParam String tel,
                            @RequestParam String emailAddress, @RequestParam String authorUpdate) throws IOException, SQLException {


        String lastNameAuthorUpdate = user.getLastName();
        String nameAuthorUpdate = user.getName();


        String string = executor;
        String[] str_array = string.split(" ");
        String last_name = str_array[0];
        String first_name = str_array[1];
        String name = str_array[2];
        String emailExecutor = str_array[3];


        String stringController = controller;
        String[] str_arrayController = stringController.split(" ");
        String last_nameController = str_arrayController[0];
        String first_nameController = str_arrayController[1];
        String nameController = str_arrayController[2];
        String emailController = str_arrayController[3];


        Appeal appeal = new Appeal(user, briefDescription, footing,
                text, last_nameController + " " + first_nameController + " " + nameController, last_name + " " + first_name + " " + name, status, surname,
                lastName, dataAnswer, dataCreation, nameCompany, address, tel, emailAddress, lastNameAuthorUpdate + " " + nameAuthorUpdate);


        appeal.getId();


        for(MultipartFile file1  : file){

            if (file != null) {



                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }


             //   String uuidFile = UUID.randomUUID().toString();
                String resultFilename = file1.getOriginalFilename();
                appeal.setFileName(resultFilename);

                if (resultFilename.isEmpty()) {
                    resultFilename = " ";
                }
                file1.transferTo(new File(uploadPath + resultFilename ));


            }



            for(MultipartFile file3  : file2){

                if (file2 != null) {

                    File uploadDir = new File(uploadPath);

                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }

                    //   String uuidFile = UUID.randomUUID().toString();
                    String resultFilename2 = file3.getOriginalFilename();
                    appeal.setFileName2(resultFilename2);

                    if (resultFilename2.isEmpty()) {
                        resultFilename2 = " ";
                    }
                    file3.transferTo(new File(uploadPath + resultFilename2));
                }


                }
            }







        appealRepository.save(appeal);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                            "Новое входящие обращение: № %s. \n" +


                            "Краткое описание:  %s. \n" +

                            "Название компании:  %s.\n" +

                            "Дата создания: %s \n" +

                            "Дата закрытия: %s \n"+

                            "http://10.0.1.32:8080/appeal/reading/%s",
                    appeal.getId(),
                    appeal.getBriefDescription(),
                    appeal.getNameCompany(),
                    appeal.getDataCreation(),
                    appeal.getDataAnswer(),
                    appeal.getId()



            );


            mailSender.send(emailExecutor, "Вы назначены исполнителем ", message);
            mailSender.send(emailController, "Вы назначены контролёром ", message);

        }

        return "redirect:/appealHome";

    }


}


/*
   if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath  + resultFilename));

 */
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        String username = authentication.getName();
//        Object principal = authentication.getPrincipal();
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//
//        System.out.println(username);
//        System.out.println(principal);
//        System.out.println(authorities);
//
