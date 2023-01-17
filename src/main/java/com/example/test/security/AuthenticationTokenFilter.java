package com.example.test.security;

import com.example.test.exception.BadRequestException;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private TokenUtils tokenUtils;
    private UserDetailsService userDetailsService;
    protected final Log LOGGER = LogFactory.getLog(getClass());

    public AuthenticationTokenFilter(TokenUtils tokenHelper, UserDetailsService userDetailsService) {
        this.tokenUtils = tokenHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String email;
        String authToken = tokenUtils.getToken(request);

        try {
            if (authToken != null) {
                email = tokenUtils.getEmailFromToken(authToken);
                if (email != null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                    if (tokenUtils.validateToken(authToken, userDetails)) {
                        TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                        authentication.setToken(authToken);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }

        } catch (BadCredentialsException ex) {
            throw new BadRequestException("Wrong username or password!!");
        } catch (ExpiredJwtException ex) {
            // add refresh token
            String isRefreshToken = request.getHeader("isRefreshToken");
            String requestURL = request.getRequestURL().toString();
            // allow for refresh token creation if following conditions are true
            if(isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("refreshtoken"))
                allowForRefreshToken(ex, request);
            else request.setAttribute("exception", ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        filterChain.doFilter(request, response);
    }

    private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {
        TokenBasedAuthentication authentication = new TokenBasedAuthentication(null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        request.setAttribute("claims", ex.getClaims());
    }
}
