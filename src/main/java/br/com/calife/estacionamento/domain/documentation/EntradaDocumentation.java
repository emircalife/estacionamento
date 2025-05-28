package br.com.calife.estacionamento.domain.documentation;

import br.com.calife.estacionamento.config.Constants;
import br.com.calife.estacionamento.core.documentation.Documentation;
import br.com.calife.estacionamento.core.http.response.ResponseErrorDTO;
import br.com.calife.estacionamento.domain.entity.DTO.EntradaRequestDTO;
import br.com.calife.estacionamento.domain.entity.Vaga;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Entradas", description = "Entradas de veículos nesta API")
public interface EntradaDocumentation {

    // CREATE
    @Operation(summary = Constants.DOCUMENTATION.MESSAGES.SUMMARY_ENTRADA, responses = {
            @ApiResponse(responseCode = "201", description = Constants.DOCUMENTATION.MESSAGES.RESPONSE_201, content = @Content(mediaType = "application/json;charset=UTF-8", schema = @Schema(implementation = Documentation.GenericResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = Constants.DOCUMENTATION.MESSAGES.ERROR_404, content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = ResponseErrorDTO.class))),
            @ApiResponse(responseCode = "403", description = Constants.DOCUMENTATION.MESSAGES.ERROR_403, content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = ResponseErrorDTO.class))),
            @ApiResponse(responseCode = "409", description = Constants.DOCUMENTATION.MESSAGES.ERROR_409, content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = ResponseErrorDTO.class))),
            @ApiResponse(responseCode = "422", description = Constants.DOCUMENTATION.MESSAGES.ERROR_422, content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = ResponseErrorDTO.class))),
    })
    ResponseEntity<String> registrarEntrada(@RequestBody EntradaRequestDTO entradaRequestDTO);
}