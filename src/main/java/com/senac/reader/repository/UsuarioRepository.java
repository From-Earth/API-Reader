package com.senac.reader.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senac.reader.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Optional<Usuario> findByNome(String nome);
	public Optional<Usuario> findByCpf(String cpf);
	public Optional<Usuario> findByEmail(String email);
}
