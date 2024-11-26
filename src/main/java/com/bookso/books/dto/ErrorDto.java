package com.bookso.books.dto;

/*
* @author: santhosh kumar
* @description: Error Dto class
 */

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name= "ErrorDto", description = "Schema for ErrorDto")
public class ErrorDto extends BaseResponseDto {

    @Schema(description = "Error Path", example="uri/api/path")
    private String path;

    @Schema(description = "Response Time", example="9:50")
    private LocalDateTime timeStamp;
}
