package com.ls.token_refresh_demo;

import com.ls.token_refresh_demo.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.security.SignatureException;
import java.util.Date;

import static com.ls.token_refresh_demo.util.JwtUtil.generalKey;

@SpringBootTest
class TokenRefreshDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test() throws Exception {
        String token = JwtUtil.createJWT("1735209949551763457");
        System.out.println("Token: " + token);
        Date tokenExpirationDate = getTokenExpirationDate(token);
        System.out.println(tokenExpirationDate);
        System.out.println(tokenExpirationDate.toString());
        long exp = tokenExpirationDate.getTime();
        long cur = System.currentTimeMillis();
        System.out.println(exp);
        System.out.println(cur);
        System.out.println(exp - cur);
    }
    // 解析令牌并获取过期时间
    public static Date getTokenExpirationDate(String token) {
        try {
            SecretKey secretKey = generalKey();
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getExpiration();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Invalid token", e);
        }
    }
}
