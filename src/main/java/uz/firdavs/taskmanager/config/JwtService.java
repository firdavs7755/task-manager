package uz.firdavs.taskmanager.config;


import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import uz.firdavs.taskmanager.payload.ReqAuth;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class JwtService {

    private static String secretKey="fir4gb56w4rbn6bhr46b41t6g4w3e5423t4ghtdavsokam354ax57545844w2d4qsutaliyev";
    private static Integer jwtExpDate=864000;

    public static void main(String[] args) {
        System.out.println(generateToken(new ReqAuth("111","444")));
    }

    public static String generateToken(ReqAuth auth){
        Date expDate = new Date(new Date().getTime()+jwtExpDate);
        return Jwts.builder()
                .setSubject(auth.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
    public  String generateTokenn(ReqAuth auth){
        Date expDate = new Date(new Date().getTime()+jwtExpDate);
        return Jwts.builder()
                .setSubject(auth.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    public String getJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken)){
            return bearerToken;
        }
        return null;
    }
    public  String getUserNameFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token.substring(7)).getBody();
        return claims.getSubject();
    }
    public String updateTokenExpiration(ReqAuth req) {
        return Jwts.builder()
                .setSubject(req.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token.substring(7));
            return true;
        } catch (SignatureException s){
            System.err.println("Invalid JWT signature:"+s.getMessage());
        }catch (MalformedJwtException m){
            System.err.println("Invalid JWT token:"+m.getMessage());
        }catch (ExpiredJwtException e){
            System.err.println("Expired JWT token:"+e.getMessage());
        }catch (UnsupportedJwtException e){
            System.err.println("Unsupported JWT token:"+e.getMessage());
        }catch (IllegalArgumentException e){
            System.err.println("JWT claims string is empty token:"+e.getMessage());
        }
        return false;
    }
}
