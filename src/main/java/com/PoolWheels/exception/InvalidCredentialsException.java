package com.PoolWheels.exception;

import com.PoolWheels.dto.ServerErrorResponseDto;
import com.PoolWheels.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;
import javax.ws.rs.InternalServerErrorException;

public class InvalidCredentialsException extends InternalServerErrorException {

    private ServerErrorResponseDto serverErrorResponseDto;


    public InvalidCredentialsException() {
        super(String.valueOf(HttpStatus.NOT_FOUND));
        this.serverErrorResponseDto = new ServerErrorResponseDto("User not found", ErrorCodeEnum.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}