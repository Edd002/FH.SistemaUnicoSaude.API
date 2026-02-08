package com.fiap.hackathon.domain.question;

import com.fiap.hackathon.domain.question.dto.QuestionResponseDTO;
import com.fiap.hackathon.domain.question.enumerated.QuestionTopicEnum;
import com.fiap.hackathon.domain.question.enumerated.QuestionTypeEnum;
import com.fiap.hackathon.global.base.response.error.BaseErrorResponse401;
import com.fiap.hackathon.global.base.response.success.BaseSuccessResponse200;
import com.fiap.hackathon.global.base.response.success.pageable.BasePageableSuccessResponse200;
import com.fiap.hackathon.global.component.DatabaseManagementComponent;
import com.fiap.hackathon.global.component.HttpBodyComponent;
import com.fiap.hackathon.global.component.HttpHeaderComponent;
import com.fiap.hackathon.global.util.ValidationUtil;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.math.NumberUtils;
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
public class QuestionControllerTest {

    private final HttpHeaderComponent httpHeaderComponent;
    private final HttpBodyComponent httpBodyComponent;
    private final TestRestTemplate testRestTemplate;
    private final DatabaseManagementComponent databaseManagementComponent;

    @Autowired
    public QuestionControllerTest(HttpHeaderComponent httpHeaderComponent, HttpBodyComponent httpBodyComponent, TestRestTemplate testRestTemplate, DatabaseManagementComponent databaseManagementComponent) {
        this.httpHeaderComponent = httpHeaderComponent;
        this.httpBodyComponent = httpBodyComponent;
        this.testRestTemplate = testRestTemplate;
        this.databaseManagementComponent = databaseManagementComponent;
    }

    private static final String PATH_RESOURCE_QUESTION = "/mock/question/question.json";

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

    @DisplayName(value = "Teste de sucesso - Questão existe ao verificar por filtro de título")
    @Test
    public void findByFilterTitleSuccess() {
        final String title = "TURNO";
        URI uriTemplate = httpHeaderComponent.buildUriWithDefaultQueryParamsGetFilter("/api/v1/questions/filter")
                .queryParam("title", title)
                .build().encode()
                .toUri();
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange(uriTemplate, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BasePageableSuccessResponse200<QuestionResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertEquals(1, responseObject.getList().size());
        Assertions.assertEquals(1, responseObject.getTotalElements());
        Assertions.assertEquals(title, responseObject.getList().stream().toList().get(NumberUtils.INTEGER_ZERO).getTitle());
    }

    @DisplayName(value = "Teste de sucesso - Questão existe ao verificar por filtro de descrição")
    @Test
    public void findByFilterDescriptionSuccess() {
        final String description = "(F) Feminino (M) Masculino (I) Indeterminado";
        URI uriTemplate = httpHeaderComponent.buildUriWithDefaultQueryParamsGetFilter("/api/v1/questions/filter")
                .queryParam("description", description)
                .build().encode()
                .toUri();
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange(uriTemplate, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BasePageableSuccessResponse200<QuestionResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertEquals(1, responseObject.getList().size());
        Assertions.assertEquals(1, responseObject.getTotalElements());
        Assertions.assertEquals(description, responseObject.getList().stream().toList().get(NumberUtils.INTEGER_ZERO).getDescription());
    }

    @DisplayName(value = "Teste de sucesso - Questão existe ao verificar por filtro de tema")
    @Test
    public void findByFilterTopicSuccess() {
        final QuestionTopicEnum topic = QuestionTopicEnum.GERAL;
        URI uriTemplate = httpHeaderComponent.buildUriWithDefaultQueryParamsGetFilter("/api/v1/questions/filter")
                .queryParam("topic", topic)
                .build().encode()
                .toUri();
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange(uriTemplate, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BasePageableSuccessResponse200<QuestionResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertEquals(8, responseObject.getList().size());
        Assertions.assertEquals(8, responseObject.getTotalElements());
        Assertions.assertEquals(topic, QuestionTopicEnum.valueOf(responseObject.getList().stream().toList().get(NumberUtils.INTEGER_ZERO).getTopic()));
    }

    @DisplayName(value = "Teste de sucesso - Questão existe ao verificar por filtro de tipo")
    @Test
    public void findByFilterTypeSuccess() {
        final QuestionTypeEnum type = QuestionTypeEnum.OPEN_FIELD;
        URI uriTemplate = httpHeaderComponent.buildUriWithDefaultQueryParamsGetFilter("/api/v1/questions/filter")
                .queryParam("type", type)
                .build().encode()
                .toUri();
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange(uriTemplate, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BasePageableSuccessResponse200<QuestionResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertEquals(7, responseObject.getList().size());
        Assertions.assertEquals(7, responseObject.getTotalElements());
        Assertions.assertEquals(type, QuestionTypeEnum.valueOf(responseObject.getList().stream().toList().get(NumberUtils.INTEGER_ZERO).getType()));
    }

    @DisplayName(value = "Teste de sucesso - Busca de informações de questão por hash id")
    @Test
    public void findSuccess() {
        final String EXISTING_QUESTION_HASH_ID = "3aecb2404f444b15a498cccaf2b2c820";
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/questions/" + EXISTING_QUESTION_HASH_ID, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BaseSuccessResponse200<QuestionResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotBlank(responseObject.getItem().getHashId()));
        Assertions.assertEquals(EXISTING_QUESTION_HASH_ID, responseObject.getItem().getHashId());
    }

    @DisplayName(value = "Teste de falha - Busca de informações de questão sem estar autenticado")
    @Test
    public void findWithoutBeingAuthenticatedFailure() {
        final String EXISTING_QUESTION_HASH_ID = "3aecb2404f444b15a498cccaf2b2c820";
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithoutBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/questions/" + EXISTING_QUESTION_HASH_ID, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BaseErrorResponse401 responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), responseObject.getStatus());
        Assertions.assertFalse(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotEmpty(responseObject.getMessages()));
    }
}