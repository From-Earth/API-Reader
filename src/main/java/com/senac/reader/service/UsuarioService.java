package com.senac.reader.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.senac.reader.dto.UsuarioLoginDTO;
import com.senac.reader.model.Usuario;
import com.senac.reader.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;	
	
	public Optional<Object> cadastrarUsuario(Usuario usuario){
		return repository.findByCpf(usuario.getCpf()).map(resp ->{
			return Optional.empty();
		}).orElseGet(() ->{
			return Optional.ofNullable(repository.save(usuario));
		});
	}
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		return repository.findByCpf(usuario.getCpf()).map(resp ->{
			resp.setNome(usuario.getNome());
			resp.setEmail(usuario.getEmail());
			resp.setTelefone(usuario.getTelefone());
			resp.setLogradouro(usuario.getLogradouro());
			resp.setNumeroLogradouro(usuario.getNumeroLogradouro());
			resp.setComplemento(usuario.getComplemento());
			resp.setSenha(usuario.getSenha());
			return Optional.ofNullable(repository.save(usuario));
		}).orElseGet(() ->{
			return Optional.ofNullable(repository.save(usuario));
		});
	}
	
	public Optional<Usuario> logar (UsuarioLoginDTO dto){
		return Optional.ofNullable(repository.findByEmail(dto.getEmail()).map((resp) ->{
    		if(resp.getSenha() == dto.getSenha()) {
    			return resp;	
    		}
    		return null;
			
    	}).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,
					"Usuário não encontrado, id inválido!");

		}));
	}
}
