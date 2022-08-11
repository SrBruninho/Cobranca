package com.gora.cobranca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gora.cobranca.model.Titulo;

@Controller
@RequestMapping("/titulos")
public class CadastroTituloController {
	
	@RequestMapping("/novo")
	public String novo() {
		return "CadastroTitulo";
	}
	
	@RequestMapping(method = RequestMethod.POST )
	public String salvar( Titulo titulo ) {
		System.out.println( ">>>> " + titulo.getDescricao());
		
		return "CadastroTitulo";
	}

}
