package com.eteh.eteh.repository;


import com.eteh.eteh.models.Appeal;
import com.eteh.eteh.service.MailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;

@Service
public class ChangedExecutorAppealEmailSend {

    @Value("${UrlEmail}")
    private String UrlEmail;

    private DataSource dataSource;
    private final MailSender mailSender;
    private Appeal appeal;

    UserProfileRepo userProfile;

    public ChangedExecutorAppealEmailSend(DataSource dataSource, MailSender mailSender) {
        this.dataSource = dataSource;
        this.mailSender = mailSender;
    }


    //Проверяем изменилось ли поле в Appeal-Executor
    public void checkExecutor(Long AppealId, Long executorId, String BriefDescription, Date DataCreation , Date DataAnswer){

        try (Connection c = dataSource.getConnection()) {

            String sql = "select executor_id from appeal where id = ?";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setObject(1, AppealId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();



            while (resultSet.next()) {

                Long executor = resultSet.getLong(1);


                if (executor == executorId){

                    break;
                }else {

                    userEmailIdSendExecutor(executorId,AppealId,BriefDescription,DataCreation,DataAnswer);

                    break;
                }






            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    // Находим email по id
    public void userEmailIdSendExecutor(Long idExecutor,Long appealId, String BriefDescription,Date DataCreation , Date DataAnswer ) {
        try (Connection c = dataSource.getConnection()) {

            String sql = "select email from user where id = ?";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setObject(1, idExecutor);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();


            while (resultSet.next()) {

                String emailExecutor = resultSet.getString("email");


                sendEmailChangedExecutor(emailExecutor,appealId,BriefDescription,DataCreation,DataAnswer);

                break;

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // Отправляем письмо
    public  void sendEmailChangedExecutor(String emailExecutor,Long appealId, String BriefDescription,Date DataCreation , Date DataAnswer) {

        String message = String.format(
                "Новое входящие обращение: № . " + appealId + "\n" +


                        "Название компании:  ." + BriefDescription + "\n" +

                        "Дата создания: " + DataCreation + "\n" +

                        "Дата закрытия: " + DataAnswer + "\n" +

                        UrlEmail + appealId

        );
        mailSender.send(emailExecutor, "Вы назначены исполнителем ", message);
    }
}
