package com.lab4.lab4.controller;

import com.lab4.lab4.domain.ProductDTO;
import com.lab4.lab4.domain.ProductRequestDto;
import com.lab4.lab4.service.ProductService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductRequestDto productRequestDto){
        productService.addProduct(productRequestDto);
        return new ResponseEntity<ProductRequestDto>(productRequestDto,HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody ProductRequestDto productRequestDto){
        productService.updateProduct(productRequestDto);
        return new ResponseEntity<ProductRequestDto>(productRequestDto,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductByID(@PathVariable String id){
       ProductDTO myProduct =  productService.getProductById(id);
        return new ResponseEntity<ProductDTO>(myProduct,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        List<ProductDTO> productDTOList = productService.getAllProducts();
        return new ResponseEntity<List<ProductDTO>>(productDTOList,HttpStatus.OK);
    }
}
