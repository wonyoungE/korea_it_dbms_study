package com.koreait.dbms_study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddUserRespDto {
    private String status;
    private String message;
}
