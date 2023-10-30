package com.tech.sportsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.sportsys.model.Jugador;
@Repository
public interface JugadorRepository  extends JpaRepository<Jugador, Long>{

}
