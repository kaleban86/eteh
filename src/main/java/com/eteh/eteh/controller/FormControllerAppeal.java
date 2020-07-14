package com.eteh.eteh.controller;


import com.eteh.eteh.models.*;
import com.eteh.eteh.repository.*;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@PreAuthorize("hasAnyAuthority('APPEAL_CREATE','SUPER_ADMIN')  ")
@Controller
public class FormControllerAppeal {


    @Value("${upload.path}")
    private String uploadPath;

    private final MailSender mailSender;

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final AppealStatusRepo appealStatusRepo;
    private final FootingRepo footingRepo;
    private final StatusColor statusColor;
    private final ColorIdRepo colorIdRepo;
    private final UserProfileRepo userProfileRepo;
    private final AppealRepository appealRepository;
    private UserService userService;

    @Autowired
    public FormControllerAppeal(MailSender mailSender, UserRepository userRepository, CustomerRepository customerRepository, AppealStatusRepo appealStatusRepo, FootingRepo footingRepo, StatusColor statusColor, ColorIdRepo colorIdRepo, UserProfileRepo userProfileRepo, AppealRepository appealRepository, UserService userService) {
        this.mailSender = mailSender;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.appealStatusRepo = appealStatusRepo;
        this.footingRepo = footingRepo;
        this.statusColor = statusColor;
        this.colorIdRepo = colorIdRepo;
        this.userProfileRepo = userProfileRepo;
        this.appealRepository = appealRepository;
        this.userService = userService;
    }

    /*
    Страница новое обращение
     */

    @GetMapping("/appeal")
    public String appeal(String name, Model model) {
        model.addAttribute("name", name);
        List<User> user = userService.findAll();
        model.addAttribute("user", user);
        Iterable<Customer> customersList = customerRepository.findAll();
        model.addAttribute("customerList", customersList);
        List<AppealStatus> appealStatusList = appealStatusRepo.findAll();
        model.addAttribute("appealStatusList", appealStatusList);
        List<Footing> footings = footingRepo.findAll();
        model.addAttribute("footings", footings);

        List<ColorStatusId> colorId = colorIdRepo.findAll();
        model.addAttribute("colorId", colorId);

        return "appeal";
    }



    /*

   Данные для bd
     */
    @PostMapping("/appeal/appalAdd")
    public String appealAdd(@RequestParam String briefDescription,
                            @AuthenticationPrincipal User user,
                            @RequestParam("file") MultipartFile[] file,
                            @RequestParam("file2") MultipartFile[] file2,
                            @RequestParam Long footing,
                            @RequestParam String text,
                            @RequestParam User executor,
                            @RequestParam Long controller,
                            @RequestParam AppealStatus status,
                            @RequestParam String surname,
                            @RequestParam(required = false) String lastName,
                            @RequestParam(required = false) java.sql.Date dataCreation,
                            @RequestParam java.sql.Date dataAnswer,
                            @RequestParam Customer nameCompany,
                            @RequestParam String address,
                            @RequestParam String tel,
                            @RequestParam String emailAddress,
                            @RequestParam Long authorUpdate,
                            @RequestParam(required = false) ColorStatusId color) throws IOException, SQLException {


        Long lastNameAuthorUpdate = user.getId();
        Long nameAuthorUpdate = user.getId();


        authorUpdate = user.getId();




        Appeal appeal = new Appeal(user, briefDescription, footing,
                text, executor, controller, status, surname,
                lastName, dataAnswer, dataCreation, nameCompany, address, tel, emailAddress, authorUpdate, color);


        appeal.getId();


        for (MultipartFile file1 : file) {

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
                file1.transferTo(new File(uploadPath + resultFilename));


            }


            for (MultipartFile file3 : file2) {

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


        /*
        Сохраняем новое обрашение
         */
        appealRepository.save(appeal);



        /*
        Отправляем письмо пользователям контролёр и исполнитель, в новом потоке
         */

     Long executorId =    executor.getId();
        try {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    userProfileRepo.userEmailIdSendExecutor(controller,appeal.getId(),
                            appeal.getBriefDescription(),
                            appeal.getDataCreation(),appeal.getDataAnswer());
                    userProfileRepo.userEmailIdSendController(executorId,appeal.getId(),
                            appeal.getBriefDescription(),appeal.getDataCreation(),
                            appeal.getDataAnswer());
                }
            }).start();

        }catch (Exception e){

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
