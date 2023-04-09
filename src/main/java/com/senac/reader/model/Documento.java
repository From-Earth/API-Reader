package com.senac.reader.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	private Date data_insercao = new java.sql.Date(System.currentTimeMillis());
	
	
	@NotNull(message = "campo progresso nao pode ser nulo")
	private double progresso =0;
	
	@NotNull(message = "campo publico nao pode ser nulo")
	private boolean publico;
	
	@NotBlank(message = "campo extensao nao pode ser em branco")
	private String extensao;
	
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
		return data_insercao;
	}

	public void setData_insercao(Date data_insercao) {
		this.data_insercao = data_insercao;
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
	
	
	
	
	
	
}
