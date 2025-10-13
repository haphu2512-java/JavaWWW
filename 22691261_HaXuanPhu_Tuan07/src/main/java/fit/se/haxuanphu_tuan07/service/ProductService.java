package fit.se.haxuanphu_tuan07.service;

import fit.se.haxuanphu_tuan07.entity.Category;
import fit.se.haxuanphu_tuan07.entity.Product;

import java.util.List;


import fit.se.haxuanphu_tuan07.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }
}