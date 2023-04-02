package com.hendisantika.springbootdemo2.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-demo2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/2/23
 * Time: 08:13
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //Contains search on either firstname or lastname
    List<Customer> findAllByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

    Optional<Customer> findCustomerByEmailAddress(String email);
}
