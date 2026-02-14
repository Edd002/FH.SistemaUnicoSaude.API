package com.fiap.hackathon.domain.formsubmission;

import com.fiap.hackathon.domain.formsubmission.dto.FormSubmissionPatchRequestDTO;
import com.fiap.hackathon.domain.formsubmission.dto.FormSubmissionPostRequestDTO;
import com.fiap.hackathon.domain.formsubmission.dto.FormSubmissionResponseDTO;
import com.fiap.hackathon.global.base.response.error.BaseErrorResponse401;
import com.fiap.hackathon.global.base.response.error.BaseErrorResponse409;
import com.fiap.hackathon.global.base.response.error.BaseErrorResponse422;
import com.fiap.hackathon.global.base.response.success.BaseSuccessResponse200;
import com.fiap.hackathon.global.base.response.success.BaseSuccessResponse201;
import com.fiap.hackathon.global.base.response.success.nocontent.NoPayloadBaseSuccessResponse200;
import com.fiap.hackathon.global.base.response.success.pageable.BasePageableSuccessResponse200;
import com.fiap.hackathon.global.component.DatabaseManagementComponent;
import com.fiap.hackathon.global.component.HttpBodyComponent;
import com.fiap.hackathon.global.component.HttpHeaderComponent;
import com.fiap.hackathon.global.util.DateTimeUtil;
import com.fiap.hackathon.global.util.JsonUtil;
import com.fiap.hackathon.global.util.ValidationUtil;
import com.fiap.hackathon.global.util.enumerated.DatePatternEnum;
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
public class FormSubmissionControllerTest {

    private final HttpHeaderComponent httpHeaderComponent;
    private final HttpBodyComponent httpBodyComponent;
    private final TestRestTemplate testRestTemplate;
    private final DatabaseManagementComponent databaseManagementComponent;

    @Autowired
    public FormSubmissionControllerTest(HttpHeaderComponent httpHeaderComponent, HttpBodyComponent httpBodyComponent, TestRestTemplate testRestTemplate, DatabaseManagementComponent databaseManagementComponent) {
        this.httpHeaderComponent = httpHeaderComponent;
        this.httpBodyComponent = httpBodyComponent;
        this.testRestTemplate = testRestTemplate;
        this.databaseManagementComponent = databaseManagementComponent;
    }

    private static final String PATH_RESOURCE_FORM_SUBMISSION = "/mock/formsubmission/form_submission.json";

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

