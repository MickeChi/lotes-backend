package com.lotes.lotesbackend.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {
	//https://www.bezkoder.com/spring-boot-file-upload/

	String save(MultipartFile file);

	Resource load(String filename);

	void deleteAll();

	Stream<Path> loadAll();
}
