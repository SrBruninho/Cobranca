package com.gora.cobranca.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gora.cobranca.model.StatusTitulo;
import com.gora.cobranca.model.Titulo;
import com.gora.cobranca.repository.TituloRepository;
import com.gora.cobranca.service.TituloService;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	private static final String CADASTRO_VIEW = "CadastroTitulo";
	private static final String PESQUISA_VIEW = "PesquisaTitulo";
	private static final String REDIRECT_CADASTRO_VIEW = "redirect:/titulos/novo";
	private static final String REDIRECT_PESQUISA_VIEW = "redirect:/titulos";
	
	
	@Autowired
	private TituloRepository tituloRepository;
	
	@Autowired
	private TituloService tituloService;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView modelView = new ModelAndView( CADASTRO_VIEW );
		modelView.addObject( "todosStatusTitulo", StatusTitulo.values() );
		modelView.addObject( new Titulo() );
		return modelView;
	}
	
	@PostMapping
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes redAttributes ) {
		if( errors.hasErrors() )
			return CADASTRO_VIEW;
		
		try {
			tituloService.salvar( titulo );
			redAttributes.addFlashAttribute( "mensagem","T??tulo salvo com sucesso!" );
		} catch( IllegalArgumentException ex ) {			
			errors.rejectValue( "dataVencimento",null, ex.getMessage() );
			return CADASTRO_VIEW;
		}

		return REDIRECT_CADASTRO_VIEW;
	}

	@RequestMapping("{codigo}")
	public ModelAndView editar( @PathVariable("codigo") Titulo titulo ) {
		ModelAndView modelView = new ModelAndView( CADASTRO_VIEW );
		modelView.addObject( titulo );
		return modelView;
	}
	
	@DeleteMapping("{codigo}")
	public String deletar(@PathVariable Long codigo, RedirectAttributes redAttributes) {
		tituloService.excluir( codigo );
		redAttributes.addFlashAttribute("mensagem","T??tulo exclu??do com sucesso!");
		return REDIRECT_PESQUISA_VIEW;
	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView modelView = new ModelAndView( PESQUISA_VIEW );
		modelView.addObject( "titulos", tituloService.listarTitulos() );
		return modelView;
	}
	
	@PutMapping("/{codigo}/receber")
	public @ResponseBody String receber( @PathVariable Long codigo ) {
			
		return tituloService.receber( codigo );
	}
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> retornaStatusTitulo(){
		return Arrays.asList( StatusTitulo.values() );
	}
}