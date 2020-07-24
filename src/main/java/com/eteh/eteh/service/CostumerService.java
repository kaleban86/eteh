package com.eteh.eteh.service;


import com.eteh.eteh.models.Customer;
import com.eteh.eteh.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CostumerService {

    private final CustomerRepository customerRepository;

    public CostumerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }







}
