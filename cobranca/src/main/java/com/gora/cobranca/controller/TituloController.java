package com.gora.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gora.cobranca.model.StatusTitulo;
import com.gora.cobranca.model.Titulo;
import com.gora.cobranca.repository.TituloRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private TituloRepository tituloRepository;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView modelView = new ModelAndView( "CadastroTitulo" );
		modelView.addObject( "todosStatusTitulo", StatusTitulo.values() );
		modelView.addObject( new Titulo() );
		return modelView;
	}
	
	@PostMapping
	public ModelAndView salvar(@Validated Titulo titulo, Errors errors ) {
		ModelAndView modelView = new ModelAndView( "CadastroTitulo" );

		if( errors.hasErrors() )
			return modelView;
		
		tituloRepository.save( titulo );
		modelView.addObject("mensagem","Título salvo com sucesso!");
		return modelView;
	}

	@GetMapping
	public ModelAndView pesquisar() {
		List<Titulo> listTitulos = tituloRepository.findAll();
		ModelAndView modelView = new ModelAndView( "PesquisaTitulo" );
		modelView.addObject( "titulos", listTitulos );
		return modelView;
	}
	
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> retornaStatusTitulo(){
		return Arrays.asList( StatusTitulo.values() );
	}
}
