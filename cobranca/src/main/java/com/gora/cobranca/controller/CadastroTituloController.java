package com.gora.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gora.cobranca.model.StatusTitulo;
import com.gora.cobranca.model.Titulo;
import com.gora.cobranca.repository.TituloRepository;

@Controller
@RequestMapping("/titulos")
public class CadastroTituloController {
	
	@Autowired
	private TituloRepository tituloRepository;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView modelView = new ModelAndView( "CadastroTitulo" );
		modelView.addObject( "todosStatusTitulo", StatusTitulo.values() );
		return modelView;
	}
	
	@RequestMapping(method = RequestMethod.POST )
	public ModelAndView salvar( Titulo titulo ) {
		tituloRepository.save( titulo );
		ModelAndView modelView = new ModelAndView( "CadastroTitulo" );
		modelView.addObject("mensagem","Título salvo com sucesso!");
		return modelView;
	}

	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> retornaStatusTitulo(){
		return Arrays.asList( StatusTitulo.values() );
	}
}