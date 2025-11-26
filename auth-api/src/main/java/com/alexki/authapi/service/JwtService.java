package com.alexki.authapi.service;

import com.alexki.authapi.property.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();

        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        claims.put("roles", roles);

        Date nowDate = new Date();
        Date expiration = new Date(nowDate.getTime() + jwtProperties.getLifetime().toMillis());

        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(nowDate)
                .expiration(expiration)
                .signWith(signedKey())
                .compact();
    }

    public String getUserEmail(String token) {
        return getAllClaimsByToken(token).getSubject();
    }

    public List<String> getRoles(String token) {
        return getAllClaimsByToken(token).get("roles", List.class);
    }

    private Claims getAllClaimsByToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) signedKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    private Key signedKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    }

}
