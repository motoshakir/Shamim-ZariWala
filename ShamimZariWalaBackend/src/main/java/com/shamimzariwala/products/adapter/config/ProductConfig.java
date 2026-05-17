package com.shamimzariwala.products.adapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shamimzariwala.products.application.port.output.ProductRepository;
import com.shamimzariwala.products.application.port.output.ProductVariantAttributeRepository;
import com.shamimzariwala.products.application.port.output.ProductVariantRepository;
import com.shamimzariwala.products.application.service.ProductService;
import com.shamimzariwala.products.application.service.ProductVariantAttributeService;
import com.shamimzariwala.products.application.service.ProductVariantService;

@Configuration
public class ProductConfig {

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }

    @Bean
    public ProductVariantService productVariantService(ProductVariantRepository variantRepository,
                                                       ProductRepository productRepository) {
        return new ProductVariantService(variantRepository, productRepository);
    }

    @Bean
    public ProductVariantAttributeService productVariantAttributeService(ProductVariantAttributeRepository attributeRepository,
                                                                         ProductVariantRepository variantRepository) {
        return new ProductVariantAttributeService(attributeRepository, variantRepository);
    }
}
