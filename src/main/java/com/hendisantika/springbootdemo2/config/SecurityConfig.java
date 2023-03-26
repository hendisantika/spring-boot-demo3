package com.hendisantika.springbootdemo2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-demo2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/26/23
 * Time: 09:49
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class SecurityConfig {

    @Autowired
    private UserService userService;
}
