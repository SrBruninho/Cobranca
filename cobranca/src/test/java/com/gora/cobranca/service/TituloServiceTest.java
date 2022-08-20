package com.gora.cobranca.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gora.cobranca.model.StatusTitulo;
import com.gora.cobranca.model.Titulo;
import com.gora.cobranca.repository.TituloRepository;

@ExtendWith(MockitoExtension.class)
class TituloServiceTest {

	@Mock
	private TituloRepository tituloRepository;
	
	@Test
	public void salvar() {
		
		Titulo tituloSalvo = new Titulo( 1L,
				"desc",
				new java.util.Date("20/08/2022"),
				BigDecimal.TEN,
				StatusTitulo.PENDENTE );	
			
		lenient().when( tituloRepository.save( any( Titulo.class ) ) ).thenReturn( tituloSalvo );
		
		Titulo titulo = new Titulo( 1L,
				"desc",
				new java.util.Date("20/08/2022"),
				BigDecimal.TEN,
				StatusTitulo.PENDENTE  );	
		
		assertEquals(tituloSalvo, titulo);
	}
	
	@Test
	public void salvarException() {
		
		Titulo tituloSalvo = new Titulo();	
		
		lenient().when( tituloRepository.save( any( Titulo.class ) ) ).thenReturn( tituloSalvo );
			
		assertThatExceptionOfType(IllegalArgumentException.class);
	}

}
