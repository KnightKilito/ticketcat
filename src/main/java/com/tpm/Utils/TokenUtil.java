package com.tpm.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tpm.entity.User;
import com.tpm.exception.MyException;
import com.tpm.exception.enums.StateEnums;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

import java.util.Date;

public class TokenUtil {

    //10小时
    private static final long EXPIRE_TIME= 10*60*60*1000;
    //6分钟
//    private static final long EXPIRE_TIME= 10*60*60*10;
    private static final String TOKEN_SECRET="txdy";  //密钥盐

    /**
     * 签名生成
     * @param user
     * @return
     */
    public static String sign(User user){
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("username", user.getUsername())
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 签名验证
     * @param token
     * @return
     */
    public static boolean verify(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("username: " + jwt.getClaim("username").asString());
            System.out.println("过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (ExpiredJwtException e){
            throw new MyException(StateEnums.USER_TOKEN_OVERDUE,"登录过期，请重新登录");
        }catch (UnsupportedJwtException e){
            throw new MyException(StateEnums.USER_TOKEN_ILLEGAL,"Token非法！有你好果汁吃！");
        }catch (Exception e){
            return false;
        }
    }

}
