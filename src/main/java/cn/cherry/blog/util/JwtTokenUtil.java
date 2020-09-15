package cn.cherry.blog.util;

import cn.cherry.blog.domain.AuthUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: JWT生成令牌、验证令牌、获取令牌
 * @author: Cherry
 * @create: 2020-09-15 10:49
 **/
@Slf4j
public class JwtTokenUtil {
    @Value("${jwt.secret.key}")
    private String SECRET_KEY;
    @Value("${jwt.expiration.time}")
    private String EXPIRATION_TIME;

    /**
    * @Description: 生成令牌
    * @param userDetails
    * @return: 令牌
    */
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>(2);
        claims.put(Claims.SUBJECT,userDetails.getUsername());
        claims.put(Claims.ISSUED_AT,new Date());
        return generateToken(claims);
    }

    /**
    * @description 生成令牌
    * @param userName 用户名
    * @return 令牌
    */
    public String generateTokn(String userName){
        Map<String,Object> claims = new HashMap<>(2);
        claims.put(Claims.SUBJECT,userName);
        claims.put(Claims.ISSUED_AT,new Date());
        return generateToken(claims);
    }

    public String getUserNameFromToken(String token) throws Exception {
        String userName = null;
        Claims claims = null;
        try {
            claims = getClaimsFromToken(token);
            log.info("claims:{}",claims);
            userName = claims.getSubject();
        } catch (Exception e) {
            log.error("getUserNameFromToken Exception",e);
            throw e;
        }
        return userName;
    }

    /**
    * @description 判断令牌是否过期
    * @param token 令牌
    * @return 是否过期
    */
    public Boolean isTokenExpired(String token) throws Exception {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    /**
    * @description 刷新令牌
    * @param token 原令牌
    * @return
    */
    public String refreshToken(String token){
        String refreshToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(Claims.ISSUED_AT,new Date());
            refreshToken = generateToken(claims);
        } catch (Exception e) {
            refreshToken = null;
        }
        return refreshToken;
    }

    /**
    * @description 验证令牌
    * @param userDetails    用户
    * @return 
    */
    public Boolean validateToken(String token,UserDetails userDetails) throws Exception {
        AuthUser user = (AuthUser) userDetails;
        String userName = getUserNameFromToken(token);
        return (StringUtils.equals(userName,user.getUsername())&&!isTokenExpired(token));
    }




    /**
    * @description 生成令牌公共部分
    * @param claims  数据声明
    * @return 数据声明
    */
    private String generateToken(Map<String,Object> claims){
        long l = Long.parseLong(EXPIRATION_TIME);
        Date expirationDate = new Date(System.currentTimeMillis() + l);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512,SECRET_KEY).compact();
    }

    /**
    * @description 从令牌中获取数据声明
    * @param token 令牌
    * @return 数据声明
    */
    private Claims getClaimsFromToken(String token) throws Exception {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(e.getHeader(),e.getClaims(),"TOKEN已过期,请重新登陆");
        } catch (Exception e) {
            throw new Exception("TOKEN令牌无效");
        }
        return claims;
    }

    /*=======================TOKEN不设置有效期========================*/

    /**
    * @description 生成令牌
    * @param userDetails 用户
    * @return 令牌
    */
    public String generateJwtToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT,userDetails.getUsername());
        claims.put(Claims.ISSUED_AT,new Date());
        return generateJwtToken(claims);

    }

    /**
    * @description 生成令牌
    * @param userName   用户名
    * @return 令牌
    */
    public String generateJwtToken(String userName){
        Map<String,Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT,userName);
        claims.put(Claims.ISSUED_AT,new Date());
        return generateJwtToken(claims);
    }

    /**
    * @description 从数据声明生成令牌
    * @param claims 数据声明
    * @return 令牌
    */
    private String generateJwtToken(Map<String,Object> claims){
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512,SECRET_KEY).compact();
    }

    /**
    * @description 从令牌中获取用户名
    * @param token 令牌
    * @return 用户名
    */
    public  String getUserNameFromJwtToken(String token) throws Exception {
        String userName = null;
        try {
            Claims claims = getClaimsFromJwtToken(token);
            userName = claims.getSubject();

        } catch (Exception e) {
            log.error("getUserNameFromToken Exception",e);
            throw e;
        }
        return userName;
    }

    /**
    * @description 从令牌中获取数据声明
    * @param token 令牌
    * @return 数据声明
    */
    private Claims getClaimsFromJwtToken(String token) throws Exception {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new Exception("TOKEN令牌无效");
        }
        return claims;
    }

}
