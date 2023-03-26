package com.hendisantika.springbootdemo2.config;

import com.hendisantika.springbootdemo2.audit.AuditAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-demo2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/26/23
 * Time: 09:48
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "customAuditProvider")
public class JpaConfig {

    @Bean
    public AuditorAware<String> customAuditProvider() {
        return new AuditAwareImpl();
    }
}
