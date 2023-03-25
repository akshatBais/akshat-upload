package com.gerimedica.test.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response {

    private String message;

    private Object data;

}
