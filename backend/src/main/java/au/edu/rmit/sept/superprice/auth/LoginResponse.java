package au.edu.rmit.sept.superprice.auth;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    
    private String email;
    private String token;
    private HttpStatus httpStatus;
    private String message;

    public LoginResponse(String email, String token, HttpStatus httpStatus) {
        this.email = email;
        this.token = token;
        this.httpStatus = httpStatus;
    }

    public LoginResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
