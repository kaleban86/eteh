package com.eteh.eteh.repository;

import com.eteh.eteh.models.AppealAud;
import com.eteh.eteh.models.UserProfileModels;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AppealUadRepo {

    private final DataSource dataSource;

    public AppealUadRepo(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<AppealAud> faindAllBiUD(Long id) throws SQLException {

        try (Connection c  = dataSource.getConnection() ) {

            String sql = "select   * from appeal_aud where id = ?";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();

            List<AppealAud> appealAuds = new ArrayList<>();
            while (resultSet.next()) {

                int rev = resultSet.getInt("rev");
                String briefDescription = resultSet.getString("brief_Description");
                String footing = resultSet.getString("footing");
                String text = resultSet.getString("text");
                String executor = resultSet.getString("executor");
                String controller = resultSet.getString("controller");
                Long status = resultSet.getLong("status");
                String surname = resultSet.getString("surname");
                String lastName = resultSet.getString("last_Name");
                String nameCompany = resultSet.getString("name_Company");
                String tel = resultSet.getString("tel");
                String address = resultSet.getString("address");
                String emailAddress = resultSet.getString("email_Address");
                Date dataChange = resultSet.getDate("data_Change");
                Date dataCreation = resultSet.getDate("data_creation");
                Date dataAnswer = resultSet.getDate("data_answer");
                int userId = resultSet.getInt("user_id");
                Long authorUpdate = resultSet.getLong("author_update");
                String fileName = resultSet.getString("file_name");
                String author = resultSet.getString("author_update_history");


                AppealAud appealAud = new AppealAud();
                appealAud.setAddress(address);
                appealAud.setBriefDescription(briefDescription);
                appealAud.setFooting(footing);
                appealAud.setTel(tel);
                appealAud.setText(text);
                appealAud.setExecutor(executor);
                appealAud.setController(controller);
                appealAud.setRev(rev);
                appealAud.setStatus(status);
                appealAud.setSurname(surname);
                appealAud.setLastName(lastName);
                appealAud.setNameCompany(nameCompany);
                appealAud.setEmailAddress(emailAddress);
                appealAud.setDataChange(dataChange);
                appealAud.setDataCreation(dataCreation);
                appealAud.setDataAnswer(dataAnswer);
                appealAud.setUserId(userId);
                 appealAud.setAuthorUpdate(authorUpdate);
                appealAud.setFileName(fileName);




//                sql = "select last_name,first_name,name  from user where id = ?";
//                preparedStatement = c.prepareStatement(sql);
//                preparedStatement.setObject(1, controller);
//                resultSet = preparedStatement.executeQuery();
//                resultSetMetaData = resultSet.getMetaData();
//                int n1 = resultSetMetaData.getColumnCount();
//
//                List<AppealAud> appealAuds1 = new ArrayList<>();
//                while (resultSet.next()) {
//
//
//                    String lastNameHistory = resultSet.getString("Last_Name");
//                    String firstNameHistory = resultSet.getString("first_name");
//                    String nameHistory = resultSet.getString("name");
//
//
//
//
//
//
//
//
//                    appealAud.setAuthorUpdateHistory(lastNameHistory+ " " + nameHistory);
                    appealAuds.add(appealAud);

                }


                return appealAuds;
            }
        }






    public List<AppealAud> userNameModIDEnvers(Long id) {
        try (Connection c = dataSource.getConnection()) {

            String sql = "select rev from appeal_aud where id = ?;";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();


            List<UserProfileModels> revIdGet = new ArrayList<>();
            while (resultSet.next()) {

                int rev = resultSet.getInt("rev");

                AppealAud appealAud = new AppealAud();




                appealAud.setRev(rev);

                userNameModIDEnversR(rev);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }



    public List<AppealAud> userNameModIDEnversR(int id) {
        try (Connection c = dataSource.getConnection()) {

            String sql = "select user_id from appeal_aud where rev = ?;";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();


            List<UserProfileModels> revIdGet = new ArrayList<>();
            while (resultSet.next()) {


                int userId = resultSet.getInt("user_id");

                AppealAud appealAud = new AppealAud();

                appealAud.setUserId(userId);

                userNameId(userId);


            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    public List<AppealAud> userNameId(int id) {
        try (Connection c = dataSource.getConnection()) {

            String sql = "select last_name,first_name,name  from user where id = ?;";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();


            List<UserProfileModels> revIdGet = new ArrayList<>();
            while (resultSet.next()) {


                String lastName = resultSet.getString("Last_Name");
                String firstName = resultSet.getString("first_name");
                String name = resultSet.getString("name");


                UserProfileModels userProfile = new UserProfileModels();
                userProfile.setLastName(lastName);
                userProfile.setName(name);
                userProfile.setFirstName(firstName);


            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    public void updateIdAppealAud(Long id,Long userId) {
        try (Connection c = dataSource.getConnection()) {

            PreparedStatement statement = c.prepareStatement("UPDATE appeal_aud SET author_update= ? where rev = " +
                    "(SELECT max FROM (SELECT MAX(rev) as max FROM appeal_aud WHERE id= ?) AS t2 )");

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
}
