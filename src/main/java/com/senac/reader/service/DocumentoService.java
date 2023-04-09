package com.senac.reader.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import javax.print.Doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.senac.reader.dto.DocumentoDTO;
import com.senac.reader.model.Documento;
import com.senac.reader.repository.DocumentoRepository;

@Service
public class DocumentoService {

	@Autowired
	private DocumentoRepository repository;
	
	public Optional<Object> salvarDocumento(DocumentoDTO dto){
		return repository.findByNomeContainingIgnoreCase(dto.getNome()).map((resp) ->{
			return Optional.empty();
		}).orElseGet(()->{
			
			MultipartFile arquivo = dto.getArquivo();			
			try {
				byte [] arquivoByte = dto.getArquivo().getBytes();
				Documento documento = new Documento();
				documento.setNome(dto.getArquivo().getOriginalFilename());
				documento.setProgresso(dto.getProgresso());
				documento.setArquivo(arquivoByte);
				documento.setExtensao(dto.getArquivo().getContentType());

				return Optional.ofNullable(repository.save(documento));
			} catch (IOException e) {
				e.printStackTrace();
				return java.util.Optional.empty();
			}			
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
}
