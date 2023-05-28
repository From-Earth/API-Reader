package com.senac.reader.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.senac.reader.dto.DocumentoEditDTO;
import com.senac.reader.model.Documento;
import com.senac.reader.projection.DocumentoProjection;
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
	
	public Object atualizarDocumento(DocumentoEditDTO documento){		
		return repository.findById(documento.getId()).map((resp) ->{
			
			resp.setNome(documento.getNome());
			resp.setPublico(documento.isPublico());
			resp.setDescricao(documento.getDescricao());
			return Optional.ofNullable(repository.save(resp));
		}).orElseGet(()->{
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Documento não encontrado!");
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
	public Optional<Page<DocumentoProjection>> listar(int pagina){
    	PageRequest pageRequest = PageRequest.of(pagina, 20);
    	Page<DocumentoProjection> documentos =  repository.findAllByOrderByDataInsercaoDesc(pageRequest);
    	
    	if(documentos.isEmpty()) {
    		return Optional.empty();
    	}
    	
			
		
    	return Optional.ofNullable(documentos);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
}
