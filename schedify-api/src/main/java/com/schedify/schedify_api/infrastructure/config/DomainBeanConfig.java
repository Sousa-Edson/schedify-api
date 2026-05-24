package com.schedify.schedify_api.infrastructure.config;

import com.schedify.schedify_api.domain.service.GeracaoSlotsService;
import com.schedify.schedify_api.domain.service.ValidacaoConflitoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainBeanConfig {

    @Bean
    public ValidacaoConflitoService validacaoConflitoService() {
        return new ValidacaoConflitoService();
    }

    @Bean
    public GeracaoSlotsService geracaoSlotsService() {
        return new GeracaoSlotsService();
    }

}
