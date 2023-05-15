package com.senac.reader.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senac.reader.dto.DocumentoDTO;
import com.senac.reader.dto.DocumentoListaDTO;
import com.senac.reader.model.Documento;
import com.senac.reader.repository.DocumentoRepository;
import com.senac.reader.repository.UsuarioRepository;

@Service
public class DocumentoService {

	@Autowired
	private DocumentoRepository repository;
	
	@Autowired
	private UsuarioRepository userRepository;
	
	public Object salvarDocumento(long id, MultipartFile arquivo){		
		return userRepository.findById(id).map((resp) ->{
			
			try {					
				DocumentoDTO dto = new DocumentoDTO();
				Documento documento = new Documento();
				
				byte [] arquivoByte = arquivo.getBytes();
				documento.setNome(arquivo.getOriginalFilename());	
				documento.setArquivo(arquivoByte);
				documento.setExtensao(arquivo.getContentType());
				documento.setUsuario(resp);

				return Optional.ofNullable(repository.save(documento));
			} catch (IOException e) {
				e.printStackTrace();
				return java.util.Optional.empty();
			}
		}).orElseGet(()->{
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado!");
		});
	}
	
	public Optional<HttpEntity<byte[]>> buscarDocumento(long id){
		return repository.findById(id).map((resp) ->{
			HttpHeaders httpHeaders = new HttpHeaders();

			httpHeaders.add("Content-Disposition", "attachment;filename=\""+resp.getNome()+"\"");

			HttpEntity<byte[]> entity = new HttpEntity<byte[]>( resp.getArquivo(), httpHeaders);

			
			return Optional.ofNullable(entity);
			
			

		}).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,
					"Documento não encontrado, id inválido!");

		});
	}
    
    public Optional<Object> baixarDocumento(long id){
    	return Optional.ofNullable(repository.findById(id).map((resp) ->{
    		return resp;
    	}).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,
					"Documento não encontrado, id inválido!");

		}));
    	
    }
    
    @SuppressWarnings("null")
	public Optional<List<DocumentoListaDTO>> listar(){
    	List<Documento> documentos =  repository.findAll();
    	List<DocumentoListaDTO> documentosDTO = new  ArrayList<DocumentoListaDTO>();;
    	
    	if(documentos.isEmpty()) {
    		return Optional.empty();
    	}
    	for (Documento documento : documentos) {
			DocumentoListaDTO doc = new DocumentoListaDTO(
					documento.getId(),
					documento.getNome(),
					documento.getProgresso(),
					documento.isPublico(),
					documento.getExtensao(),
					documento.getUsuario()
					);
		documentosDTO.add(doc);
			
		}
    	return Optional.ofNullable(documentosDTO);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
}
