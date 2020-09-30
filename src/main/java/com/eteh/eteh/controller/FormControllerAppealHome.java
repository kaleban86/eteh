package com.eteh.eteh.controller;

import com.eteh.eteh.models.*;
import com.eteh.eteh.repository.*;
import com.eteh.eteh.service.AppealService;
import com.eteh.eteh.service.MailSender;
import com.eteh.eteh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@PreAuthorize("hasAnyAuthority('ADMIN ','SUPER_ADMIN','USER_READING')  ")
@Controller
public class FormControllerAppealHome {

    @Value("${upload.path}")
    private String uploadPath;
    private
    AppealRepository appealRepository;

    private final ChangedControllerAppealEmailSend changedControllerAppealEmailSend;
    private final AppealUadRepo appealUadRepo;
    private final StatusColor statusColor;

    private final AppealFileSave appealFileSave;

    private final ChangedExecutorAppealEmailSend changedExecutorAppealEmailSend;

    private
    AppealService appealService;
    private final AppealStatusRepo appealStatusRepo;
    private final UserService userService;

    private final UserProfileRepo userProfileRepo;

    private final CustomerRepository customerRepository;

    private final UpdateIdRepository updateIdRepository;
    private final MailSender mailSender;
    private final FootingRepo footingRepo;
    private final UserRepository userRepository;
    private final AppealFileRepo appealFileRepo;




    @Autowired
    public FormControllerAppealHome(AppealRepository appealRepository, ChangedControllerAppealEmailSend
            changedControllerAppealEmailSend,
                                    AppealService appealService, AppealUadRepo appealUadRepo,
                                    StatusColor statusColor, AppealFileSave appealFileSave,
                                    ChangedExecutorAppealEmailSend executorAppealEmailSend,
                                    AppealStatusRepo appealStatusRepo, UserProfileRepo userProfileRepo,
                                    UserService userService, CustomerRepository customerRepository,
                                    UpdateIdRepository updateIdRepository,
                                    MailSender mailSender, FootingRepo footingRepo,
                                    UserRepository userRepository, AppealFileRepo appealFileRepo) {
        this.appealRepository = appealRepository;
        this.changedControllerAppealEmailSend = changedControllerAppealEmailSend;

        this.appealService = appealService;
        this.appealUadRepo = appealUadRepo;
        this.statusColor = statusColor;
        this.appealFileSave = appealFileSave;
        this.changedExecutorAppealEmailSend = executorAppealEmailSend;
        this.appealStatusRepo = appealStatusRepo;
        this.userProfileRepo = userProfileRepo;
        this.userService = userService;
        this.customerRepository = customerRepository;
        this.updateIdRepository = updateIdRepository;
        this.mailSender = mailSender;
        this.footingRepo = footingRepo;
        this.userRepository = userRepository;


        this.appealFileRepo = appealFileRepo;
    }


    /** @param  @GetMapping("/appealHome") Домашняя страница  входящее обращение
     *
     */
    @GetMapping("/appealHome")
    public String appealHome(String name, Model model, @AuthenticationPrincipal User user) throws SQLException {



        model.addAttribute("appeal", appealRepository.findAll());


        List<AppealStatus> appealStatusList = appealStatusRepo.findAll();
        model.addAttribute("appealStatusList", appealStatusList);

        Iterable<Customer> customersList = customerRepository.findAll();
        model.addAttribute("customerList", customersList);


        return "/appealHome";

    }

    /** @param @GetMapping("/appeal-delete/{id}") Удалить не используется
     *
     */
    @GetMapping("/appeal-delete/{id}")
    public String deleteBlog(@PathVariable("id") Long id) {
        appealRepository.deleteById(id);
        return "redirect:/appealHome";
    }


    /** @param  @RequestMapping(value = "/appeal-reading/{id}" Страница чтения входящее обращение
     *
     */
    @PreAuthorize("hasAnyAuthority('ADMIN ','SUPER_ADMIN','USER_READING','APPEAL_CREATE')  ")
    @RequestMapping(value = "/appeal-reading/{id}", method = {RequestMethod.GET})
    public String readingAppeal(@PathVariable("id") Long id, Model model,
                                @AuthenticationPrincipal User userUpdate) throws SQLException {
        Appeal appeal = appealRepository.getOne(id);
        model.addAttribute("Update", appeal);


        List<Footing> footings = footingRepo.findAll();
        model.addAttribute("footings", footings);
        List<AppealStatus> appealStatusList = appealStatusRepo.findAll();
        model.addAttribute("appealStatusList", appealStatusList);

        List<UserProfileModels> userId = userProfileRepo.fainBiId(id);
        model.addAttribute("userId", userId);
        List<UserProfileModels> userName = userProfileRepo.userNameMod(id);

        model.addAttribute("userName", userName);
        List<User> user = userService.findAll();
        model.addAttribute("user", user);

        Iterable<Customer> customersList = customerRepository.findAll();
        model.addAttribute("customerList", customersList);


        Long idUserUpdate = userUpdate.getId();

        model.addAttribute("idUpdateAppeal", idUserUpdate);

        List<AppealFile> appealFiles = appealUadRepo.findByIdFile(id);
        model.addAttribute("appealFiles", appealFiles);


        return "/appeal-reading";
    }

