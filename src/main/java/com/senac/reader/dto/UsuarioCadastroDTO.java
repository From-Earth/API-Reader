package com.senac.reader.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioCadastroDTO {

	
	@NotBlank 
	@Size(min = 3, max = 50,  message = "Nome deve ter de 3 á 50 caracteres")
	private String nome;
	
	 @NotBlank 
	 @Email(message = "Campo deve ser um email") 
	 private String email; 
	 
	 @NotBlank 
	 @Size(min = 8, max = 30,  message = "Senha deve ter de 8 á 30 caracteres")
	 private String senha;


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
	
}
