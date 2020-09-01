package com.eteh.eteh.service;


import com.eteh.eteh.repository.AppealRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
@EnableScheduling
@Service
public class CheckDateResponse {

    private final AppealRepository appealRepository;

    private final DataSource dataSource;

    private final MailSender mailSender;


    public CheckDateResponse(AppealRepository appealRepository, DataSource dataSource, MailSender mailSender) {
        this.appealRepository = appealRepository;
        this.dataSource = dataSource;
        this.mailSender = mailSender;
    }
    @Scheduled(  fixedDelayString = "28800000")

    public void doWork() throws InterruptedException {
        try {
            findByDate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void findByDate() throws SQLException {

        try (Connection c = dataSource.getConnection()) {

            String sql = "select appeal.id from appeal where data_answer <= ADDDATE(current_date(), -1) ";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();

            while (resultSet.next()) {


                Long appealId = resultSet.getLong("id");




                statusId(appealId);

            }


        }


    }


    public void statusId(Long appealId) {

        String statusNovoe = "1";
        String statusVrabote = "2";
        try (Connection c = dataSource.getConnection()) {

            String sql = "select status from appeal where appeal.id = ?";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setObject(1, appealId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();


            while (resultSet.next()) {

                String statusId = resultSet.getString("status");

                if (statusNovoe.equals(statusId) || statusVrabote.equals(statusId)){

                    controllerId(appealId);
                    userId(appealId,appealId);

                }




                break;

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void controllerId(Long appealId) {
        try (Connection c = dataSource.getConnection()) {

            String sql = "select controller_id  from appeal where appeal.id = ?";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setObject(1, appealId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();


            while (resultSet.next()) {

                Long controllerId = resultSet.getLong("controller_id");

                userEmail(controllerId,appealId);

                System.out.println(controllerId);


                break;

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void userId(Long id,Long appealId) {
        try (Connection c = dataSource.getConnection()) {

            String sql = "select user_id  from appeal where appeal.id = ?";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();


            while (resultSet.next()) {

                Long userId = resultSet.getLong("user_id");


                userEmail(userId,appealId);

                break;

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    public void userEmail(Long id,Long appealId) {
        try (Connection c = dataSource.getConnection()) {

            String sql = "select email  from user where user.id = ?";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();


            while (resultSet.next()) {


                String emailUser = resultSet.getString("email");



                String MessageSubject = String.format(
                        "Входящие обращение  № %s.  " ,
                        appealId
                );

                String MessageText = String.format(
                        "Входящие обращение  № %s истекла дата ответа. " ,
                        appealId
                );



                mailSender.send(emailUser, MessageSubject,MessageText);


                break;

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
