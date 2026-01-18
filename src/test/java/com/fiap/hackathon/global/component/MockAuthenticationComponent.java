package com.fiap.hackathon.global.component;

import com.fiap.hackathon.domain.user.authuser.BundleAuthUserDetailsService;
import com.fiap.hackathon.domain.user.entity.User;
import com.fiap.hackathon.global.util.JsonUtil;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MockAuthenticationComponent {

    private static final String PATH_RESOURCE_USER = "/mock/user/user.json";

    private final BundleAuthUserDetailsService bundleAuthUserDetailsService;

    @Autowired
    public MockAuthenticationComponent(BundleAuthUserDetailsService bundleAuthUserDetailsService) {
        this.bundleAuthUserDetailsService = bundleAuthUserDetailsService;
    }

    public void mockAuthenticationHealthProfessional() {
        User user = JsonUtil.objectFromJson("userHealthProfessional", PATH_RESOURCE_USER, User.class);
        mockAuthentication(user);
    }

    public void mockAuthenticationPatient() {
        User user = JsonUtil.objectFromJson("userPatient", PATH_RESOURCE_USER, User.class);
        mockAuthentication(user);
    }

    private void mockAuthentication(User user) {
        Authentication authentication = bundleAuthUserDetailsService.getAuthentication(user.getLogin());
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }
}