package com.lotes.lotesbackend.service;

import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl implements FileStorageService {
	
	private final Path fileStorageLocation;

	public FileStorageServiceImpl(Environment env) {
		//this.fileStorageLocation = Paths.get("C:\\docfiles\\");
		this.fileStorageLocation = Paths.get(env.getProperty("app.file.upload-dir", "C:\\docfiles\\"))
				.toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new RuntimeException(
					"Could not create the directory where the uploaded files will be stored.", ex);
		}
	}

	@Override
	public String save(MultipartFile file) {
		String fileName = null;
		try {
			if (file.isEmpty()) {
				throw new Exception("Failed to store empty file.");
			}
			
			fileName = new Date().getTime() + "-file." + getFileExtension(file.getOriginalFilename());
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
		    Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

		} catch (Exception e) {
			if (e instanceof FileAlreadyExistsException) {
				throw new RuntimeException("A file of that name already exists.");
			}
			throw new RuntimeException(e.getMessage());
		}
		
		return fileName;
	}

	@Override
	public Resource load(String filename) {
		try {
			Path file = this.fileStorageLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(this.fileStorageLocation.toFile());
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.fileStorageLocation, 1).filter(path -> !path.equals(this.fileStorageLocation)).map(this.fileStorageLocation::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files!");
		}
	}
		
	private String getFileExtension(String fileName) {
	    if (fileName == null) {
	      return null;
	    }
	    String[] fileNameParts = fileName.split("\\.");

	    return fileNameParts[fileNameParts.length - 1];
	  }

}
