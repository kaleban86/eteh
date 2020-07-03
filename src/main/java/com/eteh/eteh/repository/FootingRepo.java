package com.eteh.eteh.repository;

import com.eteh.eteh.models.AppealStatus;
import com.eteh.eteh.models.Footing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FootingRepo extends JpaRepository<Footing,Long> {
}
