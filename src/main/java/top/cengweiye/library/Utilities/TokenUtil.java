package top.cengweiye.library.Utilities;
import io.jsonwebtoken.*;
import java.util.Date;

public class TokenUtil {
    // 设置Token的过期时间（例如：1小时）
    private static final long EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000L;
    private static final String SECRET_KEY ="MTIzNDU2"; // 用于加密的密钥

    // 生成Token的方法
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // 设置JWT的主体，通常是用户名
                .setIssuedAt(new Date()) // 设置Token的签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 设置Token的过期时间
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY) // 设置签名算法和密钥
                .compact();
    }

    // 验证Token的方法
    public static boolean verify(String token) {
        if (TokenBlacklist.isTokenBlacklisted(token)) {
            System.out.println("Token已注销");
            return false;
        }
        try {
            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token); // 验证Token的签名
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token已过期");
        } catch (JwtException e) {
            System.out.println("无效的Token：" + e.getMessage());
            System.out.println(token);
        }
        return false;
    }

    // 获取Token中的用户名（作为用户的标识）
    public static String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject(); // 获取JWT中的用户名
    }
}
