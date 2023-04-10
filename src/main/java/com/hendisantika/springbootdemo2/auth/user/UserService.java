package com.hendisantika.springbootdemo2.auth.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-demo2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/11/23
 * Time: 05:26
 * To change this template use File | Settings | File Templates.
 */
@Service
@CacheConfig(cacheNames = "users")
@Log4j2
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
}
