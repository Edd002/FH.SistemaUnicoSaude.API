package com.fiap.hackathon.domain.answer;

import com.fiap.hackathon.domain.answer.dto.AnswerRegisterPatchRequestDTO;
import com.fiap.hackathon.domain.answer.dto.AnswerReplyPatchRequestDTO;
import com.fiap.hackathon.domain.answer.dto.AnswerResponseDTO;
import com.fiap.hackathon.domain.question.enumerated.VisitationAlternativeEnum;
import com.fiap.hackathon.global.base.response.error.BaseErrorResponse401;
import com.fiap.hackathon.global.base.response.error.BaseErrorResponse403;
import com.fiap.hackathon.global.base.response.error.BaseErrorResponse422;
import com.fiap.hackathon.global.base.response.success.BaseSuccessResponse200;
import com.fiap.hackathon.global.base.response.success.pageable.BasePageableSuccessResponse200;
import com.fiap.hackathon.global.component.DatabaseManagementComponent;
import com.fiap.hackathon.global.component.HttpBodyComponent;
import com.fiap.hackathon.global.component.HttpHeaderComponent;
import com.fiap.hackathon.global.util.JsonUtil;
import com.fiap.hackathon.global.util.ValidationUtil;
import com.fiap.hackathon.global.util.enumerated.DatePatternEnum;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;
import java.util.List;

