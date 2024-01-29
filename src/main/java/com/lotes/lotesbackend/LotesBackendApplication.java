package com.lotes.lotesbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lotes.lotesbackend.service.FileStorageService;

import jakarta.annotation.Resource;

@SpringBootApplication
public class LotesBackendApplication implements CommandLineRunner{
	
	@Resource
	FileStorageService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(LotesBackendApplication.class, args);
	}
	
	@Override
	  public void run(String... arg) throws Exception {
	    storageService.init();
	  }
}
