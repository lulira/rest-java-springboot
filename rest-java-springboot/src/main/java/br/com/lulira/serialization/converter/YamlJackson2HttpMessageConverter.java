package br.com.lulira.serialization.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;



public final class YamlJackson2HttpMessageConverter  extends AbstractJackson2HttpMessageConverter {
    public YamlJackson2HttpMessageConverter() {
        super(new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL),
                MediaType.parseMediaType("application/x-yaml"));

    }
}
