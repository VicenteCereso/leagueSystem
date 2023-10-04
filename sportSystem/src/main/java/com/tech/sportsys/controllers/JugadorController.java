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
import com.tech.sportsys.model.Jugador;
import com.tech.sportsys.service.JugadorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("tech/jugador")
public class JugadorController {
	public record Greeting(long id, String content) { }
	
	@Autowired
	private JugadorService jugadorService;
	
	@PostMapping("/create")
	public ResponseEntity<Jugador> createJugador(@RequestBody @Valid Jugador jugador) {
		return ResponseEntity.ok(jugadorService.crearJugador(jugador));
	}
	@GetMapping
	public ResponseEntity<List<Jugador>> getAllUsers(){
		List<Jugador> lstJugador = jugadorService.consultarAllJugador();
		if(lstJugador==null || lstJugador.isEmpty()) {
			throw new ResourceNotFoundException("jugadores");
		}
		return ResponseEntity.ok(lstJugador);
	}
	@GetMapping("/jugador/{id}")
	public ResponseEntity<Jugador> searchJugadorById(@PathVariable("id") Long id){
		Optional<Jugador> jugador = null;
		try {
			jugador = jugadorService.consultarJugadorById(id);
			if(jugador==null || jugador.isEmpty()) {
				throw new ResourceNotFoundException("jugador", "Id",id);
			}
		}catch (DataAccessException e) {
			throw new BadRequestException(e.getMessage());
		}
		return ResponseEntity.ok(jugador.get());
	}
	
	@DeleteMapping("/delete")
	public void deleteJugadorById(@RequestBody @Valid Jugador jugador){
		try {
			jugadorService.eliminarJugador(jugador.getId());
		}catch (DataAccessException e) {
			throw new BadRequestException(e.getMessage());
		}
		
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<Jugador> editJugador(@PathVariable("id") Long id, @RequestBody @Valid Jugador jugador) {
		Jugador updateJugador = null;
		try {
			updateJugador = jugadorService.actualizarJugador(jugador,id);
			if(updateJugador==null) {
				throw new ResourceNotFoundException("edit", "Id",id);
			}
			
		}
		catch (DataAccessException e) {
			throw new BadRequestException(e.getMessage());
		}
		return ResponseEntity.ok(updateJugador);
	}
}
