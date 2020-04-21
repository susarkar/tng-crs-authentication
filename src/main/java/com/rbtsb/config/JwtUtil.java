package com.rbtsb.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
public class JwtUtil {

//    private String SECRET_KEY = "secret";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Value("${jwt.token.secretkey}")
    private String SECRET_KEY;

    @Value("${jwt.token.expiryinmillisec}")
    private int EXPIRY_IN_MILLI_SEC;

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
  /*
        System.out.println("SECRET_KEY--" + SECRET_KEY);
        System.out.println("EXPIRY_IN_MILLI_SEC--" + EXPIRY_IN_MILLI_SEC);
        System.out.println(1000 * 60 * 1 * 1);//expires in 15min
        System.out.println(1000 * 60 * 60 * 1);//expires in 15min
        System.out.println(new Date(System.currentTimeMillis() + 1000 * 60 * 1 * 1));//expires in 15min
        System.out.println(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10));//expires in 10hours
*/
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 1))//expires in 1 hour.
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRY_IN_MILLI_SEC))//expires in 1 hour.
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        boolean validate = false;
        try {
            final String username = extractUsername(token);
            validate = username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (Exception e) {
            log.error("Error in JWTUtil.validate-token--{} ", e.getMessage());
        }
        return validate;
    }
}