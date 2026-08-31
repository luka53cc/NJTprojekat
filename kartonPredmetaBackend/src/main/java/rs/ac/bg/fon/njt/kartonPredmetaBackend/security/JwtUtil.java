package rs.ac.bg.fon.njt.kartonPredmetaBackend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generisiToken(String korisnickoIme, String uloga) {
        Date sada = new Date();
        Date istice = new Date(sada.getTime() + expiration);

        return Jwts.builder()
                .subject(korisnickoIme)
                .claim("uloga", uloga)
                .issuedAt(sada)
                .expiration(istice)
                .signWith(getKey())
                .compact();
    }

    public String izvuciKorisnickoIme(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String izvuciUlogu(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("uloga", String.class);
    }

    public boolean jeValidan(String token) {
        try {
            Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}