package com.senac.reader.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.senac.reader.dto.CredenciaisDTO;
import com.senac.reader.dto.UsuarioCadastroDTO;
import com.senac.reader.dto.UsuarioLoginDTO;
import com.senac.reader.model.Usuario;
import com.senac.reader.projection.UsuarioProjection;
import com.senac.reader.repository.UsuarioRepository;
import com.senac.reader.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioService service;
	
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Object> cadastrar(@Valid @RequestBody UsuarioCadastroDTO usuario){
		return service.cadastrarUsuario(usuario).map(resp -> ResponseEntity.status(201).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PostMapping("/logar")
	public ResponseEntity<CredenciaisDTO> logar (@RequestBody UsuarioLoginDTO dto){
		return service.logar(dto);
	}

	@GetMapping
	public ResponseEntity<List<UsuarioProjection>> listar(){
		return ResponseEntity.ok(repository.findAllByOrderByIdDesc());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> listar(@Valid @PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PutMapping("/atualizar")
	public Optional<Usuario> atualizar(@Valid @RequestBody Usuario usuario){
		return repository.findById(usuario.getId()).map(resp ->{
			resp.setNome(usuario.getNome());
			resp.setEmail(usuario.getEmail());
			resp.setTelefone(usuario.getTelefone());
			resp.setCpf(usuario.getCpf());
			resp.setLogradouro(usuario.getLogradouro());
			resp.setNumeroLogradouro(usuario.getNumeroLogradouro());
			resp.setComplemento(usuario.getComplemento());
			return Optional.ofNullable(repository.save(resp));
			//return ResponseEntity.status(201).body(resp);
		}).orElseThrow(() ->{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário inválido");
		});
	}
	
	@DeleteMapping("/{id_usuario}")
	public ResponseEntity<Object> deletar(@PathVariable(value = "id_usuario") Long idUsuario) {
		return repository.findById(idUsuario).map(idExistente -> {
			repository.deleteById(idUsuario);
			return ResponseEntity.status(200).build();
		}).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID inexistente, passe um ID valido para deletar!");
		});
	}
}
