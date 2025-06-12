package io.github.pinjie.reaspring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.pinjie.reaspring.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void getAllProducts_shouldReturnOK() throws Exception{
		mockMvc.perform(get("/products"))
				.andExpect(status().isOk());
	}

	@Test
	void getProduct_shouldReturnOK() throws Exception{
		mockMvc.perform(get("/products/1"))
				.andExpect(status().isOk());
	}

	@Test
	void createProduct_shouldReturnOK() throws Exception {
		ProductDto productDto = ProductDto.builder()
				.name("test")
				.price(100)
				.description("test")
				.build();

		mockMvc.perform(post("/products/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(productDto)))
				.andExpect(status().isOk());
	}

	@Test
	void updateProduct_shouldReturnOK() throws Exception{
		ProductDto productDto = ProductDto
				.builder()
				.name("Update")
				.price(100)
				.description("Demo")
				.build();

		mockMvc.perform(put("/products/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(productDto)))
				.andExpect(status().isOk());
	}

	@Test
	void deleteProduct_shouldReturnOK() throws Exception {
		mockMvc.perform(delete("/products/1"))
				.andExpect(status().isOk());
	}
}
