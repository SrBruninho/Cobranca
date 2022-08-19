package com.gora.cobranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gora.cobranca.model.Titulo;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long> {

}
