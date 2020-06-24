package com.eteh.eteh.repository;

import com.eteh.eteh.models.Appeal;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class UpdateIdRepository {


    private DataSource dataSource;
    


    UpdateIdRepository updateIdRepository;
    public UpdateIdRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void updateIdAppeal(Long id,Long userId) {
        try (Connection c = dataSource.getConnection()) {

            PreparedStatement statement = c.prepareStatement("update  appeal set user_id = ? where id = ?");

            statement.setLong(1, userId);
            statement.setLong(2, id);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("updated successfully!");
            }


        } catch (Exception ex) {
            throw new RuntimeException(ex);

        }

    }

    public void updateIdAppealAud(Long id,String userId) {
        try (Connection c = dataSource.getConnection()) {

            PreparedStatement statement = c.prepareStatement("UPDATE appeal_aud SET author_update= ? where rev = " +
                    "(SELECT max FROM (SELECT MAX(rev) as max FROM appeal_aud WHERE id= ?) AS t2 )");

            statement.setString(1, userId);
            statement.setLong(2, id);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("updated successfully!");
            }


        } catch (Exception ex) {
            throw new RuntimeException(ex);

        }

    }



}
