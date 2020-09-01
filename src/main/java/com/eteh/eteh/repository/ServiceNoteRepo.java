package com.eteh.eteh.repository;

import com.eteh.eteh.models.ServiceNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceNoteRepo extends JpaRepository<ServiceNote,Long> {
}
