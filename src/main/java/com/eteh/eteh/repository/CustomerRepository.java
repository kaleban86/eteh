package com.eteh.eteh.repository;

import com.eteh.eteh.models.Customer;
import org.springframework.data.repository.CrudRepository;
import sun.text.CollatorUtilities;

public interface CustomerRepository extends CrudRepository<Customer,Long> {



}
