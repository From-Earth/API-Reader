package com.senac.reader.dto;

import java.util.List;

import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.senac.reader.model.Usuario;

public class DocumentoListaDTO {
	private long id;
	
	private String nome;

	private double progresso;
	
	private boolean publico;
	
	private String extensao;
	
	@JsonIgnoreProperties("documento")
	private Usuario usuario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getProgresso() {
		return progresso;
	}

	public void setProgresso(double progresso) {
		this.progresso = progresso;
	}

	public boolean isPublico() {
		return publico;
	}

	public void setPublico(boolean publico) {
		this.publico = publico;
	}


	
	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public DocumentoListaDTO() {
		
	}
	

	public DocumentoListaDTO(long id, String nome, double progresso, boolean publico, String extensao,  Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.progresso = progresso;
		this.publico = publico;
		this.extensao = extensao;
		this.usuario = usuario;
	}

	
	
	
	
}
