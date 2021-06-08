package com.example.mensageria.store.web.rest;

import br.com.luanrocha.store.entity.Product;
import br.com.luanrocha.store.service.ProductService;
import br.com.luanrocha.store.service.impl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/product")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        Product newProduct = productService.save(product);
        return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
    }

    @PostMapping("/invoice")
    public ResponseEntity<String> sendInvoice(@RequestBody Map<String, Long> map) {
        productService.sendInvoice(map.get("id"));
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }


    @PutMapping("/invoice")
    public ResponseEntity<Product> addInvoice(@RequestBody Map<String, Long> map) {
        Product newProduct = productService.addInvoice(map.get("id"));
        return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
    }


}
