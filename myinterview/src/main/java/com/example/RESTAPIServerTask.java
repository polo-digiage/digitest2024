package com.example;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This task expects you to create an implementation of a REST API Server.
 * Your code should expose a REST API. 
 * Feel free to explore possibilities/functionalities/capabilities following Rest standard (best practices) 
 * and any framework or library to help you in this journey.
 * We suggest that your implementation have at least a CRUD scenario about any subject.
 * Be creative!
 *
 */
public class RESTAPIServerTask {
    @RestController
    @RequestMapping("/products")
    public class ProductController {

        private final Map<Long, Product> productMap = new HashMap<>();
        private long idCounter = 1;

        @PostMapping
        public Product createProduct(@RequestBody Product product) {
            product.setId(idCounter++);
            productMap.put(product.getId(), product);
            return product;
        }

        @GetMapping("/{id}")
        public Product getProduct(@PathVariable Long id) {
            return productMap.get(id);
        }

        @GetMapping
        public List<Product> getAllProducts() {
            return new ArrayList<>(productMap.values());
        }

        @PutMapping("/{id}")
        public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
            if (!productMap.containsKey(id)) {
                return null; // or throw exception
            }
            product.setId(id);
            productMap.put(id, product);
            return product;
        }

        @DeleteMapping("/{id}")
        public void deleteProduct(@PathVariable Long id) {
            productMap.remove(id);
        }
    }
}