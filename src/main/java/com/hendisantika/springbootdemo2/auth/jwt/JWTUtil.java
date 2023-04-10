package com.hendisantika.springbootdemo2.auth.jwt;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-demo2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/11/23
 * Time: 05:23
 * To change this template use File | Settings | File Templates.
 */
@Component
@Log4j2
public class JWTUtil {

    @Value("${auth.jwt.issuer}")
    private String issuer;

    @Value("${auth.jwt.secret}")
    private String secret;

    @Value("${auth.jwt.audience}")
    private String audience;

    @Value("${auth.jwt.ttl-in-seconds}")
    private long timeToLiveInSeconds;

    private SecretKey secretKey;
}
