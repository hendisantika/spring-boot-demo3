package com.hendisantika.springbootdemo2.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-demo2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/2/23
 * Time: 08:17
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomerDetail(@PathVariable(name = "id") String customerId) {
        try {
            Long customerIdLong = Long.valueOf(customerId);
            Customer customer = customerService.getCustomerById(customerIdLong)
                    .orElseThrow(()->new RuntimeException("Customer does not exists"));
            return ResponseEntity.ok(customer);
        }catch(Exception ex) {
            return handleException(ex);
        }
    }
}
