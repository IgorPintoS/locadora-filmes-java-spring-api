package com.locadora.filmes.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig { //evitar instancias desnecessárias

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
