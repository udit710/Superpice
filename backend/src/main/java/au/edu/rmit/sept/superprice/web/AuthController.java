package au.edu.rmit.sept.superprice.web;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import au.edu.rmit.sept.superprice.auth.JwtUtil;
import au.edu.rmit.sept.superprice.auth.LoginResponse;
import au.edu.rmit.sept.superprice.model.User;
import au.edu.rmit.sept.superprice.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {
    
    AuthenticationManager authenticationManager;
    JwtUtil jwtUtil;
    UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody ObjectNode details) {
        try {
            String password = hashPassword(details.get("password").asText());

            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                details.get("email").asText(),
                password
                ));

            String email = auth.getName();
            User user = new User(email);
            String token = jwtUtil.createJwt(user);
            LoginResponse loginResponse = new LoginResponse(email, token, HttpStatus.OK, "Success");

            return ResponseEntity.ok(loginResponse);
        }
        catch (BadCredentialsException err) {
            LoginResponse loginResponse = new LoginResponse(HttpStatus.BAD_REQUEST, "Incorrect username/password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
        }
        catch (Exception err) {
            LoginResponse loginResponse = new LoginResponse(HttpStatus.BAD_REQUEST, err.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<HttpStatus> signUp(@RequestBody ObjectNode details) {
        try {
            String username = details.get("username").asText();
            String email = details.get("email").asText();
            String firstName = details.get("first_name").asText();
            String lastName = details.get("last_name").asText();
            String phone = details.get("phone").asText();
    
            String password = hashPassword(details.get("password").asText());
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhone(phone);
            user.setPassword(password);
    
            if (userService.getUsersByUsername(username) != null || 
                userService.getUsersByEmail(email) != null) {
                
                throw new Exception("Username or email already used");
            }
            
            userService.saveOrUpdateUser(user);

            return ResponseEntity.ok(HttpStatus.OK);        
        }
        // Exception if username or email already exists
        catch (Exception err) {
            return ResponseEntity.status(400).build();
        }

    }

    private String hashPassword(String plainTextPassword) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = messageDigest.digest(plainTextPassword.getBytes(StandardCharsets.UTF_8));

        StringBuilder stringBuilder = new StringBuilder(new BigInteger(1, hashBytes).toString(16));
        return stringBuilder.toString();
    }

    @PostMapping("/validate")
    public ResponseEntity<User> validateToken(@RequestBody ObjectNode body) {
        try {
            final String email = getEmailFromToken(body.get("token").asText());
            User user = userService.getUsersByEmail(email);
            if (user != null) {
                return ResponseEntity.ok(user);
            }
            else {
                throw new Exception("Invalid user");
            }
        }
        catch (Exception err) {
            return ResponseEntity.badRequest().build();
        }
    }

    private String getEmailFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    private <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    @Autowired
    private Environment env;
    
    private Claims getAllClaims(String token) {
        String secretKey = env.getProperty("secret_key");
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

}
