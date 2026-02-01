package com.fiap.hackathon.domain.formtemplate;

import com.fiap.hackathon.domain.formtemplate.dto.FormTemplateGetFilter;
import com.fiap.hackathon.domain.formtemplate.dto.FormTemplatePostRequestDTO;
import com.fiap.hackathon.domain.formtemplate.dto.FormTemplatePutRequestDTO;
import com.fiap.hackathon.domain.formtemplate.dto.FormTemplateResponseDTO;
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
@RequestMapping(value = "/api/v1/form-templates")
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
@Tag(name = "Template de Formulário - Endpoints de Templates de Formulário")
public class FormTemplateController {

    private final FormTemplateServiceGateway formTemplateServiceGateway;

    @Autowired
    public FormTemplateController(FormTemplateServiceGateway formTemplateServiceGateway) {
        this.formTemplateServiceGateway = formTemplateServiceGateway;
    }

    @Operation(method = "POST", summary = "Criar template de formulário.", description = "Criar template de formulário.")
    @ApiResponse(responseCode = "201", description = "Created")
    @PreAuthorize(value = "hasAnyAuthority('HEALTH_PROFESSIONAL')")
    @PostMapping
    public ResponseEntity<BaseSuccessResponse201<FormTemplateResponseDTO>> create(@RequestBody @Valid FormTemplatePostRequestDTO formTemplatePostRequestDTO) {
        log.info("Criando template de formulário...");
        return new BaseSuccessResponse201<>(formTemplateServiceGateway.create(formTemplatePostRequestDTO)).buildResponse();
    }

    @Operation(method = "PUT", summary = "Atualizar template de formulário.", description = "Atualizar template de formulário.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PreAuthorize(value = "hasAnyAuthority('HEALTH_PROFESSIONAL')")
    @PutMapping(value = "/{hashId}")
    public ResponseEntity<BaseSuccessResponse200<FormTemplateResponseDTO>> update(@PathVariable("hashId") String hashId, @RequestBody @Valid FormTemplatePutRequestDTO formTemplatePutRequestDTO) {
        log.info("Atualizando de template de formulário...");
        return new BaseSuccessResponse200<>(formTemplateServiceGateway.update(hashId, formTemplatePutRequestDTO)).buildResponse();
    }

    @Operation(method = "GET", summary = "Buscar template de formulário por filtro.", description = "Buscar template de formulário por filtro.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PreAuthorize(value = "hasAnyAuthority('HEALTH_PROFESSIONAL')")
    @GetMapping(value = "/filter")
    public ResponseEntity<BasePageableSuccessResponse200<FormTemplateResponseDTO>> find(@ParameterObject @Valid FormTemplateGetFilter filter) {
        log.info("Buscando templates de formulário por filtro...");
        return new BasePageableSuccessResponse200<>(formTemplateServiceGateway.find(filter)).buildPageableResponse();
    }

    @Operation(method = "GET", summary = "Buscar template de formulário.", description = "Buscar template de formulário.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PreAuthorize(value = "hasAnyAuthority('HEALTH_PROFESSIONAL')")
    @GetMapping(value = "/{hashId}")
    public ResponseEntity<BaseSuccessResponse200<FormTemplateResponseDTO>> find(@PathVariable("hashId") String hashId) {
        log.info("Buscando template de formulário...");
        return new BaseSuccessResponse200<>(formTemplateServiceGateway.find(hashId)).buildResponse();
    }

    @Operation(method = "DELETE", summary = "Excluir template de formulário.", description = "Excluir template de formulário.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PreAuthorize(value = "hasAnyAuthority('HEALTH_PROFESSIONAL')")
    @DeleteMapping(value = "/{hashId}")
    public ResponseEntity<NoPayloadBaseSuccessResponse200<FormTemplateResponseDTO>> delete(@PathVariable("hashId") String hashId) {
        log.info("Excluindo template de formulário...");
        formTemplateServiceGateway.delete(hashId);
        return new NoPayloadBaseSuccessResponse200<FormTemplateResponseDTO>().buildResponseWithoutPayload();
    }
}