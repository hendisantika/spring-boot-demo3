package com.hendisantika.springbootdemo2.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.envers.AuditReader;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-demo2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/2/23
 * Time: 08:14
 * To change this template use File | Settings | File Templates.
 */
@Service
@Log4j2
@CacheConfig(cacheNames = "customers")
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final AuditReader auditReader;

    @Cacheable
    public Optional<Customer> getCustomerById(Long customerId) {
        log.info("Fetching customer by id: {}", customerId);
        return customerRepository.findById(customerId);
    }

    @CachePut(key = "#customer.id")
    public Customer create(Customer customer) {
        try {
            log.info("Creating a new customer with emailAddress: {}", customer.getEmailAddress());
            return customerRepository.save(customer);
        }catch (DataIntegrityViolationException e){
            log.error("Customer already exists with emailAddress: {}", customer.getEmailAddress());
            throw new RuntimeException("Customer already exists with same emailAddress");
        }

    }
}
