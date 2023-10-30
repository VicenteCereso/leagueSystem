package com.tech.sportsys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tech.sportsys.exception.ResourceNotFoundException;
import com.tech.sportsys.model.Equipo;
import com.tech.sportsys.repository.EquipoRepository;

@Component
public class EquipoService {
	@Autowired
	private EquipoRepository equipoRepository;

	public Equipo crearEquipo(Equipo equipo) {
		return equipoRepository.save(equipo);
	}
	
	public Optional<Equipo> consultarEquipoById(Long id) {
		Optional<Equipo> equipo = equipoRepository.findById(id);
		return equipo;
	}
	
	public List<Equipo> consultarAllEquipos(){
		return equipoRepository.findAll();
	}
	
	public void eliminarEquipo(Long id) {
		Optional<Equipo> optionalEquipo = equipoRepository.findById(id);
		if(optionalEquipo==null || optionalEquipo.isEmpty()) {
			throw new ResourceNotFoundException("eliminar", "Id",id);
		}
		equipoRepository.deleteById(id);
	}
	
	public Equipo actualizarEquipo(Equipo equipo,Long id) {
		Optional<Equipo> optionalEquipo = equipoRepository.findById(id);
		if(optionalEquipo==null || optionalEquipo.isEmpty()) {
			throw new ResourceNotFoundException("equipo", "Id",id);
		}
		optionalEquipo.get().setFoto(equipo.getFoto());
		optionalEquipo.get().setLogo(equipo.getLogo());
		optionalEquipo.get().setNombre(equipo.getNombre());
		return equipoRepository.save(optionalEquipo.get());
	}
}
