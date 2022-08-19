package com.gora.cobranca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gora.cobranca.model.StatusTitulo;
import com.gora.cobranca.model.Titulo;
import com.gora.cobranca.repository.TituloRepository;
import com.gora.cobranca.repository.filter.TituloFilter;

@Service
public class TituloService {
	
	@Autowired
	private TituloRepository tituloRepository;
	
	public void salvar( Titulo titulo ) {
		
		try {
			tituloRepository.save( titulo );
		} catch(  DataIntegrityViolationException ex ) {
			throw new IllegalArgumentException( "Formato de data inválido !" );
		}
	}
	
	public void excluir( Long codTitulo ) {
		tituloRepository.deleteById( codTitulo );
	}

	public List<Titulo> listarTitulos() {
		return tituloRepository.findAll();
	}
	
	public String receber( Long codigo) {
		Titulo titulo = tituloRepository.getById( codigo );
		titulo.setStatus( StatusTitulo.RECEBIDO );
		tituloRepository.save( titulo );
		
		return StatusTitulo.RECEBIDO.getDescricao();
	}

	public List<Titulo> filtrarTitulo(TituloFilter filtro) {
		String descricao = filtro.descricaoValida();
		return tituloRepository.findByDescricaoContaining( descricao );
	}
}
