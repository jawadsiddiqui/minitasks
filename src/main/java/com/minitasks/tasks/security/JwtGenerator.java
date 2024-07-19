package com.minitasks.tasks.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;

import java.util.Date;

public class JwtGenerator {

    public static final javax.crypto.SecretKey KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long JWT_EXPIRATION = 70000;
    public static final String SECRET_KEY = "SECRETKEY123";

    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date(System.currentTimeMillis());
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(new Date(currentDate.getTime() + JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
        System.out.println("New Token:"+ token);
        return token;
    }

    public String getUsernameFromToken(String token){
        Claims claim = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claim.getSubject();
    }

    public Boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Authentication token expired or invalid!");
        }
    }
}
