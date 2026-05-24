package com.schedify.schedify_api.infrastructure.config;

import com.schedify.schedify_api.domain.service.AgendaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainBeanConfig {

    @Bean
    public AgendaService agendaService() {
        return new AgendaService();
    }

}
