package com.eteh.eteh.controller;


import com.eteh.eteh.models.*;
import com.eteh.eteh.repository.*;
import com.eteh.eteh.service.AppealService;
import com.eteh.eteh.service.MailSender;
import com.eteh.eteh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
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
import java.util.*;

@PreAuthorize("hasAnyAuthority('APPEAL_CREATE','SUPER_ADMIN')  ")
@Controller
public class FormControllerAppeal {


    /**
     *
     * Путь для храннеия файлов на сервере
     */
    @Value("${upload.path}")

    /**
     * Внедренные зависимости
     */
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
    private final AppealFileSave appealFileSave;

    private final AppealFileRepo appealFileRepo;
    private final UpdateIdRepository updateIdRepository;

    private final AppealService appealService;


    /**
     * @param mailSender Отправляем почту
     * @param userRepository Репозиторий пользователей
     * @param customerRepository Репозиторий покупателей
     * @param appealStatusRepo Репозиторий статус
     * @param footingRepo Репозиторий основание
     * @param statusColor Репозиторий цвет статуса
     * @param colorIdRepo
     * @param userProfileRepo Репозиторий профиль пользователей
     * @param appealRepository Репозиторий входящие обращения
     * @param userService
     * @param appealFileSave
     * @param appealFileRepo1
     * @param updateIdRepository
     * @param appealService
     */
    @Autowired
    public FormControllerAppeal(MailSender mailSender, UserRepository userRepository, CustomerRepository customerRepository, AppealStatusRepo appealStatusRepo, FootingRepo footingRepo, StatusColor statusColor, ColorIdRepo colorIdRepo, UserProfileRepo userProfileRepo, AppealRepository appealRepository, UserService userService, AppealFileSave appealFileSave, AppealFileRepo appealFileRepo1, UpdateIdRepository updateIdRepository, AppealService appealService) {
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
        this.appealFileSave = appealFileSave;
        this.appealFileRepo = appealFileRepo1;

        this.updateIdRepository = updateIdRepository;
        this.appealService = appealService;
    }

    /**
     * Страница новое обращение
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


    /**
     * Параметры   новое входящие обращения
     * @param briefDescription Короткое описание
     * @param user пользователь
     * @param footing основание
     * @param text текст
     * @param executor иполнитель
     * @param controller контралёр
     * @param status статус
     * @param surname не используется
     * @param dataCreation дата создания
     * @param dataAnswer дата ответа
     * @param nameCompany название компании
     * @param address адрес
     * @param tel телефон
     * @param emailAddress почта
     * @param authorUpdate автор изменнений
     * @param color цвет
     * @return
     * @throws IOException
     * @throws SQLException
     */
    @PostMapping("/appeal/appalAdd")
    public String appealAdd(@RequestParam("file") MultipartFile  file,
                            RedirectAttributes redirectAttributes, HttpServletRequest request,
                            @RequestParam String briefDescription,
                            @AuthenticationPrincipal User user,
                            @RequestParam Footing footing,
                            @RequestParam String text,
                            @RequestParam User executor,
                            @RequestParam User controller,
                            @RequestParam AppealStatus status,
                            @RequestParam String surname,
                            @RequestParam(required = false) java.sql.Date dataCreation,
                            @RequestParam java.sql.Date dataAnswer,
                            @RequestParam Customer nameCompany,
                            @RequestParam String address,
                            @RequestParam String tel,
                            @RequestParam String emailAddress,
                            @RequestParam User authorUpdate,
                            @RequestParam(required = false) ColorStatusId color) throws IOException, SQLException, ServletException {


        Appeal appeal = new Appeal(user, briefDescription, footing,
                text, executor, controller, status, surname,
                dataAnswer, dataCreation, nameCompany, address, tel, emailAddress, authorUpdate, color);


        appeal.getId();


        /**
         *  Сохраняем новое обрашение
         */


        appealRepository.save(appeal);
        updateIdRepository.updateIdAppealAuthor(appeal.getId(),user.getId());



        /**
         * Отправляем письмо пользователям контролёр и исполнитель, в новом потоке
         */
        Long executorId = executor.getId();

        try {

            new Thread(new Runnable() {
                @Override
                public synchronized void run() {
                    userProfileRepo.userEmailIdSendExecutor(controller.getId(), appeal.getId(),
                            appeal.getBriefDescription(),
                            appeal.getDataCreation(), appeal.getDataAnswer());
                    userProfileRepo.userEmailIdSendController(executorId, appeal.getId(),
                            appeal.getBriefDescription(), appeal.getDataCreation(),
                            appeal.getDataAnswer());
                }
            }).start();

        } catch (Exception e) {

        }

        /**
         *  Сохраняем файлы
         */
        appealFileSave.saveFile(file,redirectAttributes,request,appeal.getId());




        return "redirect:/appealHome";
    }


}
/*
   String Data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        Collection<Part> parts = request.getParts();
        String[] names = new String[parts.size()];
        int i = 0;
        for (Part part : parts) {
            BufferedInputStream in = new BufferedInputStream(part.getInputStream());
            byte[] data = new byte[in.available()];
            in.read(data, 0, data.length);
            String fileName = part.getSubmittedFileName();
            Path path = Paths.get(uploadPath + fileName);
            try {

                Files.write(path, data);
            }catch (Exception e){


            }

            names[i++] = fileName;

            if (part.getSubmittedFileName()== null) {






            } else {

                try {

                    new Thread(new Runnable() {
                        @Override


                        public synchronized void run() {

                            String uuidFile = UUID.randomUUID().toString();


                            appealFileSave.saveDbFile(part.getSubmittedFileName(), data.length, Data, appeal.getId(), uuidFile);



                        }
                    }).start();

                }catch (Exception e){

                }




            }



        }
 */

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
