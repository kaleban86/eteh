package com.eteh.eteh.repository;

import com.eteh.eteh.models.UserProfileModels;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserProfileRepo {


    private DataSource dataSource;



    UserProfileRepo userProfile;

    public UserProfileRepo(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public List<UserProfileModels> fainBiId(Long id) {
        try (Connection c = dataSource.getConnection()) {


            String sql = "select last_name,first_name,name\n" +
                    "from user where id  in (\n" +
                    "    select user_id from appeal where id = ?);";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();

            List<UserProfileModels> userProfiles = new ArrayList<>();
            while (resultSet.next()) {

                String lastName = resultSet.getString("Last_Name");
                //  String username = resultSet.getString("username");
                String firstName = resultSet.getString("first_name");
                String name = resultSet.getString("name");


                UserProfileModels userProfile = new UserProfileModels();
                userProfile.setLastName(lastName);
                // userProfileModels.setUsername(username);
                userProfile.setName(name);
                userProfile.setFirstName(firstName);


                userProfiles.add(userProfile);
            }
            return userProfiles;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public List<UserProfileModels> userNameMod(Long id) {
        try (Connection c = dataSource.getConnection()) {

            String sql = "select last_name,first_name,name\n" +
                    "from user where id  in (\n" +
                    "    select user_id from appeal_aud where id = ? );";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();

            List<UserProfileModels> userProfiles = new ArrayList<>();
            while (resultSet.next()) {

                String lastName = resultSet.getString("Last_Name");
                //  String username = resultSet.getString("username");
                String firstName = resultSet.getString("first_name");
                String name = resultSet.getString("name");


                UserProfileModels userProfile = new UserProfileModels();
                userProfile.setLastName(lastName);
                // userProfileModels.setUsername(username);
                userProfile.setName(name);
                userProfile.setFirstName(firstName);


                userProfiles.add(userProfile);
            }
            return userProfiles;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public List<UserProfileModels> userNameModID(Long id) {
        try (Connection c = dataSource.getConnection()) {

            String sql = "select last_name,first_name,name\n" +
                    "from user where id  in (\n" +
                    "    select user_id from appeal_aud where id = ? );";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();

            List<UserProfileModels> userProfiles = new ArrayList<>();
            while (resultSet.next()) {

                String lastName = resultSet.getString("Last_Name");
                //  String username = resultSet.getString("username");
                String firstName = resultSet.getString("first_name");
                String name = resultSet.getString("name");


                UserProfileModels userProfile = new UserProfileModels();
                userProfile.setLastName(lastName);
                // userProfileModels.setUsername(username);
                userProfile.setName(name);
                userProfile.setFirstName(firstName);


                userProfiles.add(userProfile);
            }
            return userProfiles;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }





}