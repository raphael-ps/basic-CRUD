package io.github.raphael.basic_CRUD.controllers;


import io.github.raphael.basic_CRUD.domain.product.Product;
import io.github.raphael.basic_CRUD.domain.product.ProductRepository;
import io.github.raphael.basic_CRUD.domain.product.RequestProductPostDTO;
import io.github.raphael.basic_CRUD.domain.product.RequestProductPutDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> allProducts = productRepository.findAllByActiveTrue();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity<String> registerProduct(@RequestBody @Valid RequestProductPostDTO newProduct) {
        Product product = new Product(newProduct);
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> updateProduct(@RequestBody @Valid RequestProductPutDTO newProduct) {
        Optional<Product> optionalProduct = productRepository.findById(newProduct.id());

        if (optionalProduct.isEmpty()) {
            throw new EntityNotFoundException();

        } else {
            Product product = optionalProduct.get();
            product.setName(newProduct.name());
            product.setPrice_in_cents(newProduct.price_in_cents());
            product.setAmount(newProduct.amount());
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.map(ResponseEntity::ok).orElseThrow();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteProductById(@PathVariable String id){
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setActive(false);
            return ResponseEntity.noContent().build();
        }

        throw new EntityNotFoundException();
    }
}
