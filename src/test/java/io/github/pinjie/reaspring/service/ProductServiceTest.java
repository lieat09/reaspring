package io.github.pinjie.reaspring.service;


import io.github.pinjie.reaspring.dto.ProductDto;
import io.github.pinjie.reaspring.entity.Product;
import io.github.pinjie.reaspring.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@InjectMocks
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

	@Test
	void getProduct_shouldReturnDto_whenProductExists(){
		Product product = Product
				.builder()
				.id(1L)
				.name("Test")
				.price(100)
				.description("Test")
				.build();

		when(productRepository.findById(1L)).thenReturn(Optional.of(product));

		ProductDto dto = productService.getProduct(1L);

		assertEquals("Test", dto.getName());
		assertEquals(100, dto.getPrice());
		assertEquals("Test", dto.getDescription());
	}

	@Test
	void getProduct_shouldThrowException_whenNotFound(){

		when(productRepository.findById(99L)).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, ()->{
			productService.getProduct(99L);
		});
	}


}
