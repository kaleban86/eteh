package com.eteh.eteh.controller;


import com.eteh.eteh.models.User;
import com.eteh.eteh.models.UserProfileModels;
import com.eteh.eteh.repository.AppealRepository;
import com.eteh.eteh.repository.AppealUadRepo;
import com.eteh.eteh.repository.UserProfileRepo;
import com.eteh.eteh.service.MailSender;
import com.eteh.eteh.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FormControllerTest {



    @Value("${upload.path}")
    private String uploadPath;
    private static final String FILE_PATH = "/home/nikolai/example.pdf";
    private static final String APPLICATION_PDF = "application/pdf";

    final
    MailSender mailSender;
    final
    UserService userService;
    private final UserProfileRepo userProfileRepo;
    private final AppealUadRepo appealUadRepo;
    private final AppealRepository appealRepository;
    public FormControllerTest(UserService userService, MailSender mailSender, UserProfileRepo userProfileRepo, AppealUadRepo appealUadRepo, AppealRepository appealRepository) {
        this.userService = userService;
        this.mailSender = mailSender;
        this.userProfileRepo = userProfileRepo;
        this.appealUadRepo = appealUadRepo;
        this.appealRepository = appealRepository;
    }



    public String test(String name, Model model){


        model.addAttribute("name", name);


        return "/test";

    }

    @RequestMapping("/test")
    public String test(String name, Model model,@AuthenticationPrincipal User user){
       List<User> user1 = userService.findAll();
       model.addAttribute("user", user);
        Long userId1 = user.getId();
        List<UserProfileModels> userId = userProfileRepo.fainBiId(userId1);
        model.addAttribute("appeal", appealRepository.findAll());
        model.addAttribute("userId", userId);



       return "2";


    }

//   / / 3.1.2 Загрузка нескольких файлов
    @PostMapping("/api/upload/multi")
    public ResponseEntity uploadFileMulti(
            @RequestParam("extraField") String extraField,
            @RequestParam("files") MultipartFile[] uploadfiles) {



// Получить имя файла
        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {

            saveUploadedFiles(Arrays.asList(uploadfiles));

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - "
                + uploadedFileName, HttpStatus.OK);

    }

    // 3.1.3 отображает HTML-форму в модель
    @PostMapping("/api/upload/multi/model")
    public ResponseEntity multiUploadFileModel(@ModelAttribute Model model) {


        //  saveUploadedFiles(Arrays.asList(model.getFiles()));

        return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);

    }

    //сохранить файл
    private void saveUploadedFiles(List files) throws IOException {

//        for (MultipartFile file : files) {
//
//            if (file.isEmpty()) {
//                continue; // следующий пожалуйста
//            }
//
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(uploadPath + file.getOriginalFilename());
//            Files.write(path, bytes);
//
//        }

    }



    @RequestMapping(value = "/a{id}", method = RequestMethod.GET, produces = APPLICATION_PDF)
    public @ResponseBody
    void downloadA(HttpServletResponse response, @PathVariable("id") Long id, Model model) throws IOException, SQLException {
        File file = getFile();
//        List<AppealAud> appealAud = appealUadRepo.faindAllBiUD(id);
        InputStream in = new FileInputStream(file);

//        response.setContentType(String.valueOf(appealAud));
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        FileCopyUtils.copy(in, response.getOutputStream());
    }

    @RequestMapping(value = "/b", method = RequestMethod.GET, produces = APPLICATION_PDF)
    public @ResponseBody
    HttpEntity<byte[]> downloadB() throws IOException {
        File file = getFile();
        byte[] document = FileCopyUtils.copyToByteArray(file);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "pdf"));
        header.set("Content-Disposition", "inline; filename=" + file.getName());
        header.setContentLength(document.length);

        return new HttpEntity<byte[]>(document, header);
    }

    @RequestMapping(value = "/c", method = RequestMethod.GET, produces = APPLICATION_PDF)
    public @ResponseBody
    Resource downloadC(HttpServletResponse response) throws FileNotFoundException {
        File file = getFile();
        response.setContentType(APPLICATION_PDF);
        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        return new FileSystemResource(file);
    }

    private File getFile() throws FileNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()){
            throw new FileNotFoundException("file with path: " + FILE_PATH + " was not found.");
        }
        return file;
    }
}
