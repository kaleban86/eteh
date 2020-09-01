package com.eteh.eteh.controller;

import com.eteh.eteh.models.*;
import com.eteh.eteh.repository.*;
import com.eteh.eteh.service.MailSender;
import com.eteh.eteh.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class ServiceNote {

    @Value("${UrlEmailNote}")
    private String UrlEmailNote;

    private final UserService userService;

    private final ServiceNoteRepo serviceNoteRepo;
    private final UserProfileRepo userProfileRepo;
    private final AppealFileSave appealFileSave;
    private final AppealUadRepo appealUadRepo;
    private final ServiceNoteAudRepo serviceNoteAudRepo;
    private final MailSender mailSender;
    public ServiceNote(UserService userService, ServiceNoteRepo serviceNoteRepo, UserProfileRepo userProfileRepo, AppealFileSave appealFileSave, AppealUadRepo appealUadRepo, ServiceNoteAudRepo serviceNoteAudRepo, MailSender mailSender) {
        this.userService = userService;
        this.serviceNoteRepo = serviceNoteRepo;
        this.userProfileRepo = userProfileRepo;
        this.appealFileSave = appealFileSave;

        this.appealUadRepo = appealUadRepo;
        this.serviceNoteAudRepo = serviceNoteAudRepo;
        this.mailSender = mailSender;
    }




    @GetMapping("/service-home")
    public String serviceNoteHome(String name, Model model){

        model.addAttribute("serviceNote", serviceNoteRepo.findAll());
        List<User> user = userService.findAll();
        model.addAttribute("user", user);
        return "/service-home";
    }

    @GetMapping("/service-note")
    public String serviceNote(String name, org.springframework.ui.Model model){

        model.addAttribute("name", name);
        List<User> user = userService.findAll();
        model.addAttribute("user", user);

        return "/service-note";
    }

    @PostMapping("/service-note/add")
    public String serviceNote(String name, org.springframework.ui.Model model, @RequestParam("file") MultipartFile file,
                              RedirectAttributes redirectAttributes, HttpServletRequest request,
                              @RequestParam String briefDescription,
                              @AuthenticationPrincipal User user,
                              @RequestParam String mainText,
                              @RequestParam String commentsText,
                              @RequestParam User executor,
                              @RequestParam User controller) throws IOException, ServletException {


        com.eteh.eteh.models.ServiceNote serviceNote = new com.eteh.eteh.models.ServiceNote(briefDescription,user,mainText,commentsText,executor,controller);
       serviceNoteRepo.save(serviceNote);



      String emailExecutor =executor.getEmail();
      String emailController = controller.getEmail();

        String message = String.format(
                "Новая служебная записка: №  " + serviceNote.getId() + "\n" +


                        "Краткое описание:  " + serviceNote.getBriefDescription() + "\n" +

                        "Дата создания: " + serviceNote.getDataCreation()+ "\n" +

                        UrlEmailNote + serviceNote.getId()



        );


        try {

            new Thread(new Runnable() {
                @Override

                public synchronized void run() {
                    mailSender.send(emailExecutor,"Вы назначены исполнителем ",message);
                    mailSender.send(emailController,"Вы назначены контролёром ",message);

                }
            }).start();

        } catch (Exception e) {

        }



        try {

           new Thread(new Runnable() {
               @Override

                public synchronized void run() {
                   try {
                       appealFileSave.saveFile(file,redirectAttributes,request,serviceNote.getId());
                   } catch (IOException e) {
                       e.printStackTrace();
                   } catch (ServletException e) {
                       e.printStackTrace();
                   }

               }
            }).start();

       } catch (Exception e) {

        }


        return "redirect:/service-home";
    }

    @GetMapping("/service-update")
    public String serviceUpdate(String name, org.springframework.ui.Model model){

        model.addAttribute("name", name);
        List<User> user = userService.findAll();
        model.addAttribute("user", user);

        return "/service-update";
    }

    @RequestMapping(value = "/service-update/{id}", method = {RequestMethod.GET})
    public String updateNote(@PathVariable("id") Long id, Model model,
                               @AuthenticationPrincipal User userUpdate)
            throws SQLException, IOException, ServletException {

        com.eteh.eteh.models.ServiceNote serviceNote  = serviceNoteRepo.getOne(id);
        model.addAttribute("serviceNote", serviceNote);
        List<User> user = userService.findAll();
        model.addAttribute("user", user);

        List<UserProfileModels> userId = userProfileRepo.fainBiId(id);
        model.addAttribute("userId", userId);

        List<AppealFile> appealFiles = appealUadRepo.findByIdFile(id);
        model.addAttribute("appealFiles", appealFiles);

        List<com.eteh.eteh.models.ServiceNote> serviceNoteAud = serviceNoteAudRepo.getEntityRevisionsByIdNote(id);
        model.addAttribute("serviceNoteAud", serviceNoteAud);

        return "/service-update";
    }

    @RequestMapping("/service-update")
    public String saveNote(com.eteh.eteh.models.ServiceNote serviceNote, Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("serviceNote",serviceNoteRepo .findAll());

        serviceNoteRepo.save(serviceNote);

        /*
        Проверка и что поля контролёр и исполнитель
         изменились, и отправляем письмо  в новом потоке

         */

//
//        try {
//
//            new Thread(new Runnable() {
//                @Override
//
//                public synchronized void run() {
//
//                    changedControllerAppealEmailSend.checkController(appeal.getId(),
//                            appeal.getController().getId(), appeal.getBriefDescription(),
//                            appeal.getDataCreation(), appeal.getDataAnswer());
//                    changedExecutorAppealEmailSend.checkExecutor(appeal.getId(),
//                            appeal.getExecutor().getId(), appeal.getBriefDescription(),
//                            appeal.getDataCreation(), appeal.getDataAnswer());
//                }
//            }).start();
//
//        } catch (Exception e) {
//
//        }
//
//
//        appealRepository.save(appeal);
//        Long id = appeal.getId();
//        Long userId = user.getId();
//        String lastName = user.getLastName();
//        String name = user.getName();
//
//
//        appeal.getExecutor();
//
//
//        /*
//
//        Записываем автора который обновил входяшиие обрашение
//         */




        return "redirect:/service-home";
    }


}
