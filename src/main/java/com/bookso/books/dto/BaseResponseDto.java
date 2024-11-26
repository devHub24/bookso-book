package com.bookso.books.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "BaseResponseDto", description = "Schema for BaseResponse")
public class BaseResponseDto {

    @Schema(description = "Code of the Response", examples = {"200", "417"})
    private String code;

    @Schema(description = "Message of the Response", example="Book Created Successfully")
    private String message;
}
