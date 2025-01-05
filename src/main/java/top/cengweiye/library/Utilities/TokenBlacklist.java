package top.cengweiye.library.Utilities;
import java.util.HashSet;
import java.util.Set;

public class TokenBlacklist {
    private static final Set<String> blacklistedTokens = new HashSet<>();
    // 将Token加入黑名单
    public static void blacklistToken(String token) {
        blacklistedTokens.add(token);
    }
    // 检查Token是否在黑名单中
    public static boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}
