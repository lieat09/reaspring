package io.github.pinjie.reaspring.controller;

import io.github.pinjie.reaspring.dto.ProductDto;
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

	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllProducts(){
		return ResponseEntity.ok().body(List.of());
	}

	@GetMapping("{id}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable Long id){
		return ResponseEntity.ok().body(ProductDto.builder().build());
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody ProductDto request){
		return ResponseEntity.ok().build();
	}

	@PostMapping()
	public ResponseEntity<Void> createProduct(){
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
		return ResponseEntity.ok().build();
	}
}
