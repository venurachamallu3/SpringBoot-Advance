package com.ExceptionDTO.ExceptionDTO.Exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private LocalDateTime currentDateTime;
    private String message;
    private String details;
}
