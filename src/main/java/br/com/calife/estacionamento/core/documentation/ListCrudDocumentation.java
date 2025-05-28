package br.com.calife.estacionamento.core.documentation;

import br.com.calife.estacionamento.config.Constants;
import br.com.calife.estacionamento.core.http.response.ResponseErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

public interface ListCrudDocumentation<T, ID> extends CrudDocumentation<T, ID> {

	@Operation(summary = Constants.DOCUMENTATION.MESSAGES.SUMMARY_FIND_ALL, security = @SecurityRequirement(name = "security"), responses = {
	        @ApiResponse(responseCode = "200", description = Constants.DOCUMENTATION.MESSAGES.RESPONSE_200_FIND_ALL),
	        @ApiResponse(responseCode = "404", description = Constants.DOCUMENTATION.MESSAGES.ERROR_404, content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = ResponseErrorDTO.class))),
	        @ApiResponse(responseCode = "403", description = Constants.DOCUMENTATION.MESSAGES.ERROR_403, content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = ResponseErrorDTO.class)))})
	    List<T> findAll();

}
