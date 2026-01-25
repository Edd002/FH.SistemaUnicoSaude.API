package com.fiap.hackathon.domain.questionnaire;

import com.fiap.hackathon.domain.questionnaire.dto.QuestionnaireGetFilter;
import com.fiap.hackathon.domain.questionnaire.dto.QuestionnaireResponseDTO;
import com.fiap.hackathon.global.base.response.error.*;
import com.fiap.hackathon.global.base.response.success.BaseSuccessResponse200;
import com.fiap.hackathon.global.base.response.success.pageable.BasePageableSuccessResponse200;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@Validated
@RestController
@RequestMapping(value = "/api/v1/questionnaires")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseErrorResponse400.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseErrorResponse401.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseErrorResponse403.class))),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseErrorResponse404.class))),
        @ApiResponse(responseCode = "405", description = "Method Not Allowed", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseErrorResponse405.class))),
        @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseErrorResponse409.class))),
        @ApiResponse(responseCode = "412", description = "Precondition Failed", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseErrorResponse412.class))),
        @ApiResponse(responseCode = "415", description = "Unsupported Media Type", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseErrorResponse415.class))),
        @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseErrorResponse422.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseErrorResponse500.class)))
})
@Tag(name = "Questionários - Endpoints de Questionários")
public class QuestionnaireController {

    private final QuestionnaireServiceGateway questionnaireServiceGateway;

    @Autowired
    public QuestionnaireController(QuestionnaireServiceGateway questionnaireServiceGateway) {
        this.questionnaireServiceGateway = questionnaireServiceGateway;
    }

    @Operation(method = "GET", summary = "Buscar questionário por filtro.", description = "Buscar questionário por filtro.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'HEALTH_PROFESSIONAL')")
    @GetMapping(value = "/filter")
    public ResponseEntity<BasePageableSuccessResponse200<QuestionnaireResponseDTO>> find(@ParameterObject @Valid QuestionnaireGetFilter filter) {
        log.info("Buscando questionários por filtro...");
        return new BasePageableSuccessResponse200<>(questionnaireServiceGateway.find(filter)).buildPageableResponse();
    }

    @Operation(method = "GET", summary = "Buscar questionário.", description = "Buscar questionário.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'HEALTH_PROFESSIONAL')")
    @GetMapping(value = "/{hashId}")
    public ResponseEntity<BaseSuccessResponse200<QuestionnaireResponseDTO>> find(@PathVariable("hashId") String hashId) {
        log.info("Buscando questionário...");
        return new BaseSuccessResponse200<>(questionnaireServiceGateway.find(hashId)).buildResponse();
    }
}