package com.senac.reader.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senac.reader.model.Documento;
import com.senac.reader.projection.DocumentoProjection;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long>{
	public Optional<Documento> findByNomeContainingIgnoreCase(String nome);
	
	public Page<DocumentoProjection> findAllByOrderByDataInsercaoDesc(Pageable pageable);
	
	//public Optional<DocumentoProjection> findById(long id);
	
	
	
}
