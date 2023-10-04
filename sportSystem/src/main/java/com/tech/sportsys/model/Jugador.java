package com.tech.sportsys.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
@Entity
@Table(name="Jugador")
public class Jugador {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Nombres requerido!")
	@Size(min=2, max=50)
	private  String nombres;
	@NotEmpty(message = "Apellidos requerido!")
	@Size(min=2, max=50)
	private  String apellidos;
	@Size(min=10, max=15)
	private String identificacion;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotEmpty(message = "Fecha de Nacimiento requerida!")
	private Date fechaNacimiento;
	@NotNull
	private Integer numeroCamiseta;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Integer getNumeroCamiseta() {
		return numeroCamiseta;
	}
	public void setNumeroCamiseta(Integer numeroCamiseta) {
		this.numeroCamiseta = numeroCamiseta;
	}
	
	
}
