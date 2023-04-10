package com.choimory.memberapi.jwt;


import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 토큰 생성, 검증 등의 토큰 관련 유틸 클래스
 */
@Component
@Slf4j
public class JwtUtil {
    private final String secretKey;
    private final int expiredTime;

    /**
     * 필수 생성자
     * secretKey와 expiredTime 멤버변수를 final 변수로 만들기 위해 멤버변수에 @Value 어노테이션 지정하는 방식 대신, 필수생성자 직접 작성하여 매개변수에 @Value 어노테이션 부여
     * @param secretKey JWT 비공개 키
     * @param expiredTime JWT 만료시간
     */
    public JwtUtil (@Value("${jwt.secret-key}") String secretKey,
                    @Value("${jwt.expire-time}") int expiredTime) {
        this.secretKey = secretKey;
        this.expiredTime = expiredTime;
    }

    public String generateToken (String identity) {
        Date now = new Date();
        Date expiredAt = new Date(now.getTime() + expiredTime);

        Claims claims = Jwts.claims()
                .setSubject(identity);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiredAt)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public String findIdentityFromToken (String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean isTokenValid (String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            return !claims.getBody()
                    .getExpiration()
                    .before(new Date());
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    public boolean isTokenExpired(String token){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTokenUserMatched (String token, UserDetails userDetails) {
        return token != null && userDetails != null
                ? findIdentityFromToken(token).equals(userDetails.getUsername())
                : false;
    }

    public static String toToken (HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken)
                && StringUtils.startsWithIgnoreCase(bearerToken, "Bearer ")
                && (!bearerToken.contains("undefined") || !bearerToken.contains("null"))){
            return StringUtils.replace(bearerToken,"Bearer ", "");
        }

        return null;
    }
}
