package com.training.java.grandmassfood.delivery.api.exception.apiresponse;


import com.training.java.grandmassfood.delivery.api.exception.StandardError;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(
        responseCode = "409",
        description = "Bad request",
        content = {
                @Content(mediaType = "application/json",
                        schema = @Schema(implementation = StandardError.class)
                )
        }
)
public @interface ConflictApiResponse {
}
