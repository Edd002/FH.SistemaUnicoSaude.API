package com.fiap.hackathon.domain.user;

import com.fiap.hackathon.domain.user.dto.UserPostRequestDTO;
import com.fiap.hackathon.domain.user.dto.UserPutRequestDTO;
import com.fiap.hackathon.domain.user.dto.UserResponseDTO;
import com.fiap.hackathon.domain.user.dto.UserUpdatePasswordPatchRequestDTO;
import com.fiap.hackathon.global.base.response.error.BaseErrorResponse400;
import com.fiap.hackathon.global.base.response.error.BaseErrorResponse401;
import com.fiap.hackathon.global.base.response.error.BaseErrorResponse409;
import com.fiap.hackathon.global.base.response.success.BaseSuccessResponse200;
import com.fiap.hackathon.global.base.response.success.BaseSuccessResponse201;
import com.fiap.hackathon.global.base.response.success.nocontent.NoPayloadBaseSuccessResponse200;
import com.fiap.hackathon.global.base.response.success.pageable.BasePageableSuccessResponse200;
import com.fiap.hackathon.global.component.DatabaseManagementComponent;
import com.fiap.hackathon.global.component.HttpBodyComponent;
import com.fiap.hackathon.global.component.HttpHeaderComponent;
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
public class UserControllerTest {

    private final HttpHeaderComponent httpHeaderComponent;
    private final HttpBodyComponent httpBodyComponent;
    private final TestRestTemplate testRestTemplate;
    private final DatabaseManagementComponent databaseManagementComponent;

    @Autowired
    public UserControllerTest(HttpHeaderComponent httpHeaderComponent, HttpBodyComponent httpBodyComponent, TestRestTemplate testRestTemplate, DatabaseManagementComponent databaseManagementComponent) {
        this.httpHeaderComponent = httpHeaderComponent;
        this.httpBodyComponent = httpBodyComponent;
        this.testRestTemplate = testRestTemplate;
        this.databaseManagementComponent = databaseManagementComponent;
    }

    private static final String PATH_RESOURCE_USER = "/mock/user/user.json";

    @BeforeEach
    public void populateDatabase() {
        List<String> sqlFileScripts = List.of(
                "persistence/state/before_test_state.sql",
                "persistence/city/before_test_city.sql",
                "persistence/address/before_test_address.sql",
                "persistence/loadtable/before_test_load_table.sql",
                "persistence/user/before_test_user.sql",
                "persistence/jwt/before_test_jwt.sql"
        );
        databaseManagementComponent.populateDatabase(sqlFileScripts);
    }

    @AfterEach
    public void clearDatabase() {
        databaseManagementComponent.clearDatabase();
    }

