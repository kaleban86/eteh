package com.eteh.eteh.repository;

import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;

@Service
public class StatusColor {

    private DataSource dataSource;



    UpdateIdRepository updateIdRepository;
    public StatusColor(DataSource dataSource) {
        this.dataSource = dataSource;
    }




    public Long colorSearch(Long id,Long Status) {
        try (Connection c = dataSource.getConnection()) {

            String sql = "select color from appeal_status where id = ?";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setObject(1, Status);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();

            while (resultSet.next()) {

                String color = resultSet.getString("color");



                updateStatusColor(color,id);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void updateStatusColor(String color,Long id) {
        try (Connection c = dataSource.getConnection()) {

            PreparedStatement statement = c.prepareStatement("UPDATE appeal SET color= ? where appeal.id = ?");

            statement.setString(1, color);

            statement.setLong(2,id );

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("updated successfully!");
            }


        } catch (Exception ex) {
            throw new RuntimeException(ex);

        }

    }

}
