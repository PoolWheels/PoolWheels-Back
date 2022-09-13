package com.UNIWheels.controller.auth;

import com.UNIWheels.dto.LoginDto;
import com.UNIWheels.dto.TokenDto;
import com.UNIWheels.exception.InvalidCredentialsException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;


@RestController
@RequestMapping( "v1/auth" )
public class AuthController
{

    @Value( "${app.secret}" )
    String secret;

    //private final UserService userService;

    /*ublic AuthController( @Autowired UserService userService )
    {
        this.userService = userService;
    }*/

    @PostMapping
    public TokenDto login(@RequestBody LoginDto loginDto )
    {
        // TODO: REPLACE WITH USER INFORMATION
        //User user = userService.findByEmail( loginDto.email );
        //if ( BCrypt.checkpw(loginDto.getPassword(), user.getPasswordHash()) ){
        if ( BCrypt.checkpw(loginDto.getPassword(), null) ){
            return null;
            //return generateTokenDto(user);
        }
        else
        {
            throw new InvalidCredentialsException();
        }

    }

    /*private String generateToken( User user, Date expirationDate ) {
        return Jwts.builder()
                .setSubject( user.getId() )
                .claim( CLAIMS_ROLES_KEY, user.getRoles() )
                .setIssuedAt(new Date() )
                .setExpiration( expirationDate )
                .signWith( SignatureAlgorithm.HS256, secret )
                .compact();
    }*/

    /*private TokenDto generateTokenDto( User user ) {
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add( Calendar.MINUTE, TOKEN_DURATION_MINUTES );
        String token = generateToken( user, expirationDate.getTime() );
        return new TokenDto( token, expirationDate.getTime() );
    }*/
}