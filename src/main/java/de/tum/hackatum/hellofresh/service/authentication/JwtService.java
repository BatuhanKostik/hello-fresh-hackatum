package de.tum.hackatum.hellofresh.service.authentication;

import de.tum.hackatum.hellofresh.port.out.TokenPort;
import de.tum.hackatum.hellofresh.port.out.UserPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JwtService {

    private final long jwtExpiration;
    private final long refreshExpiration;
    private final Key signKey;
    private final TokenPort tokenPort;
    private final UserPort userPort;

    public JwtService(
            @Value("${application.security.jwt.secret-key}") String secretKey,
            @Value("${application.security.jwt.expiration}") long jwtExpiration,
            @Value("${application.security.jwt.refresh-token.expiration}") long refreshExpiration,
            TokenPort tokenPort,
            UserPort userPort
    ) {
        this.jwtExpiration = jwtExpiration;
        this.refreshExpiration = refreshExpiration;
        this.tokenPort = tokenPort;
        this.userPort = userPort;
        this.signKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public Optional<UserDetails> extractUserDetails(String token) {
        return userPort.loadUserByUsername(extractUsername(token));
    }


    public void revokeJwtToken(String jwt, UserDetails userDetails) {
        this.tokenPort.revokeToken(userDetails, jwt);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    public boolean isTokenExcluded(String token, UserDetails userDetails) {
        return tokenPort.isTokenExcluded(userDetails, token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(signKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        long currentTime = System.currentTimeMillis();
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTime + expiration))
                .signWith(signKey, SignatureAlgorithm.HS256)
                .compact();
    }

    private String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

}