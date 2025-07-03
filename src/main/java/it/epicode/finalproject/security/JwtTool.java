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

        // Crea il token usando solo l'ID dell'utente come subject
        public String createToken(User user) {
            return Jwts.builder()
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + duration))
                    .subject(String.valueOf(user.getId()))
                    .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .compact();
        }

        // Valida semplicemente il token, verifica la firma e la scadenza
        public void validateToken(String token) {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parse(token);
        }

        // Estrae l'ID dal subject e restituisce l'utente corrispondente
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
