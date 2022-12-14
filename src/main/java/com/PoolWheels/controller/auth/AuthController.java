package com.PoolWheels.controller.auth;

import com.PoolWheels.dto.LoginDto;
import com.PoolWheels.dto.TokenDto;
import com.PoolWheels.entities.User;
import com.PoolWheels.exception.InvalidCredentialsException;
import com.PoolWheels.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;

import static com.PoolWheels.utils.Constants.CLAIMS_ROLES_KEY;
import static com.PoolWheels.utils.Constants.TOKEN_DURATION_MINUTES;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping( "v1/auth" )
public class AuthController
{

    @Value( "${app.secret}" )
    String secret;

    private final UserService userService;

    public AuthController( @Autowired UserService userService )
    {
        this.userService = userService;
    }

    @PostMapping
    public TokenDto login(@RequestBody LoginDto loginDto )
    {
        User user = userService.findByEmail(loginDto.getEmail());
        if(BCrypt.checkpw(loginDto.getPassword(), user.getPassword() ) ){
            //if ( loginDto.getPassword().equals(user.getPassword()) ){
            return generateTokenDto(user);
        }
        else
        {
            throw new InvalidCredentialsException();
        }

    }

    private String generateToken( User user, Date expirationDate ) {
        return Jwts.builder()
                .setSubject( user.getId() )
                .claim( CLAIMS_ROLES_KEY, user.getRol())
                .setIssuedAt(new Date() )
                .setExpiration( expirationDate )
                .signWith( SignatureAlgorithm.HS256, secret )
                .compact();
    }

    private TokenDto generateTokenDto(User user ) {
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add( Calendar.MINUTE, TOKEN_DURATION_MINUTES );
        String token = generateToken( user, expirationDate.getTime() );
        return new TokenDto( token, expirationDate.getTime() );
    }
}