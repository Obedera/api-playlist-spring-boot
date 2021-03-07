package com.obedera.playlist.dto;

public class PlaylistDTO {
	
	private String nome_playlist;
	private String categoria_playlist;
	
	
	public String getNome_playlist() {
		return nome_playlist;
	}
	
	public void setNome_playlist(String nome_playlist) {
		this.nome_playlist = nome_playlist;
	}
	
	
	public String getCategoria_playlist() {
		return categoria_playlist;
	}

	public void setCategoria_playlist(String categoria_playlist) {
		this.categoria_playlist = categoria_playlist;
	}

	public PlaylistDTO(String nome_playlist, String categoria_playlist) {
		super();
		this.nome_playlist = nome_playlist;
		this.categoria_playlist = categoria_playlist;
	}	

}

