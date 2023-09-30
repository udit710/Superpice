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
    private long accessTokenValidity = 60*60*1000;

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

    private Claims parseJwt(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public String resolveToken(HttpServletRequest request) {

        String bearer = request.getHeader(TOKEN_HEADER);
        if (bearer != null && bearer.startsWith(TOKEN_PREFIX)) {
            return bearer.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public Claims resolve(HttpServletRequest request) {
        try {
            String token = resolveToken(request);
            if (token != null) return parseJwt(token);
            return null;
        }
        catch (ExpiredJwtException err) {
            request.setAttribute("expired", err.getMessage());
            throw err;
        }
        catch (Exception err) {
            request.setAttribute("invalid", err.getMessage());
            throw err;
        }
    }

    public boolean tokenValidate(Claims claims) {
        try {
            return claims.getExpiration().after(new Date());
        }
        catch (Exception err) {
            throw err;
        }
    }

    public String getEmail(Claims claims) {
        return claims.getSubject();
    }
}
