package com.tech.sportsys.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
@Entity
@Table(name="Users")
public class Equipo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Nombre requerido!")
	@Size(min=2, max=50)
	private String nombres;
	@NotEmpty(message = "Apellidos requerido!")
	@Size(min=2, max=50)
	private String apellidos;
	@NotEmpty(message = "Email requerido!")
	@Email
	private String email;
	
}
