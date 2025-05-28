package br.com.calife.estacionamento.core.documentation;

import br.com.calife.estacionamento.config.Constants;
import br.com.calife.estacionamento.core.http.request.ResponsePageableDTO;
import br.com.calife.estacionamento.core.http.response.ResponseErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PageableCrudDocumentation<T, ID> extends ListCrudDocumentation<T, ID> {

	@Operation(summary = Constants.DOCUMENTATION.MESSAGES.SUMMARY_UPDATE, security = @SecurityRequirement(name = "security"), responses = {
			@ApiResponse(responseCode = "200", description = Constants.DOCUMENTATION.MESSAGES.RESPONSE_200_UPDATE, content = @Content(mediaType = "application/json;charset=UTF-8", schema = @Schema(implementation = ResponsePageableDTO.class))),
			@ApiResponse(responseCode = "404", description = Constants.DOCUMENTATION.MESSAGES.ERROR_404, content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = ResponseErrorDTO.class))),
			@ApiResponse(responseCode = "403", description = Constants.DOCUMENTATION.MESSAGES.ERROR_403, content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = ResponseErrorDTO.class))),
			@ApiResponse(responseCode = "409", description = Constants.DOCUMENTATION.MESSAGES.ERROR_409, content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = ResponseErrorDTO.class))),
			@ApiResponse(responseCode = "422", description = Constants.DOCUMENTATION.MESSAGES.ERROR_422, content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = ResponseErrorDTO.class))), })
	Page<T> search(ID id, Pageable pageable);
	
}
