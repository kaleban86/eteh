package com.eteh.eteh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

@Controller
public class UploadController {

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "/home/nikolai/temp/";

    @GetMapping("/downloaded-file")
    public String index() {
        return "downloaded-file";
    }
    @RequestMapping(value = "/upload", method = {RequestMethod.POST})
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, HttpServletRequest request) throws IOException, ServletException {

        Collection<Part> parts = request.getParts();
        String[] names = new String[parts.size()];
        int i = 0;
        for (Part part : parts) {
            BufferedInputStream in = new BufferedInputStream(part.getInputStream());
            byte[] data = new byte[in.available()];
            in.read(data, 0, data.length);
            String fileName = part.getSubmittedFileName();
            Path path = Paths.get(UPLOADED_FOLDER + fileName);
           // Files.write(path, data);
            names[i++] = fileName;
            System.out.println("---------------------");
            System.out.println("ContentType=" + part.getContentType());
            System.out.println(part.getSubmittedFileName());
            System.out.println("---------------------");
        }

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded '" + Arrays.toString(names) + "'");

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
}
