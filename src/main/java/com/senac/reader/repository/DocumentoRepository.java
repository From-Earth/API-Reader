package com.senac.reader.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senac.reader.model.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long>{
	public Optional<Documento> findByNomeContainingIgnoreCase(String nome);
}
