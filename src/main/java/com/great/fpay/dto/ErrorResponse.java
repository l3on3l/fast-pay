package com.great.fpay.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ErrorResponse {

    private int status;

    private String code;

    private String message;

    private List<String> detailMessages;

    private LocalDateTime timeStamp;

}
