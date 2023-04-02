package com.hendisantika.springbootdemo2.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.envers.AuditReader;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

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

}
