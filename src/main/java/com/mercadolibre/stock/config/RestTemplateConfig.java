package com.mercadolibre.stock.config;

import com.mercadolibre.stock.exception.MiddleResponseErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Arrays;

@Configuration
public class RestTemplateConfig {
    private MessageSource messageSource;

    public RestTemplateConfig(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        builder.setConnectTimeout(Duration.ofSeconds(6));
        builder.setReadTimeout(Duration.ofSeconds(6));
        RestTemplate build = builder.build();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        build.setMessageConverters(Arrays.asList(converter, new FormHttpMessageConverter()));
        build.setErrorHandler(new MiddleResponseErrorHandler(messageSource));
        return build;
    }
}
