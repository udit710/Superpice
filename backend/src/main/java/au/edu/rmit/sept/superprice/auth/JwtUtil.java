package au.edu.rmit.sept.superprice.auth;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import au.edu.rmit.sept.superprice.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {
    
    private final String secret_key;
    private long accessTokenValidity = 3600000; // 1 hour

    private final JwtParser jwtParser;

    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    public JwtUtil(Environment env) {
        this.secret_key = env.getProperty("secret_key");
        jwtParser = Jwts.parser().setSigningKey(secret_key);
    }

    public String createJwt(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        Date tokenCreateTime = new Date();
        Date tokenValidate = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        return Jwts.builder().setClaims(claims).setExpiration(tokenValidate).signWith(SignatureAlgorithm.HS256, secret_key).compact();
    }

}
