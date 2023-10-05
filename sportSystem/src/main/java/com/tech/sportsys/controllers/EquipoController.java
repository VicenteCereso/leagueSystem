package com.tech.sportsys.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.sportsys.exception.BadRequestException;
import com.tech.sportsys.exception.ResourceNotFoundException;
import com.tech.sportsys.model.Equipo;
import com.tech.sportsys.service.EquipoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("tech/equipo")
public class EquipoController {
	public record Greeting(long id, String content) { }
	
	@Autowired
	private EquipoService equipoService;
	
	@PostMapping("/create")
	public ResponseEntity<Equipo> crearEquipo(@RequestBody @Valid Equipo equipo) {
		return ResponseEntity.ok(equipoService.crearEquipo(equipo));
	}
	@GetMapping
	public ResponseEntity<List<Equipo>> consultarAllEquipos(){
		List<Equipo> lstEquipo = equipoService.consultarAllEquipos();
		if(lstEquipo==null || lstEquipo.isEmpty()) {
			throw new ResourceNotFoundException("equipos");
		}
		return ResponseEntity.ok(lstEquipo);
	}
	@GetMapping("/equipo/{id}")
	public ResponseEntity<Equipo> buscarEquipoById(@PathVariable("id") Long id){
		Optional<Equipo> equipo = null;
		try {
			equipo = equipoService.consultarEquipoById(id);
			if(equipo==null || equipo.isEmpty()) {
				throw new ResourceNotFoundException("equipo", "Id",id);
			}
		}catch (DataAccessException e) {
			throw new BadRequestException(e.getMessage());
		}
		return ResponseEntity.ok(equipo.get());
	}
	
	@DeleteMapping("/delete")
	public void deleteEquipoById(@RequestBody @Valid Equipo equipo){
		try {
			equipoService.eliminarEquipo(equipo.getId());
		}catch (DataAccessException e) {
			throw new BadRequestException(e.getMessage());
		}
		
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<Equipo> editEquipo(@PathVariable("id") Long id, @RequestBody @Valid Equipo equipo) {
		Equipo updateEquipo = null;
		try {
			updateEquipo = equipoService.actualizarEquipo(equipo,id);
			if(updateEquipo==null) {
				throw new ResourceNotFoundException("edit", "Id",id);
			}
			
		}
		catch (DataAccessException e) {
			throw new BadRequestException(e.getMessage());
		}
		return ResponseEntity.ok(updateEquipo);
	}
}
