package com.mool.app.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

/***
 * JWT 토큰 방식을 사용할 때 필요한 기능들을 정리해놓은 클래스
 * 새로운 JWT토큰 발급, JWT토큰의 Claim에서 "loginUd" 꺼내기, 만료시간 체크 기능 수행
 */
public class JwtTokenUtil {

    //JWT토큰 발급
    public static String createToken(String loginId, String key1, long expireTimeMs) {
        // Claim = JWT Token에 들어갈 정보
        // Claim에 loginId를 넣어줌으로써 나중에 loginId를 꺼낼 수 있음
        Claims claims = Jwts.claims();
        claims.put("loginId", loginId);

        byte[] keyBytes = Decoders.BASE64.decode(key1);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * SecretKey를 이용해 Token Parsing
     */
    public static Claims extractClaims(String token, String secretKey) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }

    /**
     * Claims에서 loginId 꺼내기
     */
    public static String getLoginId(String token, String secretKey) {
        return extractClaims(token, secretKey).get("loginId").toString();
    }

    /**
     * 발급된 토큰이 만료 시간이 지났는지 체크
     */
    public static boolean isExpired(String token, String secretKey) {
        Date expiredDate = extractClaims(token, secretKey).getExpiration();

        //Token의 만료 날짜가 지금보다 이전인지 check
        return expiredDate.before(new Date());
    }
}
