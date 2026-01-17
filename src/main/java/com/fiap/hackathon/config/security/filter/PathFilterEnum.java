package com.fiap.hackathon.config.security.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum PathFilterEnum {

    API_V1_JWTS_VALIDATE_GET(HttpMethod.GET, "/api/v1/jwts/validate"),
    API_V1_JWTS_GENERATE_POST(HttpMethod.POST, "/api/v1/jwts/generate"),
    API_V1_JWTS_INVALIDATE_POST(HttpMethod.POST, "/api/v1/jwts/invalidate"),
    API_V1_USERS_DELETE(HttpMethod.DELETE, "/api/v1/users"),
    API_V1_USERS_GET(HttpMethod.GET, "/api/v1/users"),
    API_V1_USERS_FILTER_GET(HttpMethod.GET, "/api/v1/users/filter"),
    API_V1_USERS_PATCH(HttpMethod.PATCH, "/api/v1/users/change-password"),
    API_V1_USERS_POST(HttpMethod.POST, "/api/v1/users"),
    API_V1_USERS_PUT(HttpMethod.PUT, "/api/v1/users");

    private final HttpMethod httpMethod;
    private final String path;

    public String getCompletePath() {
        return "/fh-sistema-unico-saude" + this.getPath();
    }

    public static List<String> getIgnoreFilterConfigPaths() {
        return Arrays.asList(
                "/fh-sistema-unico-saude/swagger-ui/index.html",
                "/fh-sistema-unico-saude/swagger-ui/swagger-ui.css",
                "/fh-sistema-unico-saude/swagger-ui/swagger-ui-standalone-preset.js",
                "/fh-sistema-unico-saude/swagger-ui/swagger-ui-bundle.js",
                "/fh-sistema-unico-saude/v3/api-docs/swagger-config",
                "/fh-sistema-unico-saude/swagger-ui/favicon-32x32.png",
                "/fh-sistema-unico-saude/swagger-ui/favicon-16x16.png",
                "/fh-sistema-unico-saude/v3/api-docs",
                "/fh-sistema-unico-saude/h2-console",
                "/actuator/health"
        );
    }

    public static List<PathFilterEnum> getIgnoreResponseFilterPaths() {
        return Arrays.asList(
                API_V1_JWTS_VALIDATE_GET,
                API_V1_JWTS_GENERATE_POST,
                API_V1_JWTS_INVALIDATE_POST
        );
    }

    public static List<PathFilterEnum> getAllowedPathsWithoutAuthorization() {
        return Arrays.asList(
                API_V1_JWTS_GENERATE_POST,
                API_V1_USERS_POST
        );
    }
}