package com.lab4.lab4.service;

import com.lab4.lab4.domain.Product;
import com.lab4.lab4.domain.ProductDTO;
import com.lab4.lab4.domain.ProductRequestDto;
import com.lab4.lab4.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;

    public Product addProduct(ProductRequestDto productRequestDto) {
        Product mappedProduct = modelMapper.map(productRequestDto, Product.class);
        Product savedProduct = productRepository.save(mappedProduct);
        return savedProduct;
    }

    public Product updateProduct(ProductRequestDto productRequestDto) {
        Product mappedProduct = modelMapper.map(productRequestDto, Product.class);
        Product foundProduct = productRepository.findById(mappedProduct.getId()).orElse(null);

        if (foundProduct != null) {
            foundProduct.setPrice(mappedProduct.getPrice());
            productRepository.save(foundProduct);
        }

        return foundProduct;
    }

    public void deleteProduct(String productId) {
//        ObjectId objectId = new ObjectId(productId);
        Product foundProduct = productRepository.findById(productId).orElse(null);

        if (foundProduct != null) {
            productRepository.delete(foundProduct);
        }
    }

    public ProductDTO getProductById(String productId) {
//        ObjectId objectId = new ObjectId(productId);
        Product foundProduct = productRepository.findById(productId).orElse(null);

        if (foundProduct != null) {
            ProductDTO mappedProduct = modelMapper.map(foundProduct, ProductDTO.class);
            return mappedProduct;
        }

        return null;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOList = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
        return productDTOList;
    }
}
