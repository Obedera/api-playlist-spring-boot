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
	
	public List<PlaylistDTO> obterPorId(long id) {
		List<PlaylistDTO> listaPlaylistBd = new ArrayList<>();
		
		
		
		playlistRepository.findById(id).ifPresent(item -> {
			PlaylistDTO playlistBd = new PlaylistDTO(item.getNome(),item.getCategoria());
			listaPlaylistBd.add(playlistBd);
		});
		
		
		return listaPlaylistBd;
	
	}
	
	public PlaylistDTO addPlaylist(PlaylistDTO item) {
		Playlist playlistBd = new Playlist();
		playlistBd.setNome(item.getNome_playlist());
		playlistBd.setCategoria(item.getCategoria_playlist());
		playlistRepository.save(playlistBd);
		return item;
	}
	
	public List<PlaylistDTO> updatePlaylist(PlaylistDTO playlistRequest, long id) {
		List<PlaylistDTO> listaPlaylistBd = new ArrayList<>();
		
		playlistRepository.findById(id).ifPresent(item -> {
			item.setNome(playlistRequest.getNome_playlist());
			item.setCategoria(playlistRequest.getCategoria_playlist());
			playlistRepository.save(item);
			listaPlaylistBd.add(playlistRequest);
		});
		
		return listaPlaylistBd;
	}
	
	public boolean deletePlaylist(long id) {
		try {
			playlistRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	
}
