package com.obedera.playlist.service;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obedera.playlist.dto.PlaylistDTO;
import com.obedera.playlist.model.Playlist;
import com.obedera.playlist.repository.PlaylistRepository;

@Service
public class PlaylistService {
	
	@Autowired
	private PlaylistRepository playlistRepository;
	
	
	public List<PlaylistDTO> listar(){
		
		List<PlaylistDTO> listaConvertida = new ArrayList<>();
		
		
		playlistRepository.findAll()
		.forEach(item -> listaConvertida.add(new PlaylistDTO(item.getNome(), item.getCategoria())));
		
		
		return listaConvertida;
	}
	
	public PlaylistDTO addPlaylist(PlaylistDTO item) {
		Playlist playlistBd = new Playlist();
		playlistBd.setNome(item.getNome_playlist());
		playlistBd.setCategoria(item.getCategoria_playlist());
		playlistRepository.save(playlistBd);
		return item;
	}
	
	
	
	
}
