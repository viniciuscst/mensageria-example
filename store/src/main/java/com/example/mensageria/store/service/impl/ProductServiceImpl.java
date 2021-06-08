package com.example.mensageria.store.service.impl;

import br.com.luanrocha.store.entity.Product;
import br.com.luanrocha.store.repository.ProductRepository;
import br.com.luanrocha.store.service.ProductService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final JmsTemplate jmsTemplate;

    public ProductServiceImpl(ProductRepository productRepository, JmsTemplate jmsTemplate) {
        this.productRepository = productRepository;
        this.jmsTemplate = jmsTemplate;
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

    public void sendInvoice(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.orElseThrow(() -> new NullPointerException("This product not found"));

        jmsTemplate.convertAndSend("invoice-queue", product);
    }

}
