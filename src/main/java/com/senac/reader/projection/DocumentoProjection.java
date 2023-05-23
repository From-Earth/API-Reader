package com.senac.reader.projection;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.senac.reader.model.Usuario;

public interface DocumentoProjection {
	public int getId(); 

	public String getNome() ;

    public Date getDataInsercao() ;	

	public double getProgresso(); 

	public boolean isPublico();

	public String getExtensao();
	public String getDescricao();
	@JsonIgnoreProperties("documento")
	public Usuario getUsuario();
}
