package com.senac.reader.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.senac.reader.dto.CredenciaisDTO;
import com.senac.reader.dto.UsuarioCadastroDTO;
import com.senac.reader.dto.UsuarioLoginDTO;
import com.senac.reader.model.Usuario;
import com.senac.reader.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;		
	
	public Optional<Object> cadastrarUsuario(UsuarioCadastroDTO dto){
		return Optional.ofNullable(repository.findByEmail(dto.getEmail()).map(resp ->{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já utilizado!");
		}).orElseGet(() ->{
			
			Usuario usuario = new Usuario();
			usuario.setNome(dto.getNome());
			usuario.setEmail(dto.getEmail());
			usuario.setSenha(dto.getSenha());
			
			return Optional.ofNullable(repository.save(usuario));
		}));
	}
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		return repository.findByEmail(usuario.getEmail()).map(resp ->{
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
	
	public ResponseEntity<CredenciaisDTO> logar (UsuarioLoginDTO dto){
		return repository.findByEmail(dto.getEmail()).map(resp ->{
			if(dto.getSenha().equals(resp.getSenha())) {
				CredenciaisDTO credenciais = new CredenciaisDTO(resp);			
								
				return ResponseEntity.status(201).body(credenciais);
			}else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha Incorreta!");
			}
		}).orElseGet(() -> {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email não existe!");
		});
			
}}
