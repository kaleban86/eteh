package com.eteh.eteh.repository;

import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AppealFileSave {

    private final DataSource dataSource;

    public AppealFileSave(DataSource dataSource) {
        this.dataSource = dataSource;
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
