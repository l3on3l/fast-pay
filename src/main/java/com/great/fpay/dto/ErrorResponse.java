package com.great.fpay.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorResponse {

    private HttpStatus status;

    private String code;

    private String message;

    private LocalDateTime timeStamp;

}
