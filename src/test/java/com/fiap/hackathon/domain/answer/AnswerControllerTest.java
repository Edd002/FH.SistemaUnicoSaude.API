package com.fiap.hackathon.domain.answer;

import com.fiap.hackathon.domain.answer.dto.AnswerResponseDTO;
import com.fiap.hackathon.domain.question.dto.QuestionResponseDTO;
import com.fiap.hackathon.domain.question.enumerated.VisitationAlternativeEnum;
import com.fiap.hackathon.global.base.response.error.BaseErrorResponse401;
import com.fiap.hackathon.global.base.response.success.BaseSuccessResponse200;
import com.fiap.hackathon.global.base.response.success.pageable.BasePageableSuccessResponse200;
import com.fiap.hackathon.global.component.DatabaseManagementComponent;
import com.fiap.hackathon.global.component.HttpBodyComponent;
import com.fiap.hackathon.global.component.HttpHeaderComponent;
import com.fiap.hackathon.global.util.ValidationUtil;
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

    @DisplayName(value = "Teste de sucesso - Busca de informações de resposta por hash id")
    @Test
    public void findSuccess() {
        final String EXISTING_ANSWER_HASH_ID = "2990b614636342c088ae15b2d709ca70";
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/answers/" + EXISTING_ANSWER_HASH_ID, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BaseSuccessResponse200<QuestionResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
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
}