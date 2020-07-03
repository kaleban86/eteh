package com.eteh.eteh.controller;

import com.eteh.eteh.models.*;
import com.eteh.eteh.repository.*;
import com.eteh.eteh.service.AppealService;
import com.eteh.eteh.service.MailSender;
import com.eteh.eteh.service.UserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@PreAuthorize("hasAnyAuthority('ADMIN ','SUPER_ADMIN','USER_READING')  ")
@Controller
public class FormControllerAppealHome {

    @Value("${upload.path}")
    private String uploadPath;
    private
    AppealRepository appealRepository;

    private final AppealUadRepo appealUadRepo;
    private final StatusColor statusColor;


    private
    AppealService appealService;
    private final AppealStatusRepo appealStatusRepo;
    private final UserService userService;

    private final UserProfileRepo userProfileRepo;

    private final CustomerRepository customerRepository;

    private final UpdateIdRepository updateIdRepository;
    private final MailSender mailSender;
    private final FootingRepo footingRepo;

    @Autowired
    public FormControllerAppealHome(AppealRepository appealRepository, AppealService appealService, AppealUadRepo appealUadRepo, StatusColor statusColor, AppealStatusRepo appealStatusRepo, UserProfileRepo userProfileRepo, UserService userService, CustomerRepository customerRepository, UpdateIdRepository updateIdRepository, MailSender mailSender, FootingRepo footingRepo) {
        this.appealRepository = appealRepository;

        this.appealService = appealService;
        this.appealUadRepo = appealUadRepo;
        this.statusColor = statusColor;
        this.appealStatusRepo = appealStatusRepo;
        this.userProfileRepo = userProfileRepo;
        this.userService = userService;
        this.customerRepository = customerRepository;
        this.updateIdRepository = updateIdRepository;
        this.mailSender = mailSender;
        this.footingRepo = footingRepo;
    }


    @GetMapping("/appealHome")
    public String appealHome(String name, Model model,User user,AppealAud appeal) {
        model.addAttribute("appeal", appealRepository.findAll());
        List<UserProfileModels> userId = userProfileRepo.fainBiId(appeal.getAuthorUpdate());
        model.addAttribute("userId", userId);
        System.out.println(userId );
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd 'и время' hh:mm:ss a zzz");

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());


        return "/appealHome";

    }

    @GetMapping("/appeal-delete/{id}")
    public String deleteBlog(@PathVariable("id") Long id) {
        appealRepository.deleteById(id);
        return "redirect:/appealHome";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN ','SUPER_ADMIN','USER_READING')  ")
    @RequestMapping(value = "/appeal-reading/{id}", method = {RequestMethod.GET})
    public String readingAppeal(@PathVariable("id") Long id, Model model,@AuthenticationPrincipal User userUpdate) throws SQLException {
        Appeal appeal = appealRepository.getOne(id);
        model.addAttribute("Update", appeal);

        List<UserProfileModels> userId = userProfileRepo.fainBiId(id);
        List<Footing> footings = footingRepo.findAll();
        model.addAttribute("footings",footings);
        List<AppealStatus> appealStatusList = appealStatusRepo.findAll();
        model.addAttribute("appealStatusList",appealStatusList);
        model.addAttribute("userId", userId);
        List<UserProfileModels> userName = userProfileRepo.userNameMod(id);

        model.addAttribute("userName", userName);
        List<User> user = userService.findAll();
        model.addAttribute("user", user);

        Iterable<Customer> customersList = customerRepository.findAll();
        model.addAttribute("customerList", customersList);

        List<AppealAud> appealAud = appealUadRepo.faindAllBiUD(id);

        model.addAttribute("appealAud", appealAud);
        Long idUserUpdate = userUpdate.getId();

        model.addAttribute("idUpdateAppeal", idUserUpdate);


        return "/appeal-reading";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN ','SUPER_ADMIN')  ")
    @RequestMapping(value = "/appeal/{id}", method = {RequestMethod.GET})
    public String updateAppeal(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User userUpdate) throws SQLException {
        Appeal appeal = appealRepository.getOne(id);
        model.addAttribute("Update", appeal);

        List<UserProfileModels> userId = userProfileRepo.fainBiId(id);
        List<Footing> footings = footingRepo.findAll();
        model.addAttribute("footings",footings);

        List<AppealStatus> appealStatusList = appealStatusRepo.findAll();
        model.addAttribute("appealStatusList",appealStatusList);

        model.addAttribute("userId", userId);
        List<UserProfileModels> userName = userProfileRepo.userNameMod(id);

        model.addAttribute("userName", userName);
        List<User> user = userService.findAll();
        model.addAttribute("user", user);

        Iterable<Customer> customersList = customerRepository.findAll();
        model.addAttribute("customerList", customersList);

        List<AppealAud> appealAud = appealUadRepo.faindAllBiUD(id);

        model.addAttribute("appealAud", appealAud);
        Long idUserUpdate = userUpdate.getId();

        model.addAttribute("idUpdateAppeal", idUserUpdate);


        return "/appeal-update";
    }


    @RequestMapping(value = "/download-document/{fileName}", method = RequestMethod.GET)
    public @ResponseBody
    HttpEntity<byte[]> downloadDocument(Appeal appeal) throws IOException {


        byte[] document = FileCopyUtils.copyToByteArray(new File(uploadPath + appeal.getFileName()));

        if (appeal.getFileName().isEmpty()) {

        }
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "pdf"));
        header.setContentType(new MediaType("application", "doc"));
        header.setContentType(new MediaType("application", "jpg"));
        header.setContentType(new MediaType("application", "rar"));
        header.setContentType(new MediaType("application", "exe"));
        header.set("Content-Disposition", "inline; filename=" + appeal.getFileName());
        header.setContentLength(document.length);


        return new HttpEntity<byte[]>(document, header);
    }


    @RequestMapping("/appeal-update")
    public String updateBlog(Appeal appeal, Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("appeal", appealRepository.findAll());


        appealRepository.save(appeal);
        Long id = appeal.getId();
        Long userId = user.getId();
        String lastName = user.getLastName();
        String name = user.getName();


        appeal.getExecutor();

        statusColor.colorSearch(appeal.getId(),appeal.getStatus());
        updateIdRepository.updateIdAppealAud(id, userId);


        return "redirect:/appealHome";
    }


}

//    @RequestMapping(value = "/view-document/{fileName}", method = RequestMethod.GET)
//    public @ResponseBody
//    Resource viewDocument(HttpServletResponse response,Appeal appeal) throws FileNotFoundException {
//
//        response.setContentType(uploadPath+appeal.getFileName());
//        response.setHeader("Content-Disposition", "inline; filename=" + uploadPath+appeal.getFileName());
//        response.setHeader("Content-Length", String.valueOf(uploadPath.length()));
//        return new FileSystemResource(uploadPath+appeal.getFileName());
//    }