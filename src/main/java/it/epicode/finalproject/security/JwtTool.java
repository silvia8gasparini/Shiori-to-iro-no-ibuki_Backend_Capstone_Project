package it.epicode.finalproject.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.User;
import it.epicode.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTool {

    @Value("${jwt.duration}")
    private long duration;

    @Value("${jwt.secret}")
    private String secretKey;

    @Autowired
    private UserService userService;

    public String createToken(User user) {
        return Jwts.builder()
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + duration))
                .subject(String.valueOf(user.getId()))
                .claim("role", "ROLE_" + user.getRole().name())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }
    public void validateToken(String token) {
        Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parse(token);
    }

    public User getUserFromToken(String token) throws NotFoundException {
        String subject = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

        int userId = Integer.parseInt(subject);
        return userService.getUser(userId);
    }
}
