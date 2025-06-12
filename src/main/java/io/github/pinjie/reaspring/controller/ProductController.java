package io.github.pinjie.reaspring.controller;

import io.github.pinjie.reaspring.dto.ProductDto;
import io.github.pinjie.reaspring.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "practice", description = "practice")
@RequestMapping(path = "/products")
public class ProductController {

	private final ProductService productService;

	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllProducts(){

		List<ProductDto> products = productService.getProducts();

		return ResponseEntity.ok().body(products);
	}

	@GetMapping("{id}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable Long id){

		ProductDto product = productService.getProduct(id);

		return ResponseEntity.ok().body(product);
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto){

		productService.updateProduct(id, productDto);

		return ResponseEntity.ok().build();
	}

	@PostMapping()
	public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDto){

		productService.createProduct(productDto);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id){

		productService.deleteProduct(id);

		return ResponseEntity.ok().build();
	}
}
