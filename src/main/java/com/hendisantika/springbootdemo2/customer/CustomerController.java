package com.hendisantika.springbootdemo2.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

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

    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchCustomerByEmail(
            @RequestParam(name = "email") String emailAddress) {
        try {
            Optional<Customer> customer = customerService.findByEmail(emailAddress);
            return ResponseEntity.ok(customer);
        }catch(Exception ex) {
            return handleException(ex);
        }
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCustomers(
            @RequestParam("pageNum") String pageNumber,
            @RequestParam("pageSize") String pageSize) {
        try {
            Integer pageNumberLong = Integer.valueOf(pageNumber);
            Integer pageSizeLong = Integer.valueOf(pageSize);
            //Create a new paginated search request.
            PageRequest pageRequest = PageRequest.of(pageNumberLong, pageSizeLong);
            Page page = customerService.findAll(pageRequest);
            return ResponseEntity.ok(page.getContent());
        }catch(Exception ex) {
            return handleException(ex);
        }
    }

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        try {
            Customer createdCustomer = customerService.create(customer);
            return ResponseEntity.created(new URI("/customer/" + createdCustomer.getId())).body(customer);
        }catch(Exception ex) {
            return handleException(ex);
        }
    }
}
