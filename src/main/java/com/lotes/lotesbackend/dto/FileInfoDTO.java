package com.lotes.lotesbackend.dto;

import lombok.Data;

@Data
public class FileInfoDTO {
	private String name;
	private String url;

	public FileInfoDTO(String name, String url) {
		this.name = name;
		this.url = url;
	}
}
