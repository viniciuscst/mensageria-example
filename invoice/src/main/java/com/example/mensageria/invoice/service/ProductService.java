package com.example.mensageria.invoice.service;

import com.example.mensageria.invoice.entity.Product;
import com.example.mensageria.invoice.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.stereotype.Component;

import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @SneakyThrows
    public void consumerProduct(String productJson) throws JsonProcessingException {

        Product product = new ObjectMapper().readValue(productJson,  Product.class);

        if(!product.isHasInvoice()) {
            try {
                product.setHasInvoice(true);
                save(product);
                updateProduct(product);
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println(e.getMessage());
            }

        }
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Product product) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/product/invoice";

        Map<String, Long> map = new HashMap<>();

        map.put("id", product.getId());

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Map<String, Long>> requestUpdate = new HttpEntity<Map<String, Long>>(map, headers);
        restTemplate.put(url, requestUpdate);
    }

}
