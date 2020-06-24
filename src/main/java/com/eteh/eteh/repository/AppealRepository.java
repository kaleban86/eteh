package com.eteh.eteh.repository;

import com.eteh.eteh.models.Appeal;
import com.eteh.eteh.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AppealRepository extends JpaRepository<Appeal,Long> {




}
