package com.fiap.hackathon.domain.question;

import com.fiap.hackathon.domain.question.dto.QuestionGetFilter;
import com.fiap.hackathon.domain.question.dto.QuestionPostRequestDTO;
import com.fiap.hackathon.domain.question.dto.QuestionPutRequestDTO;
import com.fiap.hackathon.domain.question.dto.QuestionResponseDTO;
import com.fiap.hackathon.global.base.response.error.*;
import com.fiap.hackathon.global.base.response.success.BaseSuccessResponse200;
import com.fiap.hackathon.global.base.response.success.BaseSuccessResponse201;
import com.fiap.hackathon.global.base.response.success.nocontent.NoPayloadBaseSuccessResponse200;
import com.fiap.hackathon.global.base.response.success.pageable.BasePageableSuccessResponse200;
import io.swagger.v3.oas.annotations.Hidden;
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
@RequestMapping(value = "/api/v1/questions")
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
@Tag(name = "Questões - Endpoints de Questões")
public class QuestionController {

    private final QuestionServiceGateway questionServiceGateway;

    @Autowired
    public QuestionController(QuestionServiceGateway questionServiceGateway) {
        this.questionServiceGateway = questionServiceGateway;
    }

    @Hidden
    @Operation(method = "POST", summary = "Criar questão.", description = "Criar questão.")
    @ApiResponse(responseCode = "201", description = "Created")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'HEALTH_PROFESSIONAL')")
    @PostMapping
    public ResponseEntity<BaseSuccessResponse201<QuestionResponseDTO>> create(@RequestBody @Valid QuestionPostRequestDTO questionPostRequestDTO) {
        log.info("Criando questão...");
        return new BaseSuccessResponse201<>(questionServiceGateway.create(questionPostRequestDTO)).buildResponse();
    }

    @Hidden
    @Operation(method = "PUT", summary = "Atualizar questão.", description = "Atualizar questão.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'HEALTH_PROFESSIONAL')")
    @PutMapping(value = "/{hashId}")
    public ResponseEntity<BaseSuccessResponse200<QuestionResponseDTO>> update(@RequestBody @Valid QuestionPutRequestDTO questionPutRequestDTO) {
        log.info("Atualizando questão...");
        return new BaseSuccessResponse200<>(questionServiceGateway.update(questionPutRequestDTO)).buildResponse();
    }

    @Operation(method = "GET", summary = "Buscar questão por filtro.", description = "Buscar questão por filtro.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'HEALTH_PROFESSIONAL')")
    @GetMapping(value = "/filter")
    public ResponseEntity<BasePageableSuccessResponse200<QuestionResponseDTO>> find(@ParameterObject @Valid QuestionGetFilter filter) {
        log.info("Buscando questões por filtro...");
        return new BasePageableSuccessResponse200<>(questionServiceGateway.find(filter)).buildPageableResponse();
    }

    @Operation(method = "GET", summary = "Buscar questão.", description = "Buscar questão.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'HEALTH_PROFESSIONAL')")
    @GetMapping(value = "/{hashId}")
    public ResponseEntity<BaseSuccessResponse200<QuestionResponseDTO>> find(@PathVariable("hashId") String hashId) {
        log.info("Buscando questão...");
        return new BaseSuccessResponse200<>(questionServiceGateway.find(hashId)).buildResponse();
    }

    @Hidden
    @Operation(method = "DELETE", summary = "Excluir questão.", description = "Excluir questão.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'HEALTH_PROFESSIONAL')")
    @DeleteMapping(value = "/{hashId}")
    public ResponseEntity<NoPayloadBaseSuccessResponse200<QuestionResponseDTO>> delete() {
        log.info("Excluindo questão...");
        questionServiceGateway.delete();
        return new NoPayloadBaseSuccessResponse200<QuestionResponseDTO>().buildResponseWithoutPayload();
    }
}