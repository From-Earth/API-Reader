package com.senac.reader.projection;

import java.util.List;


public interface UsuarioProjection {

	public long getId() ;

	public String getNome() ;

	public String getCpf() ;


	public String getEmail() ;

	public String getSenha();
	public String getTelefone() ;

	public String getLogradouro() ;


	public String getNumeroLogradouro() ;
	
	public String getComplemento() ;

	public List<DocumentoProjection> getDocumento() ;
}