@ExtendWith(value = {SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnswerControllerTest {

    private final HttpHeaderComponent httpHeaderComponent;
    private final HttpBodyComponent httpBodyComponent;
    private final TestRestTemplate testRestTemplate;
    private final DatabaseManagementComponent databaseManagementComponent;

    @Autowired
    public AnswerControllerTest(HttpHeaderComponent httpHeaderComponent, HttpBodyComponent httpBodyComponent, TestRestTemplate testRestTemplate, DatabaseManagementComponent databaseManagementComponent) {
        this.httpHeaderComponent = httpHeaderComponent;
        this.httpBodyComponent = httpBodyComponent;
        this.testRestTemplate = testRestTemplate;
        this.databaseManagementComponent = databaseManagementComponent;
    }

    private static final String PATH_RESOURCE_ANSWER = "/mock/answer/answer.json";

    @BeforeEach
    public void populateDatabase() {
        List<String> sqlFileScripts = List.of(
                "persistence/state/before_test_state.sql",
                "persistence/city/before_test_city.sql",
                "persistence/address/before_test_address.sql",
                "persistence/loadtable/before_test_load_table.sql",
                "persistence/user/before_test_user.sql",
                "persistence/jwt/before_test_jwt.sql",
                "persistence/question/before_test_question.sql",
                "persistence/formtemplate/before_test_form_template.sql",
                "persistence/formtemplatequestion/before_test_form_template_question.sql",
                "persistence/formsubmission/before_test_form_submission.sql",
                "persistence/answer/before_test_answer.sql"
        );
        databaseManagementComponent.populateDatabase(sqlFileScripts);
    }

    @AfterEach
    public void clearDatabase() {
        databaseManagementComponent.clearDatabase();
    }

    @DisplayName(value = "Teste de sucesso - Registrar uma resposta")
    @Test
    public void updateRegisterAnswerSuccess() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        AnswerRegisterPatchRequestDTO answerRegisterPatchRequestDTO = JsonUtil.objectFromJson("answerRegisterPatchRequestDTO", PATH_RESOURCE_ANSWER, AnswerRegisterPatchRequestDTO.class, DatePatternEnum.DATE_FORMAT_mm_dd_yyyy_WITH_SLASH.getValue());
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/answers/register", HttpMethod.PATCH, new HttpEntity<>(answerRegisterPatchRequestDTO, headers), new ParameterizedTypeReference<>() {});
        BaseSuccessResponse200<AnswerResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotBlank(responseObject.getItem().getHashId()));
        Assertions.assertEquals(responseObject.getItem().getFormSubmission().getHashId(), answerRegisterPatchRequestDTO.getHashIdFormSubmission());
        Assertions.assertEquals(responseObject.getItem().getPatient().getHashId(), answerRegisterPatchRequestDTO.getHashIdPatient());
        Assertions.assertEquals(responseObject.getItem().getQuestion().getHashId(), answerRegisterPatchRequestDTO.getHashIdQuestion());
    }

    @DisplayName(value = "Teste de falha - Registrar uma resposta já cadastrada")
    @Test
    public void updateRegisterAnswerAlreadyDeliveredFailure() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        AnswerRegisterPatchRequestDTO answerRegisterPatchRequestDTO = JsonUtil.objectFromJson("answerRegisterPatchRequestDTOAlreadyRegistered", PATH_RESOURCE_ANSWER, AnswerRegisterPatchRequestDTO.class, DatePatternEnum.DATE_FORMAT_mm_dd_yyyy_WITH_SLASH.getValue());
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/answers/register", HttpMethod.PATCH, new HttpEntity<>(answerRegisterPatchRequestDTO, headers), new ParameterizedTypeReference<>() {});
        BaseErrorResponse422 responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), responseObject.getStatus());
        Assertions.assertFalse(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotEmpty(responseObject.getMessages()));
    }

    @DisplayName(value = "Teste de falha - Registrar uma resposta de formulário já submetido")
    @Test
    public void updateRegisterAnswerFormAlreadyDeliveredFailure() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        AnswerRegisterPatchRequestDTO answerRegisterPatchRequestDTO = JsonUtil.objectFromJson("answerRegisterPatchRequestDTOFormAlreadySubmitted", PATH_RESOURCE_ANSWER, AnswerRegisterPatchRequestDTO.class, DatePatternEnum.DATE_FORMAT_mm_dd_yyyy_WITH_SLASH.getValue());
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/answers/register", HttpMethod.PATCH, new HttpEntity<>(answerRegisterPatchRequestDTO, headers), new ParameterizedTypeReference<>() {});
        BaseErrorResponse422 responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.CONFLICT.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.CONFLICT.value(), responseObject.getStatus());
        Assertions.assertFalse(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotEmpty(responseObject.getMessages()));
    }

    @DisplayName(value = "Teste de sucesso - Registrar uma resposta de formulário sem estar autenticado")
    @Test
    public void updateRegisterAnswerWithoutBeingAuthenticatedFailure() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithoutBearerToken();
        AnswerRegisterPatchRequestDTO answerRegisterPatchRequestDTO = JsonUtil.objectFromJson("answerRegisterPatchRequestDTOFormAlreadySubmitted", PATH_RESOURCE_ANSWER, AnswerRegisterPatchRequestDTO.class, DatePatternEnum.DATE_FORMAT_mm_dd_yyyy_WITH_SLASH.getValue());
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/answers/register", HttpMethod.PATCH, new HttpEntity<>(answerRegisterPatchRequestDTO, headers), new ParameterizedTypeReference<>() {});
        BaseErrorResponse401 responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), responseObject.getStatus());
        Assertions.assertFalse(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotEmpty(responseObject.getMessages()));
    }

    @DisplayName(value = "Teste de sucesso - Registrar uma resposta de formulário autenticado como tipo de usuário não permitido")
    @Test
    public void updateRegisterAnswerAuthenticatedWithWrongUserTypeFailure() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithPatientBearerToken();
        AnswerRegisterPatchRequestDTO answerRegisterPatchRequestDTO = JsonUtil.objectFromJson("answerRegisterPatchRequestDTOFormAlreadySubmitted", PATH_RESOURCE_ANSWER, AnswerRegisterPatchRequestDTO.class, DatePatternEnum.DATE_FORMAT_mm_dd_yyyy_WITH_SLASH.getValue());
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/answers/register", HttpMethod.PATCH, new HttpEntity<>(answerRegisterPatchRequestDTO, headers), new ParameterizedTypeReference<>() {});
        BaseErrorResponse403 responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), responseObject.getStatus());
        Assertions.assertFalse(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotEmpty(responseObject.getMessages()));
    }

    @DisplayName(value = "Teste de sucesso - Responder uma questão")
    @Test
    public void updateReplyAnswerSuccess() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithPatientBearerToken();
        AnswerReplyPatchRequestDTO answerReplyPatchRequestDTO = JsonUtil.objectFromJson("answerReplyPatchRequestDTO", PATH_RESOURCE_ANSWER, AnswerReplyPatchRequestDTO.class, DatePatternEnum.DATE_FORMAT_mm_dd_yyyy_WITH_SLASH.getValue());
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/answers/reply", HttpMethod.PATCH, new HttpEntity<>(answerReplyPatchRequestDTO, headers), new ParameterizedTypeReference<>() {});
        BaseSuccessResponse200<AnswerResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotBlank(responseObject.getItem().getHashId()));
        Assertions.assertEquals(responseObject.getItem().getFormSubmission().getHashId(), answerReplyPatchRequestDTO.getHashIdFormSubmission());
        Assertions.assertEquals(responseObject.getItem().getQuestion().getHashId(), answerReplyPatchRequestDTO.getHashIdQuestion());
    }

    @DisplayName(value = "Teste de sucesso - Responder uma questão sem estar autenticado")
    @Test
    public void updateReplyAnswerWithoutBeingAuthenticatedFailure() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithoutBearerToken();
        AnswerReplyPatchRequestDTO answerReplyPatchRequestDTO = JsonUtil.objectFromJson("answerReplyPatchRequestDTO", PATH_RESOURCE_ANSWER, AnswerReplyPatchRequestDTO.class, DatePatternEnum.DATE_FORMAT_mm_dd_yyyy_WITH_SLASH.getValue());
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/answers/reply", HttpMethod.PATCH, new HttpEntity<>(answerReplyPatchRequestDTO, headers), new ParameterizedTypeReference<>() {});
        BaseErrorResponse401 responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), responseObject.getStatus());
        Assertions.assertFalse(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotEmpty(responseObject.getMessages()));
    }

    @DisplayName(value = "Teste de sucesso - Responder uma questão autenticado como tipo de usuário não permitido")
    @Test
    public void updateReplyAnswerAuthenticatedWithWrongUserTypeFailure() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        AnswerReplyPatchRequestDTO answerReplyPatchRequestDTO = JsonUtil.objectFromJson("answerReplyPatchRequestDTO", PATH_RESOURCE_ANSWER, AnswerReplyPatchRequestDTO.class, DatePatternEnum.DATE_FORMAT_mm_dd_yyyy_WITH_SLASH.getValue());
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/answers/reply", HttpMethod.PATCH, new HttpEntity<>(answerReplyPatchRequestDTO, headers), new ParameterizedTypeReference<>() {});
        BaseErrorResponse403 responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), responseObject.getStatus());
        Assertions.assertFalse(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotEmpty(responseObject.getMessages()));
    }

    @DisplayName(value = "Teste de sucesso - Resposta existe ao verificar por filtro de alternativa de visita")
    @Test
    public void findByFilterVisitationAlternativeSuccess() {
        final VisitationAlternativeEnum visitationAlternative = VisitationAlternativeEnum.V_1;
        URI uriTemplate = httpHeaderComponent.buildUriWithDefaultQueryParamsGetFilter("/api/v1/answers/filter")
                .queryParam("visitationAlternative", visitationAlternative)
                .build().encode()
                .toUri();
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange(uriTemplate, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BasePageableSuccessResponse200<AnswerResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertEquals(5, responseObject.getList().size());
        Assertions.assertEquals(5, responseObject.getTotalElements());
        Assertions.assertTrue(responseObject.getList().stream().allMatch(answerResponseDTO -> VisitationAlternativeEnum.valueOf(answerResponseDTO.getVisitationAlternative()).equals(visitationAlternative)));
    }

    @DisplayName(value = "Teste de sucesso - Resposta existe ao verificar por filtro de resposta entregue")
    @Test
    public void findByFilterDeliveredAnswerSuccess() {
        final String deliveredAnswer = "80kg.";
        URI uriTemplate = httpHeaderComponent.buildUriWithDefaultQueryParamsGetFilter("/api/v1/answers/filter")
                .queryParam("deliveredAnswer", deliveredAnswer)
                .build().encode()
                .toUri();
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange(uriTemplate, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BasePageableSuccessResponse200<AnswerResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertEquals(11, responseObject.getList().size());
        Assertions.assertEquals(11, responseObject.getTotalElements());
        Assertions.assertTrue(responseObject.getList().stream().allMatch(answerResponseDTO -> answerResponseDTO.getDeliveredAnswer().equals(deliveredAnswer)));
    }

    @DisplayName(value = "Teste de sucesso - Resposta existe ao verificar por filtro de hash id de paciente")
    @Test
    public void findByFilterHashIdPatientSuccess() {
        final String hashIdPatient = "ab15a4s1a5qa7af15a41s8a4sa15d1fa";
        URI uriTemplate = httpHeaderComponent.buildUriWithDefaultQueryParamsGetFilter("/api/v1/answers/filter")
                .queryParam("hashIdPatient", hashIdPatient)
                .build().encode()
                .toUri();
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange(uriTemplate, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BasePageableSuccessResponse200<AnswerResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertEquals(11, responseObject.getList().size());
        Assertions.assertEquals(11, responseObject.getTotalElements());
        Assertions.assertTrue(responseObject.getList().stream().allMatch(answerResponseDTO -> answerResponseDTO.getPatient().getHashId().equals(hashIdPatient)));
    }

    @DisplayName(value = "Teste de sucesso - Resposta existe ao verificar por filtro de hash id de submissão de formulário")
    @Test
    public void findByFilterHashIdFormSubmissionSuccess() {
        final String hashIdFormSubmission = "107fd39245d94438befc9950b56b655a";
        URI uriTemplate = httpHeaderComponent.buildUriWithDefaultQueryParamsGetFilter("/api/v1/answers/filter")
                .queryParam("hashIdFormSubmission", hashIdFormSubmission)
                .build().encode()
                .toUri();
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange(uriTemplate, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BasePageableSuccessResponse200<AnswerResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertEquals(3, responseObject.getList().size());
        Assertions.assertEquals(3, responseObject.getTotalElements());
        Assertions.assertTrue(responseObject.getList().stream().allMatch(answerResponseDTO -> answerResponseDTO.getFormSubmission().getHashId().equals(hashIdFormSubmission)));
    }

    @DisplayName(value = "Teste de falha - Buscar resposta sem estar autenticado")
    @Test
    public void findByFilterWithoutBeingAuthenticatedFailure() {
        URI uriTemplate = httpHeaderComponent.buildUriWithDefaultQueryParamsGetFilter("/api/v1/answers/filter")
                .build().encode()
                .toUri();
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithoutBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange(uriTemplate, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BaseErrorResponse401 responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), responseObject.getStatus());
        Assertions.assertFalse(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotEmpty(responseObject.getMessages()));
    }

    @DisplayName(value = "Teste de falha - Buscar resposta autenticado como tipo de usuário não permitido")
    @Test
    public void findByFilterAuthenticatedWithWrongUserTypeFailure() {
        URI uriTemplate = httpHeaderComponent.buildUriWithDefaultQueryParamsGetFilter("/api/v1/answers/filter")
                .build().encode()
                .toUri();
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithPatientBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange(uriTemplate, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BaseErrorResponse403 responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), responseObject.getStatus());
        Assertions.assertFalse(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotEmpty(responseObject.getMessages()));
    }

    @DisplayName(value = "Teste de sucesso - Busca de informações de resposta por hash id")
    @Test
    public void findSuccess() {
        final String EXISTING_ANSWER_HASH_ID = "2990b614636342c088ae15b2d709ca70";
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/answers/" + EXISTING_ANSWER_HASH_ID, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BaseSuccessResponse200<AnswerResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotBlank(responseObject.getItem().getHashId()));
        Assertions.assertEquals(EXISTING_ANSWER_HASH_ID, responseObject.getItem().getHashId());
    }

    @DisplayName(value = "Teste de falha - Busca de informações de resposta sem estar autenticado")
    @Test
    public void findWithoutBeingAuthenticatedFailure() {
        final String EXISTING_ANSWER_HASH_ID = "2990b614636342c088ae15b2d709ca70";
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithoutBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/answers/" + EXISTING_ANSWER_HASH_ID, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BaseErrorResponse401 responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), responseObject.getStatus());
        Assertions.assertFalse(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotEmpty(responseObject.getMessages()));
    }

    @DisplayName(value = "Teste de falha - Busca de informações de resposta autenticado como tipo de usuário não permitido")
    @Test
    public void findAuthenticatedWithWrongUserTypeFailure() {
        final String EXISTING_ANSWER_HASH_ID = "2990b614636342c088ae15b2d709ca70";
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithPatientBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/answers/" + EXISTING_ANSWER_HASH_ID, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BaseErrorResponse403 responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), responseObject.getStatus());
        Assertions.assertFalse(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotEmpty(responseObject.getMessages()));
    }
}