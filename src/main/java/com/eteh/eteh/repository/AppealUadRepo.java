package com.eteh.eteh.repository;

import com.eteh.eteh.models.Appeal;
import com.eteh.eteh.models.AppealFile;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppealUadRepo {
    @PersistenceContext
    private EntityManager entityManager;
    private final DataSource dataSource;
    private final EntityManagerFactory entityManagerFactory;


    public AppealUadRepo(DataSource dataSource, EntityManagerFactory entityManagerFactory) {
        this.dataSource = dataSource;
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<AppealFile> findByIdFile(Long id) throws SQLException {

        try (Connection c = dataSource.getConnection()) {

            String sql = "select file_name,kei_id from appeal_file where id_file = ?";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();

            List<AppealFile> appealFiles = new ArrayList<>();
            while (resultSet.next()) {



                String fileName = resultSet.getString("file_name");
                String keyID = resultSet.getNString("kei_id");



                AppealFile appealFile = new AppealFile();





               appealFile.setFileName(fileName);
               appealFile.setKeiId(keyID);





                appealFiles.add(appealFile);

            }


            return appealFiles;
        }


    }



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


    public void deleteFile(String keiId) throws SQLException {

            try (Connection c = dataSource.getConnection()) {

                PreparedStatement statement = c.prepareStatement("DELETE FROM appeal_file WHERE kei_id = ?");


                statement.setString(1,keiId);

                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("updated successfully!");
                }


            } catch (Exception ex) {
                throw new RuntimeException(ex);

            }

        }





    public List<Appeal> getEntityRevisionsById(Long id) {
        List<Appeal> results = new ArrayList<>();
        // read audit history
        AuditReader reader = AuditReaderFactory.get(entityManager);
        AuditQuery query = reader.createQuery()
                .forRevisionsOfEntity(Appeal.class, false, true)
                .add(AuditEntity.id().eq(id))
                .addOrder(AuditEntity.revisionNumber().asc());


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





//
//    public AppealFile findByIdFile(Long id) {
//        TypedQuery<AppealFile> query = entityManager.createQuery(
//                "select fileName from AppealFile  where idFile  = :id", AppealFile.class);
//        query.setParameter("id", id);
//        return entityManager.find(AppealFile.class,id);
//    }



    @Transactional
    public Appeal findAuditByRevision(Long id, int revision) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        return auditReader.find(Appeal.class, id, revision);
    }








}