    @DisplayName(value = "Teste de sucesso - Criar um usuário profissional de saúde")
    @Test
    public void createUserHealthProfessionalSuccess() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithoutBearerToken();
        UserPostRequestDTO userPostRequestDTO = JsonUtil.objectFromJson("userPostRequestDTHealthProfessional", PATH_RESOURCE_USER, UserPostRequestDTO.class, DatePatternEnum.DATE_FORMAT_mm_dd_yyyy_WITH_SLASH.getValue());
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/users", HttpMethod.POST, new HttpEntity<>(userPostRequestDTO, headers), new ParameterizedTypeReference<>() {});
        BaseSuccessResponse201<UserResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.CREATED.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotBlank(responseObject.getItem().getHashId()));
        Assertions.assertTrue(ValidationUtil.isNotBlank(responseObject.getItem().getAddress().getHashId()));
    }

    @DisplayName(value = "Teste de sucesso - Criar um usuário paciente")
    @Test
    public void createUserPatientSuccess() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithoutBearerToken();
        UserPostRequestDTO userPostRequestDTO = JsonUtil.objectFromJson("userPostRequestDTOPatient", PATH_RESOURCE_USER, UserPostRequestDTO.class, DatePatternEnum.DATE_FORMAT_mm_dd_yyyy_WITH_SLASH.getValue());
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/users", HttpMethod.POST, new HttpEntity<>(userPostRequestDTO, headers), new ParameterizedTypeReference<>() {});
        BaseSuccessResponse201<UserResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.CREATED.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotBlank(responseObject.getItem().getHashId()));
        Assertions.assertTrue(ValidationUtil.isNotBlank(responseObject.getItem().getAddress().getHashId()));
    }

    @DisplayName(value = "Teste de sucesso - Atualizar um usuário profissional de saúde")
    @Test
    public void updateUserHealthProfessionalSuccess() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        UserPutRequestDTO userPutRequestDTO = JsonUtil.objectFromJson("userPutRequestDTOHealthProfessional", PATH_RESOURCE_USER, UserPutRequestDTO.class, DatePatternEnum.DATE_FORMAT_mm_dd_yyyy_WITH_SLASH.getValue());
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/users", HttpMethod.PUT, new HttpEntity<>(userPutRequestDTO, headers), new ParameterizedTypeReference<>() {});
        BaseSuccessResponse200<UserResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotBlank(responseObject.getItem().getHashId()));
        Assertions.assertTrue(ValidationUtil.isNotBlank(responseObject.getItem().getAddress().getHashId()));
    }

    @DisplayName(value = "Teste de sucesso - Atualizar um usuário paciente")
    @Test
    public void updateUserPatientSuccess() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithPatientBearerToken();
        UserPutRequestDTO userPutRequestDTO = JsonUtil.objectFromJson("userPutRequestDTOPatient", PATH_RESOURCE_USER, UserPutRequestDTO.class, DatePatternEnum.DATE_FORMAT_mm_dd_yyyy_WITH_SLASH.getValue());
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/users", HttpMethod.PUT, new HttpEntity<>(userPutRequestDTO, headers), new ParameterizedTypeReference<>() {});
        BaseSuccessResponse200<UserResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotBlank(responseObject.getItem().getHashId()));
        Assertions.assertTrue(ValidationUtil.isNotBlank(responseObject.getItem().getAddress().getHashId()));
    }

    @DisplayName(value = "Teste de sucesso - Atualizar senha de um usuário profissional de saúde")
    @Test
    public void updateUserPasswordHealthProfessionalSuccess() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        UserUpdatePasswordPatchRequestDTO userUpdatePasswordPatchRequestDTO = JsonUtil.objectFromJson("userUpdatePasswordPatchRequestDTOHealthProfessional", PATH_RESOURCE_USER, UserUpdatePasswordPatchRequestDTO.class, DatePatternEnum.DATE_FORMAT_mm_dd_yyyy_WITH_SLASH.getValue());
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/users/change-password", HttpMethod.PATCH, new HttpEntity<>(userUpdatePasswordPatchRequestDTO, headers), new ParameterizedTypeReference<>() {});
        NoPayloadBaseSuccessResponse200<?> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertNull(responseObject);
    }

    @DisplayName(value = "Teste de sucesso - Atualizar senha de um usuário paciente")
    @Test
    public void updateUserPasswordPatientSuccess() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithPatientBearerToken();
        UserUpdatePasswordPatchRequestDTO userUpdatePasswordPatchRequestDTO = JsonUtil.objectFromJson("userUpdatePasswordPatchRequestDTOPatient", PATH_RESOURCE_USER, UserUpdatePasswordPatchRequestDTO.class, DatePatternEnum.DATE_FORMAT_mm_dd_yyyy_WITH_SLASH.getValue());
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/users/change-password", HttpMethod.PATCH, new HttpEntity<>(userUpdatePasswordPatchRequestDTO, headers), new ParameterizedTypeReference<>() {});
        NoPayloadBaseSuccessResponse200<?> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertNull(responseObject);
    }

    @DisplayName(value = "Teste de falha - Atualizar senha de um usuário paciente com a senha atual informada incorretamente")
    @Test
    public void updateUserPasswordPatientWrongActualPasswordFailure() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithPatientBearerToken();
        UserUpdatePasswordPatchRequestDTO userUpdatePasswordPatchRequestDTO = JsonUtil.objectFromJson("userUpdatePasswordPatchRequestDTOPatientWrongActualPassword", PATH_RESOURCE_USER, UserUpdatePasswordPatchRequestDTO.class, DatePatternEnum.DATE_FORMAT_mm_dd_yyyy_WITH_SLASH.getValue());
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/users/change-password", HttpMethod.PATCH, new HttpEntity<>(userUpdatePasswordPatchRequestDTO, headers), new ParameterizedTypeReference<>() {});
        BaseErrorResponse400 responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseObject.getStatus());
        Assertions.assertFalse(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotEmpty(responseObject.getMessages()));
    }

    @DisplayName(value = "Teste de falha - Atualizar senha de um usuário paciente com a senha cadastrada igual a nova")
    @Test
    public void updateUserPasswordPatientSamePasswordFailure() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithPatientBearerToken();
        UserUpdatePasswordPatchRequestDTO userUpdatePasswordPatchRequestDTO = JsonUtil.objectFromJson("userUpdatePasswordPatchRequestDTOPatientSamePassword", PATH_RESOURCE_USER, UserUpdatePasswordPatchRequestDTO.class, DatePatternEnum.DATE_FORMAT_mm_dd_yyyy_WITH_SLASH.getValue());
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/users/change-password", HttpMethod.PATCH, new HttpEntity<>(userUpdatePasswordPatchRequestDTO, headers), new ParameterizedTypeReference<>() {});
        BaseErrorResponse400 responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseObject.getStatus());
        Assertions.assertFalse(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotEmpty(responseObject.getMessages()));
    }

    @DisplayName(value = "Teste de falha - Atualizar senha de um usuário paciente com a senha cadastrada diferente da confirmação")
    @Test
    public void updateUserPasswordPatientWrongPasswordConfirmationFailure() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithPatientBearerToken();
        UserUpdatePasswordPatchRequestDTO userUpdatePasswordPatchRequestDTO = JsonUtil.objectFromJson("userUpdatePasswordPatchRequestDTOPatientWrongPasswordConfirmation", PATH_RESOURCE_USER, UserUpdatePasswordPatchRequestDTO.class, DatePatternEnum.DATE_FORMAT_mm_dd_yyyy_WITH_SLASH.getValue());
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/users/change-password", HttpMethod.PATCH, new HttpEntity<>(userUpdatePasswordPatchRequestDTO, headers), new ParameterizedTypeReference<>() {});
        BaseErrorResponse400 responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseObject.getStatus());
        Assertions.assertFalse(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotEmpty(responseObject.getMessages()));
    }

    @DisplayName(value = "Teste de sucesso - Usuário existe ao verificar por filtro de nome")
    @Test
    public void findByFilterNameSuccess() {
        final String name = "Admin";
        URI uriTemplate = httpHeaderComponent.buildUriWithDefaultQueryParamsGetFilter("/api/v1/users/filter")
                .queryParam("name", name)
                .build().encode()
                .toUri();
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange(uriTemplate, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BasePageableSuccessResponse200<UserResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertEquals(1, responseObject.getList().size());
        Assertions.assertEquals(1, responseObject.getTotalElements());
        Assertions.assertEquals(name, responseObject.getList().stream().toList().get(NumberUtils.INTEGER_ZERO).getName());
    }

    @DisplayName(value = "Teste de sucesso - Usuário existe ao verificar por filtro de email")
    @Test
    public void findByFilterEmailSuccess() {
        final String email = "admin@email.com";
        URI uriTemplate = httpHeaderComponent.buildUriWithDefaultQueryParamsGetFilter("/api/v1/users/filter")
                .queryParam("email", email)
                .build().encode()
                .toUri();
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange(uriTemplate, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BasePageableSuccessResponse200<UserResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertEquals(1, responseObject.getList().size());
        Assertions.assertEquals(1, responseObject.getTotalElements());
        Assertions.assertEquals(email, responseObject.getList().stream().toList().get(NumberUtils.INTEGER_ZERO).getEmail());
    }

    @DisplayName(value = "Teste de sucesso - Busca de informações de usuário logado")
    @Test
    public void findSuccess() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithHealthProfessionalBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/users", HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BaseSuccessResponse200<UserResponseDTO> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK.value(), responseObject.getStatus());
        Assertions.assertTrue(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotBlank(responseObject.getItem().getHashId()));
        Assertions.assertTrue(ValidationUtil.isNotBlank(responseObject.getItem().getAddress().getHashId()));
    }

    @DisplayName(value = "Teste de falha - Busca de informações de usuário sem estar autenticado")
    @Test
    public void findWithoutBeingAuthenticatedFailure() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithoutBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/users", HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BaseErrorResponse401 responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), responseObject.getStatus());
        Assertions.assertFalse(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotEmpty(responseObject.getMessages()));
    }

    @DisplayName(value = "Teste de sucesso - Deletar usuário")
    @Test
    public void deleteUserSuccess() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithPatientBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/users", HttpMethod.DELETE, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        NoPayloadBaseSuccessResponse200<?> responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertNull(responseObject);
    }

    @DisplayName(value = "Teste de falha - Deletar usuário administrador")
    @Test
    public void deleteUserAdministratorFailure() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithAdminBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/users", HttpMethod.DELETE, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BaseErrorResponse409 responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.CONFLICT.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.CONFLICT.value(), responseObject.getStatus());
        Assertions.assertFalse(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotEmpty(responseObject.getMessages()));
    }

    @DisplayName(value = "Teste de falha - Deletar usuário não estando autenticado")
    @Test
    public void deleteUserWithoutBeingAuthenticatedFailure() {
        HttpHeaders headers = httpHeaderComponent.generateHeaderWithoutBearerToken();
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/v1/users", HttpMethod.DELETE, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});
        BaseErrorResponse401 responseObject = httpBodyComponent.responseEntityToObject(responseEntity, new TypeToken<>() {});
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), responseObject.getStatus());
        Assertions.assertFalse(responseObject.isSuccess());
        Assertions.assertTrue(ValidationUtil.isNotEmpty(responseObject.getMessages()));
    }
}