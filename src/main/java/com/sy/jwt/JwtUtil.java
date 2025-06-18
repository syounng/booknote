package com.sy.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    /* JWT 서명에 사용할 비밀키 (32자 이상) */
    private final String SECRET_KEY = "mysecretkeymysecretkeymysecretkeymysecretkey";
    /* 토큰 만료 시간 (24시간) */
    private final long EXPIRATION = 1000 * 60 * 60 * 24;

    /* 비밀키 객체 생성 */
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    /* JWT 토큰 생성 (로그인 성공 시) */
    public String generateToken(String email, String name) {
        return Jwts.builder()
                .setSubject(email) //토큰 주제(이메일)
                .claim("name", name)
                .setIssuedAt(new Date()) //토큰 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)) //토큰 만료 시간
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) //토큰 서명 알고리즘
                .compact();
    }

    /* 토큰에서 이메일 추출 */
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token) //토큰 파싱 및 검증
                .getBody()
                .getSubject(); //토큰 주제(이메일) 반환
    }

    /* 토큰에서 이름 추출 */
    public String getNameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("name", String.class);
    }

    /* 토큰 유효성 검증 */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}