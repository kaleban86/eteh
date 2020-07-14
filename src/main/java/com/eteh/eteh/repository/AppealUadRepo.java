package com.eteh.eteh.repository;

import com.eteh.eteh.models.Appeal;
import com.eteh.eteh.models.AppealAud;
import org.hibernate.Session;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppealUadRepo {
    @PersistenceContext
    EntityManager entityManager;
    private final DataSource dataSource;

    public AppealUadRepo(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<AppealAud> faindAllBiUD(Long id) throws SQLException {

        try (Connection c = dataSource.getConnection()) {

            String sql = "select * from appeal_aud where id = ? ";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();

            List<AppealAud> appealAuds = new ArrayList<>();
            while (resultSet.next()) {


                String address = resultSet.getString("address");
                Long authorUpdate = resultSet.getLong("author_update");

                String briefDescription = resultSet.getString("brief_Description");

                Long controller = resultSet.getLong("controller");
                Date dataChange = resultSet.getDate("data_Change");
                Date dataCreation = resultSet.getDate("data_creation");
                Date dataAnswer = resultSet.getDate("data_answer");
                String emailAddress = resultSet.getString("email_Address");
                String footing = resultSet.getString("footing");
                String text = resultSet.getString("text");
                Long executor = resultSet.getLong("executor");


               String status = resultSet.getString("appeal_status_id");

                String surname = resultSet.getString("surname");
                String lastName = resultSet.getString("last_Name");
                Long nameCompany = resultSet.getLong("name_Company");
                String tel = resultSet.getString("tel");


                int userId = resultSet.getInt("user_id");


                AppealAud appealAud = new AppealAud();

                appealAud.setAddress(address);
                appealAud.setBriefDescription(briefDescription);
                appealAud.setFooting(footing);
                appealAud.setTel(tel);
                appealAud.setText(text);

                appealAud.setExecutor(executor);
                appealAud.setController(controller);
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


                appealAuds.add(appealAud);



            }


            return appealAuds;
        }


    }



    public void test(){
        Session session = entityManager.unwrap(Session.class);


//        AuditQuery query = auditReader.createQuery()
//                .forEntitiesAtRevision(Appeal.class, 201);

//
//        Query query = session.createQuery("from Appeal ");
//        List<Appeal> list = query.list();
//        System.out.println(list.size());



        AuditReader reader = AuditReaderFactory.get(entityManager);

        Appeal event = reader.findRevision(Appeal.class,201);

        System.out.println(event);


    }







    public List<Appeal> findAll() {
        return null;
    }

    public Appeal findById(Long id) {
        TypedQuery<Appeal> query = entityManager.createQuery(
                "select authorUpdate from Appeal  where Appeal .id = :id", Appeal.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }



    @Transactional
    public Appeal findAuditByRevision(Long id, int revision) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        return auditReader.find(Appeal.class, id, revision);
    }








}


//    public List<AppealAud> userNameModIDEnvers(Long id) {
//        try (Connection c = dataSource.getConnection()) {
//
//            String sql = "select rev from appeal_aud where id = ?;";
//            PreparedStatement preparedStatement = c.prepareStatement(sql);
//            preparedStatement.setObject(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
//            int n = resultSetMetaData.getColumnCount();
//
//
//            List<UserProfileModels> revIdGet = new ArrayList<>();
//            while (resultSet.next()) {
//
//                int rev = resultSet.getInt("rev");
//
//                AppealAud appealAud = new AppealAud();
//
//
//                appealAud.setRev(rev);
//
//                userNameModIDEnversR(rev);
//
//            }
//
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return null;
//    }
//
//
//    public List<AppealAud> userNameModIDEnversR(int id) {
//        try (Connection c = dataSource.getConnection()) {
//
//            String sql = "select user_id from appeal_aud where rev = ?;";
//            PreparedStatement preparedStatement = c.prepareStatement(sql);
//            preparedStatement.setObject(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
//            int n = resultSetMetaData.getColumnCount();
//
//
//            List<UserProfileModels> revIdGet = new ArrayList<>();
//            while (resultSet.next()) {
//
//
//                int userId = resultSet.getInt("user_id");
//
//                AppealAud appealAud = new AppealAud();
//
//                appealAud.setUserId(userId);
//
//               // userNameId(userId);
//
//
//            }
//
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return null;
//    }
//
//

//
//
//    public void updateIdAppealAud(Long id, Long userId) {
//        try (Connection c = dataSource.getConnection()) {
//
//            PreparedStatement statement = c.prepareStatement("UPDATE appeal_aud SET author_update= ? where rev = " +
//                    "(SELECT max FROM (SELECT MAX(rev) as max FROM appeal_aud WHERE id= ?) AS t2 )");
//
//            statement.setLong(1, userId);
//            statement.setLong(2, id);
//
//            int rowsUpdated = statement.executeUpdate();
//
//            if (rowsUpdated > 0) {
//                System.out.println("updated successfully!");
//            }
//
//
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//
//        }
//
//    }

