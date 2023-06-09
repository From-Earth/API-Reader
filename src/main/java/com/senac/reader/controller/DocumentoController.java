package com.senac.reader.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senac.reader.dto.DocumentoDTO;
import com.senac.reader.dto.DocumentoEditDTO;
import com.senac.reader.dto.DocumentoListaDTO;
import com.senac.reader.model.Documento;
import com.senac.reader.model.Usuario;
import com.senac.reader.projection.DocumentoProjection;
import com.senac.reader.repository.DocumentoRepository;
import com.senac.reader.service.DocumentoService;

@RestController
@RequestMapping("/documentos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DocumentoController {
	
	@Autowired
	private DocumentoRepository repository;
	
	@Autowired
	private DocumentoService service;
	
	@GetMapping
	public ResponseEntity<Page<DocumentoProjection>> lista(@RequestParam(defaultValue = "0") int pagina){
		return service.listar(pagina).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
		}
	@PostMapping("/upload")
	public Object upload(@RequestParam long id, MultipartFile arquivo){
		
		return service.salvarDocumento(id, arquivo);
	}
	
	@PutMapping("/atualizar")
	public Object atualizar(@RequestBody DocumentoEditDTO dto){
		
		return service.atualizarDocumento(dto);
	}
	
	
	@GetMapping("/download/{id}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable long id){
		Documento doc = (Documento) service.baixarDocumento(id).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(doc.getExtensao()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getNome()+"\"")
				.body(new ByteArrayResource(doc.getArquivo()));
		
	}

	@GetMapping("/{id}")
	public ResponseEntity<Documento> listar(@Valid @PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletar(@PathVariable(value = "id") Long id) {
		return repository.findById(id).map(idExistente -> {
			repository.deleteById(id);
			return ResponseEntity.status(204).build();
		}).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID inexistente, passe um ID valido para deletar!");
		});
	}

}
