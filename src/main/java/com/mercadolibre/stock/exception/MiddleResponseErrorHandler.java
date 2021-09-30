package com.mercadolibre.stock.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.stock.model.dto.ErrorDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Locale;

public class MiddleResponseErrorHandler extends DefaultResponseErrorHandler {
    private final Log logger = LogFactory.getLog(this.getClass());
    private static final String DEFAULT_CHARSET = "UTF-8";
    private MessageSource messageSource;

    public MiddleResponseErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return super.hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        ErrorDTO result = new ErrorDTO(messageSource.getMessage("api.error.external.unknown", null, Locale.ENGLISH));
        try {
            result = new ObjectMapper().readValue(getResponseBodyAsString(super.getResponseBody(response), super.getCharset(response)), ErrorDTO.class);
        } catch (JsonParseException e) {
            logger.error(e.getMessage());
        } catch (JsonMappingException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        throw new CustomException(result);
    }

    private String getResponseBodyAsString(byte[] responseBody, Charset responseCharset) {
        String charSet = (responseCharset != null ? responseCharset.name() : DEFAULT_CHARSET);

        try {
            return new String(responseBody, charSet);
        } catch (UnsupportedEncodingException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
