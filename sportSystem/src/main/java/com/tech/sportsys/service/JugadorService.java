package com.tech.sportsys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tech.sportsys.exception.ResourceNotFoundException;
import com.tech.sportsys.model.Jugador;
import com.tech.sportsys.repository.JugadorRepository;

@Component
public class JugadorService {
	@Autowired
	private JugadorRepository jugadorRepository;

	public Jugador crearJugador(Jugador jugador) {
		return jugadorRepository.save(jugador);
	}
	
	public Optional<Jugador> consultarJugadorById(Long id) {
		Optional<Jugador> equipo = jugadorRepository.findById(id);
		return equipo;
	}
	
	public List<Jugador> consultarAllJugador(){
		return jugadorRepository.findAll();
	}
	
	public void eliminarJugador(Long id) {
		Optional<Jugador> optionalJugador = jugadorRepository.findById(id);
		if(optionalJugador==null || optionalJugador.isEmpty()) {
			throw new ResourceNotFoundException("eliminar", "Id",id);
		}
		jugadorRepository.deleteById(id);
	}
	
	public Jugador actualizarJugador(Jugador jugador,Long id) {
		Optional<Jugador> optionalJugador = jugadorRepository.findById(id);
		if(optionalJugador==null || optionalJugador.isEmpty()) {
			throw new ResourceNotFoundException("equipo", "Id",id);
		}
		optionalJugador.get().setApellidos(jugador.getApellidos());
		optionalJugador.get().setFechaNacimiento(jugador.getFechaNacimiento());
		optionalJugador.get().setIdentificacion(jugador.getIdentificacion());
		optionalJugador.get().setNombres(jugador.getNombres());
		optionalJugador.get().setNumeroCamiseta(jugador.getNumeroCamiseta());
		return jugadorRepository.save(optionalJugador.get());
	}
}
