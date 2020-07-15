package com.eteh.eteh.repository;

import com.eteh.eteh.models.Appeal;
import com.eteh.eteh.models.AppealAud;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
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
    private EntityManager entityManager;
    private final DataSource dataSource;

    public AppealUadRepo(DataSource dataSource) {
        this.dataSource = dataSource;
    }

//    public List<AppealAud> faindAllBiUD(Long id) throws SQLException {
//
//        try (Connection c = dataSource.getConnection()) {
//
//            String sql = "select * from appeal_aud where id = ? ";
//            PreparedStatement preparedStatement = c.prepareStatement(sql);
//            preparedStatement.setObject(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
//            int n = resultSetMetaData.getColumnCount();
//
//            List<AppealAud> appealAuds = new ArrayList<>();
//            while (resultSet.next()) {
//
//
//                String address = resultSet.getString("address");
//                Long authorUpdate = resultSet.getLong("author_update");
//
//                String briefDescription = resultSet.getString("brief_Description");
//
//                Long controller = resultSet.getLong("controller");
//                Date dataChange = resultSet.getDate("data_Change");
//                Date dataCreation = resultSet.getDate("data_creation");
//                Date dataAnswer = resultSet.getDate("data_answer");
//                String emailAddress = resultSet.getString("email_Address");
//                String footing = resultSet.getString("footing");
//                String text = resultSet.getString("text");
//                Long executor = resultSet.getLong("executor");
//
//
//               String status = resultSet.getString("appeal_status_id");
//
//                String surname = resultSet.getString("surname");
//                String lastName = resultSet.getString("last_Name");
//                Long nameCompany = resultSet.getLong("name_Company");
//                String tel = resultSet.getString("tel");
//
//
//                int userId = resultSet.getInt("user_id");
//
//
//                AppealAud appealAud = new AppealAud();
//
//                appealAud.setAddress(address);
//                appealAud.setBriefDescription(briefDescription);
//                appealAud.setFooting(footing);
//                appealAud.setTel(tel);
//                appealAud.setText(text);
//
//                appealAud.setExecutor(executor);
//                appealAud.setController(controller);
//                appealAud.setStatus(status);
//                appealAud.setSurname(surname);
//                appealAud.setLastName(lastName);
//                appealAud.setNameCompany(nameCompany);
//                appealAud.setEmailAddress(emailAddress);
//                appealAud.setDataChange(dataChange);
//                appealAud.setDataCreation(dataCreation);
//                appealAud.setDataAnswer(dataAnswer);
//                appealAud.setUserId(userId);
//                appealAud.setAuthorUpdate(authorUpdate);
//
//
//                appealAuds.add(appealAud);
//
//
//
//            }
//
//
//            return appealAuds;
//        }
//
//
//    }



//    public void test(){
//        Session session = entityManager.unwrap(Session.class);
//
//
////        AuditQuery query = auditReader.createQuery()
////                .forEntitiesAtRevision(Appeal.class, 201);
//
////
////        Query query = session.createQuery("from Appeal ");
////        List<Appeal> list = query.list();
////        System.out.println(list.size());
//
//
//        AuditReader reader = AuditReaderFactory.get(entityManager);
//
//        Appeal event = reader.findRevision(Appeal.class,201);
//
//        System.out.println(event);
//        AuditQuery query = reader
//                .createQuery().forEntitiesAtRevision(Appeal.class,201);
//
//        System.out.println(query);
//
//    }






    public List<Appeal> getEntityRevisionsById(Long id) {
        List<Appeal> results = new ArrayList<>();
        // read audit history
        AuditReader reader = AuditReaderFactory.get(entityManager);
        AuditQuery query = reader.createQuery()
                .forRevisionsOfEntity(Appeal.class, false, true)
                .add(AuditEntity.id().eq(id))
                .addOrder(AuditEntity.revisionNumber().asc());

        AppealAud appealAud = new AppealAud();
        // get results
        for(Object row : query.getResultList()) {
            if(row instanceof Object[]) {
                Object[] array = (Object[])row;
                Appeal entity = (Appeal) array[0];
                results.add(entity);


            }
        }
        return results;
    }



    public List<Appeal> findAll()
    {
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



