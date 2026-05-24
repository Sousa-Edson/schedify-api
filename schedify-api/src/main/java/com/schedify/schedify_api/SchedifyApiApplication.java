package com.schedify.schedify_api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SchedifyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedifyApiApplication.class, args);
        log.info("Aplicação inicializada com sucesso");
    }
}
