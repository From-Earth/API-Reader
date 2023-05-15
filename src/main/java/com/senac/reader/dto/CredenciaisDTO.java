package com.senac.reader.dto;

import com.senac.reader.model.Usuario;

public class CredenciaisDTO {
	
	private long id;

	private String nome;
	
	private String email;
	
	private String senha;

	private String token = "Basic ffnweqo489jde2094wrfe3rg54354ag45iunweqf2";
	
	public CredenciaisDTO(Usuario usuario) {
		
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		}
	

	public CredenciaisDTO() {
		super();
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
