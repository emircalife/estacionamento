package br.com.calife.estacionamento.core.documentation;

import br.com.calife.estacionamento.config.Constants;
import br.com.calife.estacionamento.core.http.response.ResponseErrorDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses({
        @ApiResponse(responseCode = "404", description = Constants.DOCUMENTATION.MESSAGES.ERROR_404, content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = ResponseErrorDTO.class))),
        @ApiResponse(responseCode = "403", description = Constants.DOCUMENTATION.MESSAGES.ERROR_403, content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = ResponseErrorDTO.class))),
        @ApiResponse(responseCode = "409", description = Constants.DOCUMENTATION.MESSAGES.ERROR_409, content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = ResponseErrorDTO.class))),
        @ApiResponse(responseCode = "422", description = Constants.DOCUMENTATION.MESSAGES.ERROR_422, content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = ResponseErrorDTO.class))),
})
public @interface CommonApiResponse {
}
