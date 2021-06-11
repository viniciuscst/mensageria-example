package com.example.mensageria.store.service.impl;

import com.example.mensageria.store.config.JmsConfig;
import com.example.mensageria.store.entity.Product;
import com.example.mensageria.store.repository.ProductRepository;
import com.example.mensageria.store.service.ProductService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final RabbitTemplate rabbitTemplate;
    private final ProductRepository productRepository;

    public ProductServiceImpl(RabbitTemplate rabbitTemplate, ProductRepository productRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product addInvoice(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.orElseThrow(() -> new NullPointerException("This product not found"));

        product.setHasInvoice(true);

        return productRepository.save(product);

    }

    public void sendInvoice(Long id) throws JsonProcessingException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.orElseThrow(() -> new NullPointerException("This product not found"));
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(product);
        rabbitTemplate.convertAndSend(JmsConfig.directExchangeName, JmsConfig.queueName, json);
    }

}
