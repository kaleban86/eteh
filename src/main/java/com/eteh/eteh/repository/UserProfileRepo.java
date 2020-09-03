package com.eteh.eteh.repository;

import com.eteh.eteh.controller.FormControllerAppeal;
import com.eteh.eteh.models.Appeal;
import com.eteh.eteh.models.UserProfileModels;
import com.eteh.eteh.service.MailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserProfileRepo {

    @Value("${UrlEmail}")
    private String UrlEmail;

    private DataSource dataSource;

    private final MailSender mailSender;
    private Appeal appeal;

    UserProfileRepo userProfile;

    public UserProfileRepo(DataSource dataSource, MailSender mailSender) {
        this.dataSource = dataSource;
        this.mailSender = mailSender;
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


    public void userEmailIdSendController(Long id,Long appealId, String BriefDescription,Date DataCreation , Date DataAnswer ) {
        try (Connection c = dataSource.getConnection()) {

            String sql = "select email from user where id = ?";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();


            while (resultSet.next()) {

                String emailExecutor = resultSet.getString("email");


                sendEmailAppealReadingController(emailExecutor,appealId,BriefDescription,DataCreation,DataAnswer);


                break;

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    public void userEmailIdSendExecutor(Long id,Long appealId, String BriefDescription,Date DataCreation , Date DataAnswer ) {
        try (Connection c = dataSource.getConnection()) {

            String sql = "select email from user where id = ?";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();


            while (resultSet.next()) {

                String emailExecutor = resultSet.getString("email");


                sendEmailAppealReadingExecutor(emailExecutor,appealId,BriefDescription,DataCreation,DataAnswer);


                break;

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public  void sendEmailAppealReadingExecutor(String emailExecutor,Long appealId, String BriefDescription,Date DataCreation , Date DataAnswer) {

        String message = String.format(
                "Новое входящие обращение: №  "  +appealId+ "\n"+


                        "Краткое описание:  " + BriefDescription+ "\n"+

                        "Дата создания: " +DataCreation+"\n"+

                        "Дата закрытия: " +DataAnswer+"\n"+

                        UrlEmail+appealId

        );


        mailSender.send(emailExecutor, "Вы назначены контролёром ", message);



    }
    public  void sendEmailAppealReadingController(String emailController,Long appealId, String BriefDescription,Date DataCreation , Date DataAnswer) {

        String message = String.format(
                "Новое входящие обращение: № "  +appealId+ "\n"+


                        "Краткое описание: " + BriefDescription+ "\n"+

                        "Дата создания: " +DataCreation+"\n"+

                        "Дата закрытия: " +DataAnswer+"\n"+

                        UrlEmail+appealId

        );




        mailSender.send(emailController, "Вы назначены исполнителем ", message);
    }





}

