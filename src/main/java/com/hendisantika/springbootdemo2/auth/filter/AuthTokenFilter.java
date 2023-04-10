package com.hendisantika.springbootdemo2.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-demo2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/11/23
 * Time: 05:21
 * To change this template use File | Settings | File Templates.
 */
@Log4j2
public class AuthTokenFilter extends OncePerRequestFilter {

    public static final String _BEARER = "Bearer ";

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException {

        try {
            String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);


            if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(_BEARER)) {
                String jwtToken = headerAuth.substring(7);

                String username = jwtUtil.parseJWT(jwtToken).getSubject();

                UserDetails userDetails = userService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception ex) {
            log.error("Error authenticating user request : {}", ex.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
