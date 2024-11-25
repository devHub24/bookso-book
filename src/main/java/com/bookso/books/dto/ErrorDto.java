package com.bookso.books.dto;

/*
* @author: santhosh kumar
* @description: Error Dto class
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode
public class ErrorDto extends BaseResponseDto {

    private String path;
    private LocalDateTime timeStamp;
}
