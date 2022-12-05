package com.parkingcontrolapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.format.DateTimeFormatter;

@Configuration
public class DateConfig {
    public static final String DATETIME_FORMAT = "dd/MM/yyyy";
    public static LocalDateTimeSerializer LOCAL_DATETIME_SERIALIZER = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT));

    @Bean // -> cria um componente de consumo
    @Primary // -> prioridade no carregamento de componentes
    public ObjectMapper objectMapper() {
        // configura todo o modulo de data, ou seja, define esse padrao para a aplicação global, sem a necessidade de chamada manual
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LOCAL_DATETIME_SERIALIZER);
        return new ObjectMapper()
                .registerModule(module);
    }
}
