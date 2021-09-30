package com.mercadolibre.stock.service.impl;

import com.mercadolibre.stock.model.dto.ml.ItemMLDTO;
import com.mercadolibre.stock.service.MercadoLibreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service("mercadoLibreService")
public class MercadoLibreServiceImpl implements MercadoLibreService {
    @Value("${spring.application.mercado-libre.endpoint.items}")
    private String ENDPOINT_ITEMS;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ItemMLDTO getItemById(String itemId) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(ENDPOINT_ITEMS)
                .pathSegment("{itemId}")
                .buildAndExpand(itemId);

        return restTemplate.getForObject(uriComponents.toUriString(), ItemMLDTO.class);
    }
}
