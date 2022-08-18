package com.gora.cobranca.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gora.cobranca.model.StatusTitulo;
import com.gora.cobranca.model.Titulo;
import com.gora.cobranca.repository.TituloRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	private static final String CADASTRO_VIEW = "CadastroTitulo";
	private static final String PESQUISA_VIEW = "PesquisaTitulo";
	private static final String REDIRECT_CADASTRO_VIEW = "redirect:/titulos/novo";
	private static final String REDIRECT_PESQUISA_VIEW = "redirect:/titulos";
	
	
	@Autowired
	private TituloRepository tituloRepository;
	
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
		
		tituloRepository.save( titulo );
		redAttributes.addFlashAttribute("mensagem","Título salvo com sucesso!");
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
		tituloRepository.deleteById( codigo );
		redAttributes.addFlashAttribute("mensagem","Título excluído com sucesso!");
		return REDIRECT_PESQUISA_VIEW;
	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		List<Titulo> listTitulos = tituloRepository.findAll();
		ModelAndView modelView = new ModelAndView( PESQUISA_VIEW );
		modelView.addObject( "titulos", listTitulos );
		return modelView;
	}	
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> retornaStatusTitulo(){
		return Arrays.asList( StatusTitulo.values() );
	}
}
