package com.itsol.recruit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject {
    private HttpStatus status;
    private String message;
    private Object object;

    public ResponseObject(HttpStatus ok, String s) {
    }
}
