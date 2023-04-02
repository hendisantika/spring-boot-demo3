package com.hendisantika.springbootdemo2.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.envers.AuditReader;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @CachePut(key = "#customer.id")
    public Customer update(Customer customer) {
        log.info("Updating a customer with id: {}", customer.getId());
        Optional<Customer> optionalCustomer = customerRepository.findById(customer.getId());
        if(optionalCustomer.isEmpty()) {
            log.error("Unable to update customer by id {}",  customer.getId());
            throw new RuntimeException("Customer does not exists");
        }
        Customer existingCustomer = optionalCustomer.get();
        existingCustomer.setAddresses(customer.getAddresses());
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        return customerRepository.save(existingCustomer);
    }

    public List<Customer> findByName(String name){
        return customerRepository.findAllByFirstNameContainingOrLastNameContaining(name, name);
    }

    public Optional<Customer> findByEmail(String email){
        return customerRepository.findCustomerByEmailAddress(email);
    }

    //Paging implementation of findAll
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

}
