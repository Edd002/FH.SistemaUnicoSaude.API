package com.fiap.hackathon.domain.answer;

import com.fiap.hackathon.domain.answer.dto.AnswerRegisterPatchRequestDTO;
import com.fiap.hackathon.domain.answer.dto.AnswerReplyPatchRequestDTO;
import com.fiap.hackathon.domain.answer.dto.AnswerResponseDTO;
import com.fiap.hackathon.global.base.response.error.*;
import com.fiap.hackathon.global.base.response.success.nocontent.NoPayloadBaseSuccessResponse200;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@Validated
@RestController
@RequestMapping(value = "/api/v1/answers")
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
@Tag(name = "Respostas - Endpoints de Respostas")
public class AnswerController {

    private final AnswerServiceGateway answerServiceGateway;

    @Autowired
    public AnswerController(AnswerServiceGateway answerServiceGateway) {
        this.answerServiceGateway = answerServiceGateway;
    }

    @Operation(method = "PATCH", summary = "Registrar resposta de uma questão a um questionário por um profissional de saúde.", description = "Registrar resposta de uma questão a um questionário por um profissional de saúde.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PreAuthorize(value = "hasAnyAuthority('HEALTH_PROFESSIONAL')")
    @PatchMapping(value = "/register")
    public ResponseEntity<NoPayloadBaseSuccessResponse200<AnswerResponseDTO>> registerAnswer(@RequestBody @Valid AnswerRegisterPatchRequestDTO answerRegisterPatchRequestDTO) {
        log.info("Registrando resposta de uma questão...");
        answerServiceGateway.registerAnswer(answerRegisterPatchRequestDTO);
        return new NoPayloadBaseSuccessResponse200<AnswerResponseDTO>().buildResponseWithoutPayload();
    }

    @Operation(method = "PATCH", summary = "Responder uma questão de um questionário por um paciente.", description = "Responder uma questão de um questionário por um paciente.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PreAuthorize(value = "hasAnyAuthority('PACIENT')")
    @PatchMapping(value = "/reply")
    public ResponseEntity<NoPayloadBaseSuccessResponse200<AnswerResponseDTO>> replyAnswer(@RequestBody @Valid AnswerReplyPatchRequestDTO answerReplyPatchRequestDTO) {
        log.info("Respondendo uma questão...");
        answerServiceGateway.replyAnswer(answerReplyPatchRequestDTO);
        return new NoPayloadBaseSuccessResponse200<AnswerResponseDTO>().buildResponseWithoutPayload();
    }
}