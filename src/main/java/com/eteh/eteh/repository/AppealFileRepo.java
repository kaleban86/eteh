package com.eteh.eteh.repository;

import com.eteh.eteh.models.Appeal;
import com.eteh.eteh.models.AppealFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppealFileRepo extends JpaRepository<AppealFile,Long>  {
}
