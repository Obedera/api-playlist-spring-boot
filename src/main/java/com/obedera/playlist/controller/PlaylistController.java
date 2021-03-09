package com.obedera.playlist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obedera.playlist.dto.PlaylistDTO;
import com.obedera.playlist.service.PlaylistService;

@RestController
@RequestMapping("/api/v1/playlist")
public class PlaylistController {
	
	
	@Autowired
	private PlaylistService playlistService;
	
	@PostMapping
	public ResponseEntity<PlaylistDTO> add(@RequestBody PlaylistDTO playlist){
		return ResponseEntity.ok(playlistService.addPlaylist(playlist));		
	}
	
	@GetMapping
	public ResponseEntity<List<PlaylistDTO>> obterTodasPlaylist(){
		List<PlaylistDTO> listaBd = playlistService.listar();
		if(listaBd.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(playlistService.listar());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<PlaylistDTO> obterPlaylistPorId(@PathVariable long id){
		
		List<PlaylistDTO> listaBd = playlistService.obterPorId(id);
		if(listaBd.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(listaBd.get(0));
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<PlaylistDTO> atualizarPlayist(
										@PathVariable long id, 
										@RequestBody PlaylistDTO playlist){
		List<PlaylistDTO> listaBd = playlistService.updatePlaylist(playlist,id);
		if(listaBd.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(listaBd.get(0));
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deletarPlaylist(@PathVariable long id){
		boolean exclusaoPlaylist = playlistService.deletePlaylist(id);
		if(exclusaoPlaylist) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	
	
	
	
	
}