    @DisplayName(value = "Teste de sucesso - Criar uma submissão de formulário")
    @Test
    public void createFormSubmissionSuccess() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        FormSubmissionPostRequestDTO formSubmissionPostRequestDTO = JsonUtil.objectFromJson("formSubmissionPostRequestDTO", PATH_RESOURCE_FORM_SUBMISSION, FormSubmissionPostRequestDTO.class, DatePatternEnum.DATE_FORMAT_mm_dd_yyyy_WITH_SLASH.getValue());
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/form-submissions", HttpMethod.POST, new HttpEntity<>(formSubmissionPostRequestDTO, headers), new ParameterizedTypeReference<>() {});
        BaseSuccessResponse201<FormSubmissionResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.CREATED.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotBlank(responseObject.getItem().getHashId()));
        Assertions.assertTrue(ValidationUtil.isNotBlank(responseObject.getItem().getFormTemplate().getHashId()));
    }

    @DisplayName(value = "Teste de sucesso - Submeter um formulário")
    @Test
    public void updateSubmitFormSubmissionSuccess() {
        final String EXISTING_HASH_ID_FORM_SUBMISSION = "026ca461ab024616a2f573aa2bfc6421";
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        FormSubmissionPatchRequestDTO formSubmissionPatchRequestDTO = JsonUtil.objectFromJson("formSubmissionPatchRequestDTO", PATH_RESOURCE_FORM_SUBMISSION, FormSubmissionPatchRequestDTO.class, DatePatternEnum.DATE_FORMAT_mm_dd_yyyy_WITH_SLASH.getValue());
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/form-submissions/submit/" + EXISTING_HASH_ID_FORM_SUBMISSION, HttpMethod.PATCH, new HttpEntity<>(formSubmissionPatchRequestDTO, headers), new ParameterizedTypeReference<>() {});
        BaseSuccessResponse200<FormSubmissionResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotBlank(responseObject.getItem().getHashId()));
        Assertions.assertEquals(responseObject.getItem().getGeneralObservation(), formSubmissionPatchRequestDTO.getGeneralObservation());
    }

    @DisplayName(value = "Teste de falha - Submeter um formulário já submetido")
    @Test
    public void createFormTemplateNameAlreadyExistsFailure() {
        final String EXISTING_HASH_ID_FORM_SUBMISSION = "215c29b4d29d409cb728f4b80ca5be72";
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        FormSubmissionPatchRequestDTO formSubmissionPatchRequestDTO = JsonUtil.objectFromJson("formSubmissionPatchRequestDTO", PATH_RESOURCE_FORM_SUBMISSION, FormSubmissionPatchRequestDTO.class, DatePatternEnum.DATE_FORMAT_mm_dd_yyyy_WITH_SLASH.getValue());
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/form-submissions/submit/" + EXISTING_HASH_ID_FORM_SUBMISSION, HttpMethod.PATCH, new HttpEntity<>(formSubmissionPatchRequestDTO, headers), new ParameterizedTypeReference<>() {});
        BaseErrorResponse422 responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.CONFLICT.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.CONFLICT.value(), responseObject.getStatus());
        Assertions.assertFalse(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotEmpty(responseObject.getMessages()));
    }

    @DisplayName(value = "Teste de sucesso - Submissão de formulário existe ao verificar por filtro de se foi submetido")
    @Test
    public void findByFilterIsSubmittedSuccess() {
        final Boolean isSubmitted = Boolean.TRUE;
        URI uriTemplate = httpHeaderComponent.buildUriWithDefaultQueryParamsGetFilter("/api/v1/form-submissions/filter")
                .queryParam("isSubmitted", isSubmitted)
                .build().encode()
                .toUri();
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange(uriTemplate, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BasePageableSuccessResponse200<FormSubmissionResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertEquals(3, responseObject.getList().size());
        Assertions.assertEquals(3, responseObject.getTotalElements());
        Assertions.assertTrue(responseObject.getList().stream().allMatch(formSubmissionResponseDTO -> formSubmissionResponseDTO.getIsSubmitted().equals(isSubmitted)));
    }

    @DisplayName(value = "Teste de sucesso - Submissão de formulário existe ao verificar por filtro de data de submissão")
    @Test
    public void findByFilterSubmittedAtSuccess() {
        final String submittedAt = "07/02/2026";
        URI uriTemplate = httpHeaderComponent.buildUriWithDefaultQueryParamsGetFilter("/api/v1/form-submissions/filter")
                .queryParam("submittedAt", submittedAt)
                .build().encode()
                .toUri();
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange(uriTemplate, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BasePageableSuccessResponse200<FormSubmissionResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertEquals(4, responseObject.getList().size());
        Assertions.assertEquals(4, responseObject.getTotalElements());
        Assertions.assertTrue(responseObject.getList().stream().allMatch(formSubmissionResponseDTO -> DateTimeUtil.format(DatePatternEnum.DATE_FORMAT_dd_mm_yyyy_WITH_SLASH.getValue(), formSubmissionResponseDTO.getSubmittedAt()).equals(submittedAt)));
    }

    @DisplayName(value = "Teste de sucesso - Submissão de formulário existe ao verificar por filtro de observação geral")
    @Test
    public void findByFilterGeneralObservationSuccess() {
        final String generalObservation = "Formulário poder ser submetido incompleto. Paciente relatou tontura constante, mas não aferiu pressão. Necessário revisão médica.";
        URI uriTemplate = httpHeaderComponent.buildUriWithDefaultQueryParamsGetFilter("/api/v1/form-submissions/filter")
                .queryParam("generalObservation", generalObservation)
                .build().encode()
                .toUri();
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange(uriTemplate, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BasePageableSuccessResponse200<FormSubmissionResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertEquals(4, responseObject.getList().size());
        Assertions.assertEquals(4, responseObject.getTotalElements());
        Assertions.assertTrue(responseObject.getList().stream().allMatch(formSubmissionResponseDTO -> formSubmissionResponseDTO.getGeneralObservation().equals(generalObservation)));
    }

    @DisplayName(value = "Teste de sucesso - Submissão de formulário existe ao verificar por filtro de hash id de template de formulário")
    @Test
    public void findByFilterHashIdFormTemplateSuccess() {
        final String hashIdFormTemplate = "8a2d4778c1214ce48c8601ffffa62ba4";
        URI uriTemplate = httpHeaderComponent.buildUriWithDefaultQueryParamsGetFilter("/api/v1/form-submissions/filter")
                .queryParam("hashIdFormTemplate", hashIdFormTemplate)
                .build().encode()
                .toUri();
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange(uriTemplate, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BasePageableSuccessResponse200<FormSubmissionResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertEquals(1, responseObject.getList().size());
        Assertions.assertEquals(1, responseObject.getTotalElements());
        Assertions.assertEquals(hashIdFormTemplate, responseObject.getList().stream().toList().get(NumberUtils.INTEGER_ZERO).getFormTemplate().getHashId());
    }

    @DisplayName(value = "Teste de sucesso - Busca de informações de submissão de formulário por hash id")
    @Test
    public void findSuccess() {
        final String EXISTING_FORM_SUBMISSION_HASH_ID = "107fd39245d94438befc9950b56b655a";
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/form-submissions/" + EXISTING_FORM_SUBMISSION_HASH_ID, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BaseSuccessResponse200<FormSubmissionResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotBlank(responseObject.getItem().getHashId()));
        Assertions.assertEquals(EXISTING_FORM_SUBMISSION_HASH_ID, responseObject.getItem().getHashId());
    }

    @DisplayName(value = "Teste de falha - Busca de informações de submissão de formulário sem estar autenticado")
    @Test
    public void findWithoutBeingAuthenticatedFailure() {
        final String EXISTING_FORM_SUBMISSION_HASH_ID = "107fd39245d94438befc9950b56b655a";
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithoutBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/form-submissions/" + EXISTING_FORM_SUBMISSION_HASH_ID, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BaseErrorResponse401 responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), responseObject.getStatus());
        Assertions.assertFalse(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotEmpty(responseObject.getMessages()));
    }

    @DisplayName(value = "Teste de sucesso - Deletar submissão de formulário")
    @Test
    public void deleteFormSubmissionSuccess() {
        final String EXISTING_HASH_ID_FORM_SUBMISSION = "026ca461ab024616a2f573aa2bfc6421";
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/form-submissions/" + EXISTING_HASH_ID_FORM_SUBMISSION, HttpMethod.DELETE, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        NoPayloadBaseSuccessResponse200<?> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertNull(responseObject);
    }

    @DisplayName(value = "Teste de falha - Deletar submissão de formulário já concluída")
    @Test
    public void deleteFormSubmissionAlreadyConcludedFailure() {
        final String EXISTING_HASH_ID_FORM_SUBMISSION = "215c29b4d29d409cb728f4b80ca5be72";
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/form-submissions/" + EXISTING_HASH_ID_FORM_SUBMISSION, HttpMethod.DELETE, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BaseErrorResponse409 responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.CONFLICT.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.CONFLICT.value(), responseObject.getStatus());
        Assertions.assertFalse(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotEmpty(responseObject.getMessages()));
    }
}