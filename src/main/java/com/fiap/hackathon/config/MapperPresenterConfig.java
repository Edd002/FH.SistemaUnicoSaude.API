package com.fiap.hackathon.config;

import com.fiap.hackathon.domain.jwt.dto.JwtResponseDTO;
import com.fiap.hackathon.domain.jwt.entity.Jwt;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperPresenterConfig {

    @Bean
    public ModelMapper modelMapperPresenter() {
        ModelMapper modelMapperPresenter = new ModelMapper();
        modelMapperPresenter.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true)
                .setDeepCopyEnabled(true)
                .setAmbiguityIgnored(true)
                .setSkipNullEnabled(true);
        configModelMapperPresenter(modelMapperPresenter);
        return modelMapperPresenter;
    }

    private void configModelMapperPresenter(ModelMapper modelMapperPresenter) {
        configJwtToJwtResponseDTOMapperPresenter(modelMapperPresenter);
    }

    private void configJwtToJwtResponseDTOMapperPresenter(ModelMapper modelMapperPresenter) {
        modelMapperPresenter.typeMap(Jwt.class, JwtResponseDTO.class)
                .addMappings(mapperPresenter -> mapperPresenter.map(jwt -> jwt.getUser().getLogin(), JwtResponseDTO::setUserLogin));
    }
}