    /** @param    @RequestMapping(value = "/appeal/{id}" Страница оновления входящее обращение
     *
     */
    @PreAuthorize("hasAnyAuthority('ADMIN ','SUPER_ADMIN','APPEAL_CREATE')  ")
    @RequestMapping(value = "/appeal/{id}", method = {RequestMethod.GET})
    public String updateAppeal(@PathVariable("id") Long id, Model model,
                               @AuthenticationPrincipal User userUpdate)
            throws SQLException, IOException, ServletException {
        Appeal appeal = appealRepository.getOne(id);
        model.addAttribute("Update", appeal);

        List<UserProfileModels> userId = userProfileRepo.fainBiId(id);
        model.addAttribute("userId", userId);

        List<Footing> footings = footingRepo.findAll();
        model.addAttribute("footings", footings);

        List<AppealStatus> appealStatusList = appealStatusRepo.findAll();
        model.addAttribute("appealStatusList", appealStatusList);

        List<UserProfileModels> userName = userProfileRepo.userNameMod(id);
        model.addAttribute("userName", userName);

        List<User> user = userService.findAll();
        model.addAttribute("user", user);

        Iterable<Customer> customersList = customerRepository.findAll();
        model.addAttribute("customerList", customersList);


        Long idUserUpdate = userUpdate.getId();
        model.addAttribute("idUpdateAppeal", idUserUpdate);

        List<Appeal> appeals = appealUadRepo.getEntityRevisionsById(id);
        model.addAttribute("appeals", appeals);


        List<AppealFile> appealFiles = appealUadRepo.findByIdFile(id);
        model.addAttribute("appealFiles", appealFiles);



        return "/appeal-update";
    }


    /** @param    @RequestMapping(value = "/download-document/{fileName}" Скачать файлы
     *
     */
    @RequestMapping(value = "/download-document/{fileName}", method = RequestMethod.GET)
    public @ResponseBody
    HttpEntity<byte[]> downloadDocument(AppealFile appealFile) throws IOException {


        byte[] document = FileCopyUtils.copyToByteArray(new File(uploadPath + appealFile.getFileName()));

        if (appealFile.getFileName().isEmpty()) {

        }
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "pdf"));
        header.setContentType(new MediaType("application", "doc"));
        header.setContentType(new MediaType("application", "jpg"));
        header.setContentType(new MediaType("application", "rar"));
        header.setContentType(new MediaType("application", "exe"));
        header.set("Content-Disposition", "inline; filename=" + appealFile.getFileName());
        header.setContentLength(document.length);


        return new HttpEntity<byte[]>(document, header);
    }


    /*
    Сохраняем измения
     */
    @RequestMapping("/appeal-update")
    public String saveAppeal(Appeal appeal, Model model, @AuthenticationPrincipal User user,HttpServletRequest request) {
        model.addAttribute("appeal", appealRepository.findAll());

        String keiId= request.getParameter("keiId");


        /*
        Проверка и что поля контролёр и исполнитель
         изменились, и отправляем письмо  в новом потоке

         */


        try {

            new Thread(new Runnable() {
                @Override

                public synchronized void run() {

                    changedControllerAppealEmailSend.checkController(appeal.getId(),
                            appeal.getController().getId(), appeal.getBriefDescription(),
                            appeal.getDataCreation(), appeal.getDataAnswer());

                }
            }).start();

        } catch (Exception e) {

        }


        try {

            new Thread(new Runnable() {
                @Override

                public synchronized void run() {

                    changedExecutorAppealEmailSend.checkExecutor(appeal.getId(),
                            appeal.getExecutor().getId(), appeal.getBriefDescription(),
                            appeal.getDataCreation(), appeal.getDataAnswer());
                }
            }).start();

        } catch (Exception e) {

        }

        if (keiId != null){

            try {
                appealUadRepo.deleteFile(keiId);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


        appealRepository.save(appeal);
        Long id = appeal.getId();
        Long userId = user.getId();
        String lastName = user.getLastName();
        String name = user.getName();


        appeal.getExecutor();


        /*

        Записываем автора который обновил входяшиие обрашение
         */
        updateIdRepository.updateIdAppealAud(id, userId);




        return "redirect:/appealHome";
    }


}
