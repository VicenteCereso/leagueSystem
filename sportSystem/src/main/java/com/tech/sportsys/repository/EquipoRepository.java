package com.tech.sportsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.sportsys.model.Equipo;
@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

}
