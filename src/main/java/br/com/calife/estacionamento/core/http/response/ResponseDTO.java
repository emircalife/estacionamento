package br.com.calife.estacionamento.core.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Setter
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ResponseDTO<T> {
    /** The HTTP status code associated with the API response. */
    protected int httpStatus;
    /** The Timestamp from a server. */
    private Instant timestamp;
    /** The status of the API response, indicating success or failure. */
    private String status;
    /** The path from a URI request. */
    private String path;
    /** The method from a request. */
    private String method;
    /** A human-readable message providing additional information about the API response. */
    private String message;
    /** An internal code or identifier used for error identification by stakeholders utilizing these APIs.  */
    private String internalCode;
    /** The payload included in the API response, holding the actual content.    */
    private T payload;

    public ResponseDTO() {this.setTimestamp(Instant.now());}
    public ResponseDTO(HttpStatus httpStatus, String message) {
        this();
        this.setHttpStatus(httpStatus.value());
        this.setStatus(httpStatus.series().name());  // .getReasonPhrase()
        this.setMessage(message);
    }
    public ResponseDTO(HttpStatus httpStatus, String message, HttpServletRequest request) {
        this(httpStatus, message);
        this.setPath(request.getMethod());
        this.setMethod(request.getRequestURI());
    }
    public ResponseDTO(HttpStatus httpStatus, String message, HttpServletRequest request, T payload) {
        this(httpStatus, message, request);
        this.setPayload(payload);
    }
    public ResponseDTO(T payload) {
        this(HttpStatus.OK, "message", null);
        this.setPayload(payload);
    }
}
