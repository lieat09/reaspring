package io.github.pinjie.reaspring.service;

import io.github.pinjie.reaspring.dto.ProductDto;
import io.github.pinjie.reaspring.entity.Product;
import io.github.pinjie.reaspring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

	private final ProductRepository productRepository;

	public List<ProductDto> getProducts() {

		return productRepository.findAll().stream().map(p -> {
					return ProductDto.builder()
							.name(p.getName())
							.price(p.getPrice())
							.description(p.getDescription())
							.build();
				})
				.toList();
	}

	public ProductDto getProduct(Long id) {

		Product product = productRepository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("找不到Product:　" + id));

		return ProductDto
				.builder()
				.name(product.getName())
				.price(product.getPrice())
				.description(product.getDescription())
				.build();
	}

	public void createProduct(ProductDto productDto) {
		Product product = Product
				.builder()
				.name(productDto.getName())
				.price(productDto.getPrice())
				.description(productDto.getDescription())
				.build();
		productRepository.save(product);
	}

	public void updateProduct(Long id, ProductDto productDto) {
		Product product = Product
				.builder()
				.id(id)
				.name(productDto.getName())
				.price(productDto.getPrice())
				.description(productDto.getDescription())
				.build();
		productRepository.save(product);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}
