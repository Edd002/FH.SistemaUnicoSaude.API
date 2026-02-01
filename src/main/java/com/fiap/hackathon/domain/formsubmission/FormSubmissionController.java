package com.fiap.hackathon.domain.formsubmission;

import com.fiap.hackathon.domain.formsubmission.dto.FormSubmissionGetFilter;
import com.fiap.hackathon.domain.formsubmission.dto.FormSubmissionPostRequestDTO;
import com.fiap.hackathon.domain.formsubmission.dto.FormSubmissionResponseDTO;
import com.fiap.hackathon.global.base.response.error.*;
import com.fiap.hackathon.global.base.response.success.BaseSuccessResponse200;
import com.fiap.hackathon.global.base.response.success.BaseSuccessResponse201;
import com.fiap.hackathon.global.base.response.success.nocontent.NoPayloadBaseSuccessResponse200;
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
import org.springframework.web.bind.annotation.*;

@Log
@Validated
@RestController
@RequestMapping(value = "/api/v1/form-submissions")
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
@Tag(name = "Submissão de Formulário - Endpoints de Submissões de Formulário")
public class FormSubmissionController {

    private final FormSubmissionServiceGateway formSubmissionServiceGateway;

    @Autowired
    public FormSubmissionController(FormSubmissionServiceGateway formSubmissionServiceGateway) {
        this.formSubmissionServiceGateway = formSubmissionServiceGateway;
    }

    @Operation(method = "POST", summary = "Criar submissão de formulário.", description = "Criar submissão de formulário.")
    @ApiResponse(responseCode = "201", description = "Created")
    @PreAuthorize(value = "hasAnyAuthority('HEALTH_PROFESSIONAL')")
    @PostMapping
    public ResponseEntity<BaseSuccessResponse201<FormSubmissionResponseDTO>> create(@RequestBody @Valid FormSubmissionPostRequestDTO formSubmissionPostRequestDTO) {
        log.info("Criando submissão de formulário...");
        return new BaseSuccessResponse201<>(formSubmissionServiceGateway.create(formSubmissionPostRequestDTO)).buildResponse();
    }

    @Operation(method = "PATCH", summary = "Submeter um formulário.", description = "Submeter um formulário.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PreAuthorize(value = "hasAnyAuthority('HEALTH_PROFESSIONAL')")
    @PatchMapping(value = "/submit/{hashId}")
    public ResponseEntity<BaseSuccessResponse200<FormSubmissionResponseDTO>> submitForm(@PathVariable("hashId") String hashId) {
        log.info("Submetendo um formulário...");
        return new BaseSuccessResponse200<>(formSubmissionServiceGateway.submitForm(hashId)).buildResponse();
    }

    @Operation(method = "GET", summary = "Buscar submissão de formulário por filtro.", description = "Buscar submissão de formulário por filtro.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'HEALTH_PROFESSIONAL')")
    @GetMapping(value = "/filter")
    public ResponseEntity<BasePageableSuccessResponse200<FormSubmissionResponseDTO>> find(@ParameterObject @Valid FormSubmissionGetFilter filter) {
        log.info("Buscando submissões de formulário por filtro...");
        return new BasePageableSuccessResponse200<>(formSubmissionServiceGateway.find(filter)).buildPageableResponse();
    }

    @Operation(method = "GET", summary = "Buscar submissão de formulário.", description = "Buscar submissão de formulário.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'HEALTH_PROFESSIONAL')")
    @GetMapping(value = "/{hashId}")
    public ResponseEntity<BaseSuccessResponse200<FormSubmissionResponseDTO>> find(@PathVariable("hashId") String hashId) {
        log.info("Buscando submissão de formulário...");
        return new BaseSuccessResponse200<>(formSubmissionServiceGateway.find(hashId)).buildResponse();
    }

    @Operation(method = "DELETE", summary = "Excluir submissão de formulário.", description = "Excluir submissão de formulário.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PreAuthorize(value = "hasAnyAuthority('HEALTH_PROFESSIONAL')")
    @DeleteMapping(value = "/{hashId}")
    public ResponseEntity<NoPayloadBaseSuccessResponse200<FormSubmissionResponseDTO>> delete(@PathVariable("hashId") String hashId) {
        log.info("Excluindo template de formulário...");
        formSubmissionServiceGateway.delete(hashId);
        return new NoPayloadBaseSuccessResponse200<FormSubmissionResponseDTO>().buildResponseWithoutPayload();
    }
}