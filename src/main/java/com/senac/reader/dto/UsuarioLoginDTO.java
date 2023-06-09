package com.senac.reader.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioLoginDTO {
	
	 @NotBlank 
	 @Email(message = "Campo deve ser um email") 
	 private String email; 
	 
	 @NotBlank 
	 @Size(min = 8, max = 30,  message = "Senha deve ter de 8 á 30 caracteres")
	 private String senha;

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
