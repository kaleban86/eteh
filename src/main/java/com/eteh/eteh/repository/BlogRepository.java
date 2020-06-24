package com.eteh.eteh.repository;

import com.eteh.eteh.models.Blog;
import com.eteh.eteh.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog,Long> {


}
