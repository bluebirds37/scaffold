package top.bluebirds37.scaffold.config.authority;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;

public class JWTUtil {

    private static Key key = MacProvider.generateKey();

    /**
     * 校验token是否正确
     *
     * @param token 密钥
     * @return
     */
    public static boolean verify(String token) {
        //签名秘钥，和生成的签名的秘钥一模一样
        Claims claims;
        try {
            claims = Jwts.parser()
                    //得到DefaultJwtParser
                    .setSigningKey(key)
                    //设置签名的秘钥
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }//设置需要解析的jwt
        return claims != null;
    }


    /**
     * 获取登录id
     *
     * @param token
     * @return
     */
    public static Integer getUserId(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
        return Integer.parseInt(claimsJws.getBody().getSubject());
    }

    /**
     * 生成签名
     *
     * @param
     * @return
     */
    public static String sign(Integer userId) {
        // 指定过期时间
        return Jwts.builder()
                .setSubject(userId.toString())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

}

