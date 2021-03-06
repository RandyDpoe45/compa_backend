package com.wesdom.compa.backend;


import com.wesdom.compa.backend.dtos.UserLoginDto;
import com.wesdom.compa.backend.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.compa.backend.exceptionhandling.exceptions.GeneralException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Date;

import static com.wesdom.compa.backend.JWTConstants.*;

@Component
public class JWTAuthenticationUtils {

    @Autowired
    private AuthenticationManager authenticationManager;

    public Authentication attemptAuthentication(UserLoginDto userLoginDto)
            throws AuthenticationException {
        try {
            Authentication cc = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword()));
            return cc;
        }catch (BadCredentialsException e){
            throw new GeneralException(ExceptionCodesEnum.BAD_CREDENTIALS,"Las credenciales no son validas");
        }
    }

    public String createToken(Authentication auth) throws IOException, ServletException {

        String token = Jwts.builder().setIssuedAt(new Date()).setIssuer(ISSUER_INFO)
                .setSubject(((User)auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY).compact();
        return token;
    }
}
