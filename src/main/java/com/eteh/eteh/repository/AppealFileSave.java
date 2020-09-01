package com.eteh.eteh.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Service
public class AppealFileSave {

    private final DataSource dataSource;

    public AppealFileSave(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Value("${upload.path}")


    private String uploadPath;

    public void saveFile(@RequestParam("file") MultipartFile file,
                         RedirectAttributes redirectAttributes, HttpServletRequest request,Long appealID) throws IOException, ServletException {
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


                            saveDbFile(part.getSubmittedFileName(), data.length, Data, appealID, uuidFile);


                        }
                    }).start();

                } catch (Exception e) {

                }


            }


        }
    }


    public boolean saveDbFile(String fileName, int size, String upload_date,Long idFile,String keiId )  {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try (Connection c = dataSource.getConnection()) {
            String sql = "INSERT INTO appeal_file (file_name,size,upload_date,id_File,kei_Id) values (?,?,?,?,?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, fileName );
            ps.setInt(2, size);
            Date date = format.parse(String.valueOf(upload_date));
            ps.setTimestamp(3, new Timestamp(date.getTime()));
            ps.setLong(4, idFile);
            ps.setString(5, keiId);
            ps.execute();
        } catch (Exception ex) {
            throw new RuntimeException(ex);

        }


        return false;
    }
}
