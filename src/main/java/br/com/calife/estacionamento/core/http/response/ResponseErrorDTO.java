package br.com.calife.estacionamento.core.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Response error object", contentMediaType = "application/json;charset=UTF-8")
public class ResponseErrorDTO extends ResponseDTO<Map<String, String>> {
    public ResponseErrorDTO(HttpStatus httpStatus, String message, HttpServletRequest request) {
        super(httpStatus, message, request);
    }

    public ResponseErrorDTO(HttpStatus httpStatus, String message, HttpServletRequest request, BindingResult result,
            MessageSource messageSource) {
        this(httpStatus, message, request);
        addErrors(result, messageSource, request.getLocale());
    }

    private void addErrors(BindingResult result, MessageSource messageSource, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : result.getFieldErrors()) {
            String code = Objects.requireNonNull(fieldError.getCodes())[0];
            String message = messageSource.getMessage(code, fieldError.getArguments(), locale);
            errors.put(fieldError.getField(), message);
        }
        super.setPayload(errors);
    }
}
