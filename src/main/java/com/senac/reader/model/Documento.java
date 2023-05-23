package com.senac.reader.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "documento")
public class Documento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Lob
	@NotNull(message = "campo arquivo nao pode ser em branco")
	private byte[] arquivo;
	
	@NotBlank(message = "campo nome nao pode ser em branco")
	@Size(min = 3, max = 50 )
	private String nome;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_insercao")
	private Date dataInsercao = new java.sql.Date(System.currentTimeMillis());
	
	
	@NotNull(message = "campo progresso nao pode ser nulo")
	private double progresso =0;
	
	@NotNull(message = "campo publico nao pode ser nulo")
	private boolean publico;
	
	@NotBlank(message = "campo extensao nao pode ser em branco")
	private String extensao;
	
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "fk_usuario")
	@JsonIgnoreProperties("documento")
	@NotNull(message = "Campo documento não pode ser nulo")
	private Usuario usuario;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData_insercao() {
		return dataInsercao;
	}

	public void setData_insercao(Date data_insercao) {
		this.dataInsercao = data_insercao;
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
	
	

	public Date getDataInsercao() {
		return dataInsercao;
	}

	public void setDataInsercao(Date dataInsercao) {
		this.dataInsercao = dataInsercao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
	
	
}
