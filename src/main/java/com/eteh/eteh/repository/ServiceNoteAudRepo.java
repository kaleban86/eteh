package com.eteh.eteh.repository;

import com.eteh.eteh.models.ServiceNote;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceNoteAudRepo {
    @PersistenceContext
    private EntityManager entityManager;


    public List<ServiceNote> getEntityRevisionsByIdNote(Long id) {
        List<ServiceNote> results = new ArrayList<>();
        // read audit history
        AuditReader reader = AuditReaderFactory.get(entityManager);
        AuditQuery query = reader.createQuery()
                .forRevisionsOfEntity(ServiceNote.class, false, true)
                .add(AuditEntity.id().eq(id))
                .addOrder(AuditEntity.revisionNumber().asc());


        // get results
        for(Object row : query.getResultList()) {
            if(row instanceof Object[]) {
                Object[] array = (Object[])row;
                ServiceNote serviceNote = (ServiceNote) array[0];
                results.add(serviceNote);


            }
        }
        return results;
    }

}
