package com.eteh.eteh.repository;

import com.eteh.eteh.models.Appeal;
import com.eteh.eteh.service.MailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;


/*


Отправка письма если изменили контролёра

 */
@Service
public class ChangedControllerAppealEmailSend {

    @Value("${UrlEmail")
    private String UrlEmail;

    private DataSource dataSource;
    private final MailSender mailSender;
    private Appeal appeal;

    UserProfileRepo userProfile;

    public ChangedControllerAppealEmailSend(DataSource dataSource, MailSender mailSender) {
        this.dataSource = dataSource;
        this.mailSender = mailSender;
    }


    //Проверяем изменилось ли поле в Appeal-Controller
    public void checkController(Long AppealId, Long controllerId, String BriefDescription, Date DataCreation, Date DataAnswer) {

        try (Connection c = dataSource.getConnection()) {

            String sql = "select controller from appeal where id = ?";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setObject(1, AppealId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();


            while (resultSet.next()) {

                Long controller = resultSet.getLong(1);


                if (controller == controllerId) {

                    break;
                } else {

                    userEmailIdSendController(controllerId, AppealId, BriefDescription, DataCreation, DataAnswer);
                    break;

                }


            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    // Находим email по id
    public void userEmailIdSendController(Long id, Long appealId, String BriefDescription, Date DataCreation, Date DataAnswer) {
        try (Connection c = dataSource.getConnection()) {

            String sql = "select email from user where id = ?";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();


            while (resultSet.next()) {

                String emailController = resultSet.getString("email");


                sendEmailChangedController(emailController, appealId, BriefDescription, DataCreation, DataAnswer);

                break;

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // Отправляем письмо
    public void sendEmailChangedController(String emailController, Long appealId, String BriefDescription, Date DataCreation, Date DataAnswer) {

        String message = String.format(
                "Новое входящие обращение: № . " + appealId + "\n" +


                        "Название компании:  ." + BriefDescription + "\n" +

                        "Дата создания: " + DataCreation + "\n" +

                        "Дата закрытия: " + DataAnswer + "\n" +

                        UrlEmail + appealId

        );
        mailSender.send(emailController, "Вы назначены контролёром ", message);

    }
}
