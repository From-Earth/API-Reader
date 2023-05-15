package com.senac.reader.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class DocumentoUploadDTO {
	
	@NotNull
	private long idUsuario;
	
	@NotBlank
	private MultipartFile arquivo;
	
	
	
}
