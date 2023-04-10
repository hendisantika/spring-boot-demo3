package com.hendisantika.springbootdemo2.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

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

    private static final String CLAIM_FIRST_NAME_KEY = "FirstName";
    private static final String CLAIM_LAST_NAME_KEY = "LastName";

    @PostConstruct
    public void setUpSecretKey() {
        secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String createJWT(User user) {
        String jwt =
                Jwts.builder()
                        .setId(UUID.randomUUID().toString())
                        .setSubject(user.getUsername())
                        .setIssuer(issuer)
                        .setIssuedAt(Date.from(Instant.now()))
                        .setExpiration(Date.from(Instant.now().plus(Duration.ofSeconds(timeToLiveInSeconds))))
                        .claim(CLAIM_FIRST_NAME_KEY, user.getFirstName())
                        .claim(CLAIM_LAST_NAME_KEY, user.getLastName())
                        .signWith(secretKey)
                        .compact();
        return jwt;
    }
}
