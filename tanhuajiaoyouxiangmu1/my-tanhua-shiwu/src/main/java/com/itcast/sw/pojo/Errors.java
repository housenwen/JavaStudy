package com.itcast.sw.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Error
 * @Description ToDO
 * @Author 执鉴
 * @Date 2021/5/8 23:45
 * Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Errors implements Serializable {
    private ErrorInfo info;
}
