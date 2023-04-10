package com.hendisantika.springbootdemo2.auth.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-demo2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/11/23
 * Time: 05:24
 * To change this template use File | Settings | File Templates.
 */
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    @NotNull
    private String userName;

    @NotNull
    private String password;
}
