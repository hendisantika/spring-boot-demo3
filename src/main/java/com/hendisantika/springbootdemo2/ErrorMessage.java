package com.hendisantika.springbootdemo2;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-demo2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/26/23
 * Time: 09:47
 * To change this template use File | Settings | File Templates.
 */
@Data
@AllArgsConstructor
public class ErrorMessage {
    private int statusCode;
    private String errorMessage;
}
