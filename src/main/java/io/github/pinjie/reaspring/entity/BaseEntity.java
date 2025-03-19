package io.github.pinjie.reaspring.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(updatable = false)
	private LocalDateTime createdTime;

	private LocalDateTime updatedTime;

	@PrePersist
	protected void onCreate(){
		this.createdTime = LocalDateTime.now();
		this.updatedTime = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate(){
		this.updatedTime = LocalDateTime.now();
	}
}
