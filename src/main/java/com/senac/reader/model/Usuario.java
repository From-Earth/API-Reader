package com.senac.reader.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "campo nome nao pode ser em branco")
	@Size(min = 3, max = 50 )
	private String nome;	

	//@NotBlank(message = "campo cpf nao pode ser em branco")
	@Size(min = 11, max = 14 )	
	private String cpf;
	

	@NotBlank(message = "campo email nao pode ser em branco")
	@Size(min = 5, max = 50 )
	private String email;
	

	@NotBlank(message = "campo senha nao pode ser em branco")
	@Size(min = 8, max = 30 )
	private String senha;

	//@NotBlank(message = "campo telefone nao pode ser em branco")
	@Size(min = 8, max = 50 )
	private String telefone;

	//@NotBlank(message = "campo logradouro nao pode ser em branco")
	@Size(min = 3, max = 50 )
	private String logradouro;

	//@NotBlank(message = "campo numeroLogradouro nao pode ser em branco")
	@Size(min = 3, max = 50 )
	private String numeroLogradouro;
	
	@Size(min = 3, max = 50 )
	private String complemento;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"usuario"})
	private List<Documento> documento = new ArrayList<>();
	

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumeroLogradouro() {
		return numeroLogradouro;
	}

	public void setNumeroLogradouro(String numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
	}

	
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public List<Documento> getDocumento() {
		return documento;
	}

	public void setDocumento(List<Documento> documento) {
		this.documento = documento;
	}

	
}
