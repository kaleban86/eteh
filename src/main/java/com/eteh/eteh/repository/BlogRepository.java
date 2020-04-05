package com.eteh.eteh.repository;

import com.eteh.eteh.models.Blog;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<Blog,Long> {
}
