package com.eteh.eteh.controller;


import com.eteh.eteh.models.Appeal;
import com.eteh.eteh.repository.*;
import com.eteh.eteh.service.MailSender;
import com.eteh.eteh.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
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
    private final UserRepository userRepository;

    private final AppealFileSave appealFileSave;
    private final AppealStatusRepo appealStatusRepo;
    private final CustomerRepository customerRepository;
    public FormControllerTest(UserService userService, MailSender mailSender, UserProfileRepo userProfileRepo, AppealUadRepo appealUadRepo, AppealRepository appealRepository, UserRepository userRepository, AppealFileSave appealFileSave, AppealStatusRepo appealStatusRepo, CustomerRepository customerRepository) {
        this.userService = userService;
        this.mailSender = mailSender;
        this.userProfileRepo = userProfileRepo;
        this.appealUadRepo = appealUadRepo;
        this.appealRepository = appealRepository;
        this.userRepository = userRepository;
        this.appealFileSave = appealFileSave;
        this.appealStatusRepo = appealStatusRepo;
        this.customerRepository = customerRepository;
    }



    public String test(String name, Model model){


        model.addAttribute("name", name);


        return "/test";

    }

    @RequestMapping(value = "/test", method = {RequestMethod.GET})
 //    @GetMapping("/test")
    public String test(@RequestParam("file") MultipartFile  file,
                       RedirectAttributes redirectAttributes, HttpServletRequest request, Model model, Appeal appeal) throws SQLException, IOException, ServletException {


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
             } catch (Exception e) {


             }

             names[i++] = fileName;

             if (part.getSubmittedFileName() == null) {


             } else {

                 try {

                     new Thread(new Runnable() {
                         @Override


                         public synchronized void run() {

                             String uuidFile = UUID.randomUUID().toString();




                         }
                     }).start();

                 } catch (Exception e) {

                 }


             }


         }

        return "/test";


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
