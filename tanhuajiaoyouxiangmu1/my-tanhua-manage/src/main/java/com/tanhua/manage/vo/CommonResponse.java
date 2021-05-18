package com.tanhua.manage.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message = "";

    public CommonResponse(final String message) {
        this.message = message;
    }

    public CommonResponse() {

    }
}
